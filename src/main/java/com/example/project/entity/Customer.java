package com.example.project.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="customers")
public class Customer implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	public String IDUser;
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
	
	private String username;
	@Column(columnDefinition = "nvarchar(50) not null")
	public String password;
	
	public Boolean IsEmailActive;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Rate> rates;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Order> orders;
	
	@ManyToOne
	@JoinColumn(name="IDManager")
	private Manager manager;

}
