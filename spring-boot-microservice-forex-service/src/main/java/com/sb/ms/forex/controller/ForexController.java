package com.sb.ms.forex.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sb.ms.forex.entity.ExchangeValue;
import com.sb.ms.forex.repo.ExchangeValueRepository;


@RestController
public class ForexController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository repository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	//@RequestMapping(value = "/currency-exchange/from/{from}/to/{to", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getDataFallBack")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from,@PathVariable String to)
	{
		ExchangeValue exchangeValue=null;
		System.out.println("inside retrieveExchangeValue");
		try {
			Thread.sleep(3000);
			exchangeValue=repository.findByFromAndTo(from, to);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(exchangeValue.getConversionMultiple().intValue()<50)
		{
			throw new RuntimeException("Amount is not correct");
		}
		
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return exchangeValue;
	}
	
	public ExchangeValue getDataFallBack(String from,String to)
	{
		System.out.println("inside getDataFallBack");
		ExchangeValue exchangeValue=new ExchangeValue();
		exchangeValue.setConversionMultiple(new BigDecimal(0));
		exchangeValue.setFrom("INR");
		exchangeValue.setTo("EURO");
		exchangeValue.setId(8032L);
		exchangeValue.setPort(8080);
		return exchangeValue;
		
	}
	

}
