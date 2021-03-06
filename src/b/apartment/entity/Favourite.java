package b.apartment.entity;

import java.io.Serializable;

public class Favourite implements Serializable {
	private Integer id;
	private Integer userId;
	private Integer apartmentId;
	private Apartments apartment;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getApartmentId() {
		return apartmentId;
	}
	public void setApartmentId(Integer apartmentId) {
		this.apartmentId = apartmentId;
	}
	public Apartments getApartment() {
		return apartment;
	}
	public void setApartment(Apartments apartment) {
		this.apartment = apartment;
	}
	
}
