package com.example.project.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="managers")
public class Manager implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	public String IDManager;
	@Column(columnDefinition = "nvarchar(50) not null")
	public String username;
	@Column(columnDefinition = "nvarchar(50) not null")
	public String password;
	@Column(columnDefinition = "nvarchar(10) not null")
	public String phone;
	
	private String picture;
	
	
	@OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Voucher> vouchers;
	
	@OneToOne
	@JoinColumn(name="IDPost")
	private PostOffice post;
	
	@OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Shipper> shippers;
	
	@OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Customer> users;
}
