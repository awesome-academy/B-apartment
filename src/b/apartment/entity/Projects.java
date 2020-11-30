package b.apartment.entity;

import java.io.Serializable;
import java.util.List;

public class Projects implements Serializable {
	private Integer id;
	private String name;
	private String location;
	private Integer province;
	private Integer district;
	private Integer wards;
	private String desctiption;
	private String images;
	
	private List<Apartments> apartments;
	
	public List<Apartments> getApartments() {
		return apartments;
	}
	public void setApartments(List<Apartments> apartments) {
		this.apartments = apartments;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Integer getDistrict() {
		return district;
	}
	public void setDistrict(Integer district) {
		this.district = district;
	}
	public Integer getWards() {
		return wards;
	}
	public void setWards(Integer wards) {
		this.wards = wards;
	}
	public String getDesctiption() {
		return desctiption;
	}
	public void setDesctiption(String desctiption) {
		this.desctiption = desctiption;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	
	

}
