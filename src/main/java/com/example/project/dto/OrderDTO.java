package com.example.project.dto;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	private String orderName;
	
	private int shipFee;
	private int codFee;
	
	private String sourceCity;
	private String destCity;
	
	private int width;
	private int height;
	private int weight;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;
	
	private String IDPost;
	private String IDGoods;
	private String IDTransport;
	
	
}
