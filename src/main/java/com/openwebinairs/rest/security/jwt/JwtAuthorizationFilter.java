package com.openwebinairs.rest.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.openwebinairs.rest.users.model.UserEntity;
import com.openwebinairs.rest.users.services.CustomUserDetailsService;

import lombok.extern.slf4j.Slf4j;

/**
 * clase para filtrar peticiones
 * 
 * @author juan
 * 
 *         OncePerRequestFilter: Filtro que va a ejecutarse una vez en cada
 *         petición
 */
@Component
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider tokenProvider;
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	
	/**
	 * método se ejecuta en cada petición
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = getJwtFromRequest(request);
			if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
				Long userId = tokenProvider.getUserIdFromJWT(token);
				UserEntity user = (UserEntity) userDetailsService.loadUserById(userId);

				// diseñada para la presentación simple de un nombre de usuario y contraseña.
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
						user.getRoles(), user.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetails(request));
				//Guarda el usuario en el contexto de seguridad
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			log.info("No se ha podido establecer la autenticación de usuario en el contexto de seguridad");
		}
		filterChain.doFilter(request, response);
	}

	/**
	 * 
	 * @return devulevel el tojen sin el BEARER
	 */
	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(JwtTokenProvider.TOKEN_HEADER);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtTokenProvider.TOKEN_PREFIX)) {
			return bearerToken.substring(JwtTokenProvider.TOKEN_PREFIX.length(), bearerToken.length());
		}
		return null;
	}
}
