package com.openwebinairs.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 
 * @author juan
 * @EnableJpaAuditing habilita el mec
 */
@SpringBootApplication
@EnableJpaAuditing
public class ModeloUsuairioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModeloUsuairioApplication.class, args);
	}

}
