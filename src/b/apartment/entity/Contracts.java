package b.apartment.entity;

import java.io.Serializable;
import java.util.Date;

public class Contracts implements Serializable {
	private Integer id;
	private Date created_at;
	private Integer apartment_id;
	private Integer saler_id;
	private Integer customer_id;
	private Double total_money;
	private Float down_payment;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Integer getApartment_id() {
		return apartment_id;
	}
	public void setApartment_id(Integer apartment_id) {
		this.apartment_id = apartment_id;
	}
	public Integer getSaler_id() {
		return saler_id;
	}
	public void setSaler_id(Integer saler_id) {
		this.saler_id = saler_id;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public Double getTotal_money() {
		return total_money;
	}
	public void setTotal_money(Double total_money) {
		this.total_money = total_money;
	}
	public Float getDown_payment() {
		return down_payment;
	}
	public void setDown_payment(Float down_payment) {
		this.down_payment = down_payment;
	}
	
	

}
