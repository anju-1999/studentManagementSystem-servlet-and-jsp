package student.model;

public class StudentData {
	
	private int registerNumber;
	private String registerNumberString;
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
	private String dateOfJoining2;
	int age;
	
	public String getRegisterNumberString() {
		return registerNumberString;
	}

	public void setRegisterNumberString(String registerNumberString) {
		this.registerNumberString = registerNumberString;
	}

	public String getDateOfJoining2() {
		return dateOfJoining2;
	}

	public void setDateOfJoining2(String dateOfJoining2) {
		this.dateOfJoining2 = dateOfJoining2;
	}

	public int getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(int registerNumber) {
		this.registerNumber = registerNumber;
	}

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
	
	public String toString() {
	    return "{\"RegisterNumber\":\"" + registerNumber + "\", \"FirstName\":\"" + firstName + "\", "
	            + "\"LastName\":\"" + lastName + "\", \"DepartmentName\":\"" + departmentName + "\", \"\":\""+registerNumber+"\"}";
	}

}
