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
@Table(name ="transports")
public class TransportType implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	public String IDTransport;
	
	@Column(columnDefinition = "nvarchar(100)")
	public String TransportType;
	
	public float TransportFee;
	
	@OneToMany(mappedBy = "transport", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Order> order;
	
	@OneToMany(mappedBy = "transport", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Voucher> voucher;
}
