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
@Table(name ="postoffice")
public class PostOffice implements Serializable  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	public String IDPost; 
	
	@Column(columnDefinition = "nvarchar(100)")
	public String namePost;
	
	// một post có nhiều shipper
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Shipper> shippers;
	
	@OneToMany(mappedBy = "postOffice", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Order> orders;
	
	@OneToOne(mappedBy = "post", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Manager manager; 
}