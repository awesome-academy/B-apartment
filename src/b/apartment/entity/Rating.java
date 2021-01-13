package b.apartment.entity;

import java.io.Serializable;

public class Rating implements Serializable {
	private Integer id;
	private Integer apartmentId;
	private Integer userId;
	private Integer scores;
	private Apartments apartment;
	private Users user;
	
	
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
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
	public Apartments getApartment() {
		return apartment;
	}
	public void setApartment(Apartments apartment) {
		this.apartment = apartment;
	}
}
