package com.example.project.model;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipperModel {
		private String IDShipper;
		private String cccd;
		private String phone;
		private String name;
		private Boolean gender;
		private Date birth;
		private String address;
		private String city;
		private String picture;
		private String password;
		private Boolean statusShipper;
		
}
