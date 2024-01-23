package student.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import student.model.StudentData;
import student.service.StudentService;

public class StudentDAO {

	private StudentDAO() {

	}

	private static final String JDBC_URL = "jdbc:sqlserver://ANJU-PC:1433;databaseName=Student;trustServerCertificate=true;";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = System.getenv("sa@123");

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	}

	public static List<StudentData> getRegisterNumberList()
	{
		List<StudentData> registernoList = new ArrayList<>();
		try(Connection connection = StudentService.getConnection())
		{
			String registernoQuery= "SELECT registerno FROM studentdetails";
			try (PreparedStatement preparedStatement = connection.prepareStatement(registernoQuery)) {
				ResultSet resultSet=preparedStatement.executeQuery();

				while(resultSet.next())
				{
					StudentData studentData = new StudentData();
					studentData.setRegisterNumberString(resultSet.getString("registerno"));
					registernoList.add(studentData);
				}

			}
		}
		catch ( SQLException e) {
			e.printStackTrace();
		}
		return registernoList; 
	}

	public static boolean isRegisterNumberExists(Connection connection, int registerNumber) throws SQLException {
		String checkQuery = "SELECT COUNT(*) FROM studentdetails WHERE registerno = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(checkQuery)) {
			preparedStatement.setInt(1, registerNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				return count > 0;
			}
		}
		return false;
	}

	public static List<StudentData> getSearchResult(StudentData studentData) 
	{
		List<StudentData> resultList = new ArrayList<>();

		try (Connection connection = StudentService.getConnection()) 
		{
			String query = buildQuery(studentData);

			PreparedStatement preparedStatement = connection.prepareStatement(query);

			setParametersToQuery(preparedStatement,studentData);

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				StudentData studentData2 = new StudentData();
				studentData2.setRegisterNumber(Integer.parseInt(resultSet.getString("registerno")));
				studentData2.setFirstName(resultSet.getString("first_name"));
				studentData2.setLastName(resultSet.getString("last_name"));
				studentData2.setDepartmentName(resultSet.getString("department_name"));
				resultList.add(studentData2);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return resultList;
	}

	private static String buildQuery(StudentData studentData) 
	{
		StringBuilder stringBuilder = new StringBuilder("SELECT s.registerno, s.first_name, s.last_name, "
				+ "d.department_name FROM studentdetails s " +
				"JOIN department d ON d.department_id = s.department_id");

		List<String> conditionsList = new ArrayList<>();
		if (!studentData.getFirstName().isEmpty()) {
			conditionsList.add("s.first_name LIKE ?");
		}
		if (!studentData.getLastName().isEmpty()) {
			conditionsList.add("s.last_name LIKE ?");
		}
		if (!studentData.getState().isEmpty()) {
			conditionsList.add("s.state1 LIKE ?");
		}
		if ((studentData.getRegisterNumber() != 0)) {
			conditionsList.add("s.registerno LIKE ?");
		}
		if (!studentData.getDepartmentName().equals("s")) {
			conditionsList.add("d.department_name LIKE ?");
		}
		if (!studentData.getGender().equals("s")) {
			conditionsList.add("s.gender = ?");
		}
		if (!studentData.getDateOfJoining().isEmpty() && !studentData.getDateOfJoining2().isEmpty()) {
			conditionsList.add("s.date_of_joining BETWEEN ? AND ?");
		}
		if ((studentData.getAge()!=0)) {
			conditionsList.add("s.age LIKE ?");
		}

		if (!conditionsList.isEmpty()) 
		{
			stringBuilder.append(" WHERE ");
			for (int i = 0; i < conditionsList.size(); i++)
			{
				if (i > 0) 
				{
					stringBuilder.append(" AND ");
				}
				stringBuilder.append(conditionsList.get(i));
			}
		}
		return stringBuilder.toString();

	}

	private static void setParametersToQuery(PreparedStatement preparedStatement, StudentData studentData) throws SQLException 
	{
		int paramIndex = 1;
		if (!studentData.getFirstName().isEmpty()) {
			preparedStatement.setString(paramIndex++, studentData.getFirstName() + "%");
		}
		if ((studentData.getRegisterNumber() !=0) ) {
			preparedStatement.setString(paramIndex++, studentData.getRegisterNumber() + "%");
		}
		if (!studentData.getDepartmentName().equals("s")) {
			preparedStatement.setString(paramIndex++, studentData.getDepartmentName() + "%");
		}
		if (!studentData.getGender().equals("s")) {
			preparedStatement.setString(paramIndex++, studentData.getGender());
		}
		if (!studentData.getDateOfJoining().isEmpty() && !studentData.getDateOfJoining2().isEmpty()) {
			preparedStatement.setString(paramIndex++, studentData.getDateOfJoining());
			preparedStatement.setString(paramIndex++, studentData.getDateOfJoining2());
		}
		if (!studentData.getLastName().isEmpty()) {
			preparedStatement.setString(paramIndex++, studentData.getLastName() + "%");
		}
		if (!studentData.getState().isEmpty()) {
			preparedStatement.setString(paramIndex++, studentData.getState() + "%");
		}
		if ((studentData.getAge() != 0)) {
			preparedStatement.setLong(paramIndex, studentData.getAge());
		}
	}

	public static boolean getlogin(String username1,String password1)
	{
		boolean flagBoolean=false;

		if (username1 == null || password1 == null) {
			return false;
		}

		try (Connection connection = StudentService.getConnection()) {
			String query = "SELECT username, password FROM login WHERE username = ? AND password = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, username1);
				preparedStatement.setString(2, password1);

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						flagBoolean = true;
					}
				}
			}
		} catch (SQLException  e1) {
			e1.printStackTrace();
		}
		return flagBoolean;

	}






}
