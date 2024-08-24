package com.example.mscuentas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableFeignClients
public class MsCuentasApplication {
	//Todo: Add servlet request response logging interceptor.
	//Todo: change Kafka's logging level
	public static void main(String[] args) {
		SpringApplication.run(MsCuentasApplication.class, args);
	}
}
