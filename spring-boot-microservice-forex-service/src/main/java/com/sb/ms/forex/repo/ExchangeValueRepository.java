package com.sb.ms.forex.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sb.ms.forex.entity.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long>{
	
	ExchangeValue findByFromAndTo(String from,String to);

}
