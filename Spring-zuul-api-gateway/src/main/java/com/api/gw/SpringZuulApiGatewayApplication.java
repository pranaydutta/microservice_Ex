package com.api.gw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.gw.filter.ErrorFilter;
import com.api.gw.filter.PostFilter;
import com.api.gw.filter.PreFilter;
import com.api.gw.filter.RouteFilter;

@EnableZuulProxy
@SpringBootApplication
@RestController
public class SpringZuulApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringZuulApiGatewayApplication.class, args);
	}
	
	
	
	@Bean
	public PreFilter preFilter()
	{
		return new PreFilter();
	}
	
	@Bean
	public PostFilter postFilter()
	{
		return new PostFilter();
	}
	@Bean
	public ErrorFilter errorFilter()
	{
		return new ErrorFilter();
	}
	@Bean
	public RouteFilter routeFilter()
	{
		return new RouteFilter();
	}

}
