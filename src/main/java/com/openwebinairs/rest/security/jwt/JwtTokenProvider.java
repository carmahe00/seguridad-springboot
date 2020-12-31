package com.openwebinairs.rest.security.jwt;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.openwebinairs.rest.users.model.UserEntity;
import com.openwebinairs.rest.users.model.UserRole;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenProvider {

	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String TOKEN_TYPE = "JWT";

	/**
	 * @Value busca application.prperties, pero sino lo encuentra toma este valor
	 */
	@Value("${jwt.secret:EnUnLugarDeLaManchaDeCuyoNombreNoQuieroAcordarmeNoHaMuchoTiempoQueVivíaUnHidalgo}")
	private String jwtSecreto;

	@Value("${jwt.token-expiration:864000}")
	private int jwtDurationTokenInSeconds;
	
	/**
	 * 
	 * @param authentication usuairo autenticado
	 * @return genera el token
	 */
	public String generateToken(Authentication authentication) {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		Date tokenExpiration = new Date(System.currentTimeMillis() + (jwtDurationTokenInSeconds* 1000));
		return Jwts.builder()
				.signWith(Keys.hmacShaKeyFor(jwtSecreto.getBytes()), SignatureAlgorithm.HS512)
				.setHeaderParam("type", TOKEN_TYPE)
				.setSubject(Long.toString(user.getId()))
				.setIssuedAt(new Date())
				.setExpiration(tokenExpiration)
				.claim("fullname", user.getUsername())
				//agrega los roles en string separado por ,
				.claim("roles", user.getRoles()
						.stream()
						.map(UserRole::name)
						.collect(Collectors.joining(", ")))
				.compact();
	}
	
	
	/**
	 * 
	 * @param token token convertido
	 * @return devuelve el id(nuestro caso el sujeto)
	 */
	public Long getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(Keys.hmacShaKeyFor(jwtSecreto.getBytes()))
				.parseClaimsJws(token)
				.getBody();
		return Long.parseLong(claims.getSubject());
	}
	
	/**
	 * método validar token
	 * @param authToken token encriptado
	 * @return true | false depende si se puede parsear el token con la firma
	 */
	public boolean validateToken(String authToken) {
		try {			
			Jwts.parser().setSigningKey(jwtSecreto.getBytes()).parse(authToken);
			return true;
		} catch (Exception ex ) {
			log.info(ex.getMessage());
		}
		return false;
		
	}
}
