package com.example.project.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerRequestDTO {
	private String IDUser;
	private String cccd;
	private String phone;
	private String name;
	private Boolean gender;
	private Date birth;
	
	private String address;
	private String city;
	private String picture;
	private String pictureUpload;
}
