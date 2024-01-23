package student.model;

import java.util.List;

public class StudentMainReport {
	private int registerNumber;
	private String firstName;
	private String lastName;
	private String dateOfJoining;
	private String gender;
	private String dateOfBirth;
	private String contactNumber;
	private String email;
	private String addressLine1;
	private String addressLine2;
	private String state;
	private String country;
	private int postalCode;
	private String departmentName;
	int age;
	private List<Score> studentScoreList;
	
	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getDateOfJoining() {
		return dateOfJoining;
	}



	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getDateOfBirth() {
		return dateOfBirth;
	}



	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}



	public String getContactNumber() {
		return contactNumber;
	}



	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getAddressLine1() {
		return addressLine1;
	}



	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}



	public String getAddressLine2() {
		return addressLine2;
	}



	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public int getPostalCode() {
		return postalCode;
	}



	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}



	public String getDepartmentName() {
		return departmentName;
	}



	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public List<Score> getStudentScoreList() {
		return studentScoreList;
	}
	public void setStudentScoreList(List<Score> studentScoreList) {
		this.studentScoreList = studentScoreList;
	}
	public int getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(int registerNumber) {
		this.registerNumber = registerNumber;
	}
	
	
}
