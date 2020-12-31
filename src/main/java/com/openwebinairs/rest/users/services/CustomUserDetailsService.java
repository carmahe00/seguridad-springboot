package com.openwebinairs.rest.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserEntityService userDetailsService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userDetailsService.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(username+" no encontrado"));
	}

	public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
		return userDetailsService.findById(id)
				.orElseThrow(()-> new UsernameNotFoundException("Usuario con Id: "+id+" no encontrado"));
	}
}
