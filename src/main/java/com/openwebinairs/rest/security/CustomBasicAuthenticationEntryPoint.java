package com.openwebinairs.rest.security;

/*import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openwebinairs.rest.error.exceptions.ApiError;*/

//@Component
public class CustomBasicAuthenticationEntryPoint 
//extends BasicAuthenticationEntryPoint 
{

	/*
	 * @Autowired private ObjectMapper mapper;
	 * 
	 *//**
		 * modifica lo encabezados cuando falle la autenticación
		 */
	/*
	 * @Override public void commence(HttpServletRequest request,
	 * HttpServletResponse response, AuthenticationException authException) throws
	 * IOException { response.addHeader("WWW-Authenticate",
	 * "Basic realm=\""+getRealmName()+"\"");
	 * response.setContentType("application/json");
	 * response.setStatus(HttpStatus.UNAUTHORIZED.value());
	 * 
	 * ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED,
	 * authException.getMessage()); //ApiError to String String strApiError =
	 * mapper.writeValueAsString(apiError);
	 * 
	 * PrintWriter writer = response.getWriter(); writer.println(strApiError); }
	 * 
	 *//**
		 * método obligatorio para definir el nombre de reino
		 *//*
			 * @PostConstruct public void initRealname() { setRealmName("openwebinars.net");
			 * }
			 */
}
