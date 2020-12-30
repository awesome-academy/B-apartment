
package b.apartment.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import b.apartment.entity.Apartments;
import b.apartment.uploader.ImageUpload;
import b.apartment.uploader.cloudinary.CloudinaryImageUpload;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
	private String description;

	private MultipartFile picture;
	private String image;

	@NotEmpty(message = "{apartment.validation.description.locationX}")
	private String locationX;
	@NotEmpty(message = "{apartment.validation.description.locationY}")
	private String locationY;
	
	public String getLocationX() {
		return locationX;
	}
	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}
	public String getLocationY() {
		return locationY;
	}
	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public MultipartFile getPicture() {
		return picture;
	}
	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}
	@NotNull(message = "{apartment.validation.description.required}")
	private ProjectsModel project;

	private Integer provinceId;
	
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

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
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

	public boolean isAttached() {
		return StringUtils.hasText(image);
	}

	public ImageUpload getUpload() {
		ImageUpload file = new CloudinaryImageUpload();
		if (StringUtils.hasText(image)) {
			file.setStoredPath(image);
		}
		return file;
	}

	public void setUpload(ImageUpload file) {
		this.image = file.getStoredPath();
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
