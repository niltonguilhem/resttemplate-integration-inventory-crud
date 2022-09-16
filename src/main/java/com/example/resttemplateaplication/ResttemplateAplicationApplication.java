package com.example.resttemplateaplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.management.remote.JMXServerErrorException;
import javax.naming.NamingException;

@SpringBootApplication
public class ResttemplateAplicationApplication {

	private static Logger logger = LoggerFactory.getLogger(ResttemplateAplicationApplication.class);
	public static void main(String[] args) throws NamingException, JMXServerErrorException {
		logger.info("Iniciando a api controle de vagas");
		SpringApplication.run(ResttemplateAplicationApplication.class, args);
		logger.info("API de integração do controle de estoque iniciada e pronta para receber requisições");
	}

}
