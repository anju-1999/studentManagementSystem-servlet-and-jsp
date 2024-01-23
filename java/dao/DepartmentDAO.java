package student.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import student.model.Department;
import student.service.StudentService;

public class DepartmentDAO {
	private DepartmentDAO() {
		
	}

	public static List<Department> getDepartmentList() {
		List<Department> departmentList = new ArrayList<>();
		try (Connection connection = StudentService.getConnection()) {
			String departmentQuery = "SELECT department_name FROM department";
			try(PreparedStatement preparedStatement = connection.prepareStatement(departmentQuery)){
				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					Department department = new Department();
					department.setDepartmentName(resultSet.getString("department_name"));
					departmentList.add(department);
				}
			}

		} catch ( SQLException e) {
			e.printStackTrace();
		}
		return departmentList;
	}

	public static boolean isDepartmentExists(String departmentName) {
		try (Connection connection = StudentService.getConnection()) {
			String departmentQuery = "SELECT department_id FROM department WHERE department_name=?";
			try(PreparedStatement preparedStatement = connection.prepareStatement(departmentQuery)){
				preparedStatement.setString(1, departmentName);
				ResultSet resultSet = preparedStatement.executeQuery();
				return resultSet.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean insertDepartment(String departmentName) {
		try (Connection connection = StudentService.getConnection()) {
			String insertDepartmentQuery = "INSERT INTO department(department_name) VALUES(?)";
			try(PreparedStatement preparedStatement = connection.prepareStatement(insertDepartmentQuery)){
				preparedStatement.setString(1, departmentName);
				int rowsInserted = preparedStatement.executeUpdate();
				return rowsInserted > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}



	public static String getDepartmentName(int registerNumber) throws SQLException {

		String deptnameString = null;
		try (Connection connection = StudentService.getConnection()) 
		{
			String departmentQuery = "select d.department_name from department d join studentdetails s on "
					+ "d.department_id=s.department_id where s.registerno = ?";
			try(PreparedStatement preparedStatement = connection.prepareStatement(departmentQuery)){

				preparedStatement.setInt(1, registerNumber);
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) 
				{
					deptnameString= resultSet.getString("department_name");
				}
			}
		}

		return deptnameString;

	}


	public static int getDepartmentId(Connection connection, String departmentName) throws SQLException {
		String departmentQuery = "SELECT department_id FROM department WHERE department_name = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(departmentQuery)) {
			preparedStatement.setString(1, departmentName);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("department_id");
			}
		}
		return -1;
	}



}



