package b.apartment.model;

import b.apartment.entity.Apartments;

public class ApartmentModel extends BaseModel {
	private Integer id;
	private String name;
	private Integer floor;
	private Integer bedrooms;
	private Integer bathrooms;
	private Double cost;
	private Float area;
	private Integer project_id;
	private Integer user_id;
	
	private ProjectsModel project;
	
	public ProjectsModel getProject() {
		return project;
	}
	public void setProject(ProjectsModel project) {
		this.project = project;
	}
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
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
	public Integer getFloor() {
		return floor;
	}
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	public Integer getBedrooms() {
		return bedrooms;
	}
	public void setBedrooms(Integer bedrooms) {
		this.bedrooms = bedrooms;
	}
	public Integer getBathrooms() {
		return bathrooms;
	}
	public void setBathrooms(Integer bathrooms) {
		this.bathrooms = bathrooms;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Float getArea() {
		return area;
	}
	public void setArea(Float area) {
		this.area = area;
	}
	public Integer getProject_id() {
		return project_id;
	}
	public void setProject_id(Integer project_id) {
		this.project_id = project_id;
	}
	
	public static ApartmentModel build(Apartments apartment) {
		ApartmentModel apartmentModel = new ApartmentModel();
		apartmentModel.setId(apartment.getId());
		apartmentModel.setName(apartment.getName());
		apartmentModel.setFloor(apartment.getFloor());
		apartmentModel.setBedrooms(apartment.getBedrooms());
		apartmentModel.setBathrooms(apartment.getBathrooms());
		apartmentModel.setCost(apartment.getCost());
		apartmentModel.setArea(apartment.getArea());
		apartmentModel.setProject_id(apartment.getProject_id());
		apartmentModel.setUser_id(apartment.getUser_id());
		return apartmentModel;
	}

}
