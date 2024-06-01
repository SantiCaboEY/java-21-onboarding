package com.example.mscuentas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableFeignClients
public class MsCuentasApplication {

	//Todo Request-Response log
	//Todo Inbound Outbound log
	public static void main(String[] args) {
		SpringApplication.run(MsCuentasApplication.class, args);
	}

}
