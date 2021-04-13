package com.sb.ms.cc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.sb.ms.cc.proxy")
public class SpringBootMicroserviceCurencyconversionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMicroserviceCurencyconversionApplication.class, args);
	}

}
