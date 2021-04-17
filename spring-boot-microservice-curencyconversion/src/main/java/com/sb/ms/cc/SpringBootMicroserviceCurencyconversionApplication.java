package com.sb.ms.cc;

import java.math.BigDecimal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
//@EnableFeignClients("com.sb.ms.cc.proxy")
@EnableHystrixDashboard
@EnableCircuitBreaker
public class SpringBootMicroserviceCurencyconversionApplication {

	public static void main(String[] args) {
		ApplicationContext ctx=SpringApplication.run(SpringBootMicroserviceCurencyconversionApplication.class, args);
		//com.sb.ms.cc.controller.CurrencyConversionController currencyConversionController=ctx.getBean(com.sb.ms.cc.controller.CurrencyConversionController.class);
		//System.out.println(currencyConversionController);
		//for(int i=0;i<100;i++)
		//currencyConversionController.convertCurrency("EURO", "INR", new BigDecimal(10000));
	}

}
