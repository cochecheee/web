package com.example.project.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="shippers")
public class Shipper implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	public String IDShipper;
	public String cccd;
	public String phone;
	public String name;
	public Boolean gender;
	public Date birth;
	
	@Column(columnDefinition = "nvarchar(100)")
	public String address;
	
	@Column(columnDefinition = "nvarchar(50)")
	public String city;
	
	@Column(columnDefinition = "nvarchar(300)")
	public String picture;
	
	@Column(columnDefinition = "nvarchar(50) not null")
	public String password;
	
	public Boolean statusShipper;
	
	//Anh xa IDrate anh xa toi Rate
	@OneToMany(mappedBy = "shipper", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Rate> rates;
	
	//Anh xa IDPost toi Post
	@ManyToOne
	@JoinColumn(name="IDPost")
	private PostOffice post;
	
	@OneToMany(mappedBy = "shipper", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Order> orders;
	
	@ManyToOne
	@JoinColumn(name="IDManager")
	private Manager manager;
}
