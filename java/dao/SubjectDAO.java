package student.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import student.model.Subject1;
import student.service.DepartmentService;
import student.service.StudentService;

public class SubjectDAO {

	private SubjectDAO() {

	}

	public static int getDepartmentId(String departmentName) throws SQLException 
	{
		try (Connection connection = StudentService.getConnection())
		{
			return DepartmentService.getDepartmentId(connection, departmentName); 
		}
	}

	public static boolean insertSubject(int departmentId, Subject1 subject) throws SQLException {
		try (Connection connection = StudentService.getConnection()) 
		{
			String subjectString="select subjectname from subjects where subjectname=? and department_id=?";
			try(PreparedStatement preparedStatement1 = connection.prepareStatement(subjectString)){
				preparedStatement1.setString(1, subject.getSubjectName());
				preparedStatement1.setInt(2, departmentId);
				ResultSet resultSet1=preparedStatement1.executeQuery();
				if(resultSet1.next())
				{
					return false;
				}
				else
				{
					String insertSubjectQuery = "INSERT INTO subjects VALUES(?,?)";
					try(PreparedStatement preparedStatement2 = connection.prepareStatement(insertSubjectQuery)){
						preparedStatement2.setInt(1, departmentId);
						preparedStatement2.setString(2, subject.getSubjectName());
						return preparedStatement2.executeUpdate() > 0;
					}
				}
			}
		}
	}

	public static List<Subject1> getSubjectList()
	{
		List<Subject1> subjectList = new ArrayList<>();
		try(Connection connection = StudentService.getConnection())
		{
			String subjectQuery= "select subjectname from subjects";
			try(PreparedStatement preparedStatement=connection.prepareStatement(subjectQuery)){
				ResultSet resultSet=preparedStatement.executeQuery();

				while(resultSet.next())
				{
					Subject1 subject = new Subject1();
					subject.setSubjectName(resultSet.getString("subjectname"));
					subjectList.add(subject);
				}
			}

		}
		catch ( SQLException e) {
			e.printStackTrace();
		}
		return subjectList; 
	}

	public static List<String> getStudentDepartmentSubjectList(String departmentname)
	{
		List<String> departmentSubjectList = new ArrayList<>();
		try(Connection connection = StudentService.getConnection())
		{
			String subjectQuery= "select subjectname from subjects s join department d "
					+ "on s.department_id=d.department_id where department_name = ?";
			try(PreparedStatement preparedStatement=connection.prepareStatement(subjectQuery)){
				preparedStatement.setString(1, departmentname);
				ResultSet resultSet=preparedStatement.executeQuery();

				while(resultSet.next())
				{

					departmentSubjectList.add(resultSet.getString("subjectname"));
				}
			}

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return departmentSubjectList; 
	}


}


