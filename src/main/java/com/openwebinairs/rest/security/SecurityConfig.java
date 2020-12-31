package com.openwebinairs.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.openwebinairs.rest.security.jwt.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationEntryPoint jwtAuthenticationEntryPoint;
	/**
	 * @param jwtAuthorizationFilter filtro de seguridad
	 */
	@Autowired
	private JwtAuthorizationFilter jwtAuthorizationFilter;
	
	/**
	 * registrar bean para poder user AuthenticationManager 
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	/**
	 * configura la autenticación
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	/**
	 * control de acceso. Primero: configuración basica. segundo: cuando falle la
	 * autenticación pasa la configuración ya establecida
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		//pasamos configuración acceso denegado
		.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
		.and()
		.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/producto/**", "/lote/**").hasRole("USER")
			.antMatchers(HttpMethod.POST, "/producto/**", "/lote/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.PUT, "/producto/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.DELETE, "/producto/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/pedido/**").hasAnyRole("ADMIN", "USER")
		.and()
		.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/user/")
			.permitAll();
		
		//añadimos filtro
		http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
