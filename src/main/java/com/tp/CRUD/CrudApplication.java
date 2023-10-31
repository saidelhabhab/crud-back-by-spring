package com.tp.CRUD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.SecureRandom;
import java.util.Base64;

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) {

		SpringApplication.run(CrudApplication.class, args);

		SecureRandom random = new SecureRandom();
		byte[] keyBytes = new byte[32]; // 256 bits (32 bytes) is a common choice for HMAC-SHA256
		random.nextBytes(keyBytes);

		String secretKey = Base64.getEncoder().encodeToString(keyBytes);
		System.out.println("Generated SECRET_KEY : " + secretKey);
	}


}
