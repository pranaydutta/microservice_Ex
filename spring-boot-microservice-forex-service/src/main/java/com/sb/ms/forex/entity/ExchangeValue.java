package com.sb.ms.forex.entity;

import java.math.BigDecimal;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "EXCHANGE_VALUE")
public class ExchangeValue {
	
	@Id
	  private Long id;
	  
	  @Column(name="currency_from")
	  private String from;
	  
	  @Column(name="currency_to")
	  private String to;
	  
	  private BigDecimal conversionMultiple;
	  private int port;

}
