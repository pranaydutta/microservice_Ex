package com.sb.ms.cc.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExchangeValue {
	
	  private Long id;
	  private String from;
	  private String to;
	  private BigDecimal conversionMultiple;
	  private int port;

}
