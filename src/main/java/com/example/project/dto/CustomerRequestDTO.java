package com.example.project.dto;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birth;
	private String email;
	private String username;
	private String password;
	
	private String address;
	private String city;
	private String picture;
	private String pictureUpload;
}
