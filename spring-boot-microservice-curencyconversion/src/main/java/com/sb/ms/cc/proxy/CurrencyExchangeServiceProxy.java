//package com.sb.ms.cc.proxy;
//
//import org.springframework.cloud.netflix.ribbon.RibbonClient;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import com.sb.ms.cc.model.CurrencyConversionBean;
//import com.sb.ms.cc.model.ExchangeValue;
//
////@FeignClient(name = "forex-service",url="localhost:8004")
//
//@FeignClient(name = "forex-service")
//@RibbonClient(name = "forex-service")
//public interface CurrencyExchangeServiceProxy {
//
//
//	@GetMapping("/currency-exchange/from/{from}/to/{to}")
//	public ExchangeValue retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
//
//
//}
