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
@Table(name ="vouchers")
public class Voucher implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	public String IDVoucher;
	
	public String vouchername;
	
	@Column(columnDefinition = "nvarchar(500)")
	public String description;
	
	
	public Date dayStart;
	public Date dayEnd;
	
	public int amount;
	
	@OneToMany(mappedBy = "voucher", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Order> orders;
	
	//IDTransport anh xa bang Transport
	@ManyToOne
	@JoinColumn(name="IDTransport")
	private TransportType transport;
	//IDGood anh xa toi Goods
	@ManyToOne
	@JoinColumn(name="IDGoods")
	private GoodsType goods;
	
	//IDManager anh xa toi Manager
	@ManyToOne
	@JoinColumn(name="IDManager")
	private Manager manager;
}
