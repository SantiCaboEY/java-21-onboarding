package com.example.mspersonas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MsPersonasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPersonasApplication.class, args);
	}

}
