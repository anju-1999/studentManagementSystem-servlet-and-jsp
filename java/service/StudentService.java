package student.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import student.dao.StudentDAO;
import student.model.StudentData;

public class StudentService {
	
	private StudentService() {
		
	}

	public static Connection getConnection() throws SQLException
	{
		return StudentDAO.getConnection();
	}

	public static boolean getlogin(String username1,String password1)
	{
		return StudentDAO.getlogin(username1, password1);
	}

	public static List<StudentData> getRegisterNumberList() {
		return StudentDAO.getRegisterNumberList();
	}

	public static List<StudentData> getSearchResult(StudentData studentData) {
		return StudentDAO.getSearchResult(studentData);
	}

	public static boolean isRegisterNumberExists(Connection connection, int registerNumber) throws SQLException {
		return StudentDAO.isRegisterNumberExists(connection, registerNumber);
	}

	public static void insertStudentDetails(Connection connection, StudentData studentData, int departmentId)
			throws SQLException 
	{
		String insertQuery = "INSERT INTO studentdetails (registerno, first_name, last_name, date_of_joining, gender, "
				+ "date_of_birth, age, contact_number, email, addressline1, adressline2, state1, country, postalcode, department_id) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
			preparedStatement.setInt(1, studentData.getRegisterNumber());
			preparedStatement.setString(2, studentData.getFirstName());
			preparedStatement.setString(3, studentData.getLastName());
			preparedStatement.setString(4, studentData.getDateOfJoining());
			preparedStatement.setString(5, studentData.getGender());
			preparedStatement.setString(6, studentData.getDateOfBirth());
			preparedStatement.setInt(7, studentData.getAge());
			preparedStatement.setString(8, studentData.getContactNumber());
			preparedStatement.setString(9, studentData.getEmail());
			preparedStatement.setString(10, studentData.getAddressLine1());
			preparedStatement.setString(11, studentData.getAddressLine2());
			preparedStatement.setString(12, studentData.getState());
			preparedStatement.setString(13, studentData.getCountry());
			preparedStatement.setInt(14, studentData.getPostalCode());
			preparedStatement.setInt(15, departmentId);

			preparedStatement.executeUpdate();
		}

	}

	public static void updateStudentDetails(Connection connection, StudentData studentData)
			throws SQLException 
	{
		String updateQuery = "UPDATE studentdetails SET first_name = ?, last_name = ?, date_of_joining = ?, gender = ?, "
				+ "date_of_birth = ?, age = ?, contact_number = ?, email = ?, addressline1 = ?, adressline2 = ?, state1 = ?, "
				+ "country = ?, postalcode = ? WHERE registerno = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setString(1, studentData.getFirstName());
			preparedStatement.setString(2, studentData.getLastName());
			preparedStatement.setString(3, studentData.getDateOfJoining());
			preparedStatement.setString(4, studentData.getGender());
			preparedStatement.setString(5, studentData.getDateOfBirth());
			preparedStatement.setInt(6, studentData.getAge());
			preparedStatement.setString(7, studentData.getContactNumber());
			preparedStatement.setString(8, studentData.getEmail());
			preparedStatement.setString(9, studentData.getAddressLine1());
			preparedStatement.setString(10, studentData.getAddressLine2());
			preparedStatement.setString(11, studentData.getState());
			preparedStatement.setString(12, studentData.getCountry());
			preparedStatement.setInt(13, studentData.getPostalCode());
			preparedStatement.setInt(14, studentData.getRegisterNumber());

			preparedStatement.executeUpdate();
		}
	}

	public static StudentData fetchStudentData1(int id, HttpServletRequest request) {

		StudentData studentData = new StudentData();


		try (Connection connection = StudentService.getConnection()) {
			String query = "select s.registerno,s.first_name,s.last_name,s.date_of_joining,s.gender,s.date_of_birth,"
					+ "s.contact_number,s.email,s.addressline1,s.adressline2,s.state1,s.country,s.postalcode,d.department_name,s.age from studentdetails s "
					+ "join department d on s.department_id=d.department_id where registerno = ?";
			try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
				preparedStatement.setInt(1, id);
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {

					String registerNumber = resultSet.getString("registerno");
					String firstName = resultSet.getString("first_name");
					String lastName = resultSet.getString("last_name");
					String dateOfJoining = resultSet.getString("date_of_joining");
					String gender = resultSet.getString("gender");
					String dateOfBirth = resultSet.getString("date_of_birth");
					String contactNumber = resultSet.getString("contact_number");
					String email = resultSet.getString("email");
					String addressLine1 = resultSet.getString("addressline1");
					String addressLine2 = resultSet.getString("adressline2");
					String state = resultSet.getString("state1");
					String country = resultSet.getString("country");
					int postalCode = resultSet.getInt("postalcode");
					String departmentName = resultSet.getString("department_name");
					int age = resultSet.getInt("age");


					request.setAttribute("registerno", registerNumber);
					request.setAttribute("first_name", firstName);
					request.setAttribute("last_name", lastName);
					request.setAttribute("date_of_joining", dateOfJoining);
					request.setAttribute("gender", gender);
					request.setAttribute("date_of_birth", dateOfBirth);
					request.setAttribute("contact_number", contactNumber);
					request.setAttribute("email", email);
					request.setAttribute("addressline1", addressLine1);
					request.setAttribute("adressline2", addressLine2);
					request.setAttribute("state1", state);
					request.setAttribute("country", country);
					request.setAttribute("postalcode", postalCode);
					request.setAttribute("department_name", departmentName);

					studentData.setFirstName(firstName);
					studentData.setRegisterNumberString(registerNumber);
					studentData.setRegisterNumber(Integer.parseInt(registerNumber));
					studentData.setLastName(lastName);
					studentData.setDateOfJoining(dateOfJoining);
					studentData.setDateOfBirth(dateOfBirth);
					studentData.setGender(gender);
					studentData.setContactNumber(contactNumber);
					studentData.setEmail(email);
					studentData.setAddressLine1(addressLine1);
					studentData.setAddressLine2(addressLine2);
					studentData.setState(state);
					studentData.setCountry(country);
					studentData.setPostalCode(postalCode);
					studentData.setDepartmentName(departmentName);
					studentData.setAge(age);

				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return studentData;



	}



}
