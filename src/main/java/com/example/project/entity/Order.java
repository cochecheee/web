package com.example.project.entity;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="orders")
public class Order implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	public String IDOrder;
	
	public Integer shipFee;
	public Integer codFee;
	
	@Column(columnDefinition = "nvarchar(100)")
	public String source;
	
	@Column(columnDefinition = "nvarchar(100)")
	public String sourceCity;
	
	@Column(columnDefinition = "nvarchar(100)")
	public String dest;
	
	@Column(columnDefinition = "nvarchar(100)")
	public String destCity;
	
	public Integer width;
	public Integer height;
	public Integer weigth;
	
	public Date orderDate;
	
	//thêm date time các thứ
	private Integer status;
	/// Thuoc tinh anh xa
		//IDTransport anh xa voi Transport
		//IDGoods anh xa voi Goods
		//IDVoucher anh xa voi Voucher
		//IDShipper anh xa voi Shipper
		//IDUser anh xa voi User
		//IDPost anh xa voi User
	
	@ManyToOne
    @JoinColumn(name = "IDGoods") //tên column tham chiếu trong database
	private GoodsType good;
	
	@ManyToOne
	@JoinColumn(name="IDTransport")
	private TransportType transport;
	
	@ManyToOne
	@JoinColumn(name="IDUser")
	private Customer user;
	
	@ManyToOne
	@JoinColumn(name="IDVoucher")
	private Voucher voucher;
	
	@ManyToOne
	@JoinColumn(name="IDShipper")
	private Shipper shipper;
	
	@ManyToOne
	@JoinColumn(name="IDPost")
	private PostOffice postOffice;
}
