package b.apartment.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.util.StringUtils;

import b.apartment.validator.NullOrNotBlank;

import b.apartment.validator.FieldMatch;

@FieldMatch.List({
	@FieldMatch(first = "password", second = "confirmation", message = "{user.validation.password.notmatch}") })

public class UserModel extends BaseModel {
	private Integer id;
	@NotEmpty(message = "{user.validation.name.required}")
	@Size(max = 30, message = "{user.validation.name.max}")
	private String name;
	@NotEmpty(message = "{user.validation.phone.required}")
	@Size(max = 12, message = "{user.validation.phone.max}")
	private String phone;
	@NotEmpty(message = "{user.validation.email.required}")
	@Email(message = "{pattern.email}")
	private String email;
	private Integer role;
	private Integer gender;
	private Integer age;
	@NullOrNotBlank(message = "{user.validation.password.required}")
	@Size(max = 30, min = 3, message = "{user.validation.password.length}")
	private String password;
	@NullOrNotBlank(message = "{user.validation.confirmation.required}")
	private String confirmation = null;
	
	public UserModel() {

	}

	public UserModel(Integer id) {
		this.id = id;
	}

	public UserModel(String name, String email) {
		super();
		this.name = name;
		this.email = email;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}
	
}
