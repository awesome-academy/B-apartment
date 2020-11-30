package b.apartment.model;

import b.apartment.model.BaseModel;

public class FavouriteModel extends BaseModel {
	private Integer id;
	private Integer userId;
	private Integer ApartmentId;
	
	
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
		return ApartmentId;
	}
	public void setApartmentId(Integer apartmentId) {
		ApartmentId = apartmentId;
	}
	
	

}
