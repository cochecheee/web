package com.example.project.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="goods")
public class GoodsType implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	public String IDGoods;
	
	@Column(columnDefinition = "nvarchar(100)")
	public String GoodsType;
	
	public float GoodsFee;
	
	//one to one với bảng order
	@OneToMany(mappedBy = "good", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Order> order;
	
	@OneToMany(mappedBy = "goods", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Voucher> vouchers;
}
