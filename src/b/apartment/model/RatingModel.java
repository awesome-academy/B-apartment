package b.apartment.model;

import b.apartment.model.BaseModel;

public class RatingModel extends BaseModel {
	private Integer id;
	private Integer apartmentId;
	private Integer userId;
	private Integer scores;
	private ApartmentModel apartmentModel;
	private UserModel userModel;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getApartmentId() {
		return apartmentId;
	}
	public void setApartmentId(Integer apartmentId) {
		this.apartmentId = apartmentId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getScores() {
		return scores;
	}
	public void setScores(Integer scores) {
		this.scores = scores;
	}
	public ApartmentModel getApartmentModel() {
		return apartmentModel;
	}
	public void setApartmentModel(ApartmentModel apartmentModel) {
		this.apartmentModel = apartmentModel;
	}
	public UserModel getUserModel() {
		return userModel;
	}
	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
}
