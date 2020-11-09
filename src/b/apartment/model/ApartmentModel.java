package b.apartment.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import b.apartment.entity.Apartments;

public class ApartmentModel extends BaseModel {
	private Integer id;
	@NotEmpty(message = "{apartment.validation.name.required}")
	@Size(max = 100, message = "{apartment.validation.name.max}")
	private String name;
	@NotNull(message = "{apartment.validation.floor.required}")
	private Integer floor;
	@NotNull(message = "{apartment.validation.bedrooms.required}")
	private Integer bedrooms;
	@NotNull(message = "{apartment.validation.bathrooms.required}")
	private Integer bathrooms;
	@NotNull(message = "{apartment.validation.cost.required}")
	private Double cost;
	@NotNull(message = "{apartment.validation.area.required}")
	private Float area;
	@NotNull(message = "{apartment.validation.project.required}")
	private Integer project_id;
	private Integer user_id;
	@NotEmpty(message = "{apartment.validation.description.required}")
	private String description;
	
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

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
