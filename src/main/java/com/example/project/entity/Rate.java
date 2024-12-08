package com.example.project.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="rates")
public class Rate implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	public String IDrate;
	
	//Anh xa IDShipper anh xa Shipper
	@ManyToOne
	@JoinColumn(name="IDShipper")
	private Shipper shipper;
	//Anh xa IDUser anh xa User
	@ManyToOne
	@JoinColumn(name="IDUser")
	private Customer user;
	
	public String feedback;
	public int star;
}
