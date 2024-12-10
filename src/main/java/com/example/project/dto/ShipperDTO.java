package com.example.project.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipperDTO {
	private String name;
    private String cccd;
    private String phone;
    private Boolean gender;
    //private Date birth;
    private String address;
    private String city;
    // private String picture; // Hình ảnh có thể được lưu trữ dưới dạng URL hoặc tên tệp.
    private String username;
    private String password;
    private Boolean statusShipper;
    private String postId;   // ID của PostOffice
    private String managerId; // ID của Manager
}
