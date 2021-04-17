package com.sb.ms.cc.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sb.ms.cc.model.CurrencyConversionBean;
//import com.sb.ms.cc.model.ExchangeValue;
//import com.sb.ms.cc.proxy.CurrencyExchangeServiceProxy;

@RestController
public class CurrencyConversionController {
	
	
	//@Autowired
	//private CurrencyExchangeServiceProxy proxy;
	
	@Autowired
	private LoadBalancerClient loadBalancer;
	
	@Autowired
	  RestTemplate restTemplate;
	
	@Value("${forex-service-url}")
	private String forex_Service_url;
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	
	@HystrixCommand(fallbackMethod = "callForexServiceAndGetData_Fallback",  commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
	public CurrencyConversionBean convertCurrency(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity)
	{
ServiceInstance serviceInstance=loadBalancer.choose("forex-service");
		
		System.out.println(serviceInstance.getUri());
		
String baseUrl=serviceInstance.getUri().toString();
		
		baseUrl=baseUrl+"/currency-exchange/from/{from}/to/{to}";
		
		Map<String,String> uriVariables =new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		ResponseEntity<CurrencyConversionBean> responseEntity= restTemplate.getForEntity(baseUrl, CurrencyConversionBean.class,uriVariables);
		
		CurrencyConversionBean response = responseEntity.getBody();
		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
		        quantity.multiply(response.getConversionMultiple()), response.getPort());
		
		
	}
	
	@SuppressWarnings("unused")
    private CurrencyConversionBean callForexServiceAndGetData_Fallback(String from,String to,BigDecimal quantity) {
		
		CurrencyConversionBean currencyConversionBean=new CurrencyConversionBean();
		currencyConversionBean.setFrom("fallback_from");
		currencyConversionBean.setTo("fallback_To");
		currencyConversionBean.setPort(8080);
		currencyConversionBean.setQuantity(new BigDecimal(00000));
		currencyConversionBean.setId(0000L);
		currencyConversionBean.setConversionMultiple(new BigDecimal(000));
		currencyConversionBean.setConversionMultiple(new BigDecimal(0000));
        System.out.println("Forex Service is down!!! fallback route enabled...");
 System.out.println("CIRCUIT BREAKER ENABLED!!! No Response From Forex Service at this moment. " +
                    " Service will be back shortly - " + new Date());
        return currencyConversionBean;
    }
	
	
//	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
//	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity)
//	{
//		ExchangeValue response= proxy.retrieveExchangeValue(from, to);
//		
//		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
//		        quantity.multiply(response.getConversionMultiple()), response.getPort());
//	}

}
