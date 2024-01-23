package student.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import student.model.Score;
import student.model.StudentData;
import student.service.DepartmentService;
import student.service.StudentService;

public class ScoreDAO {

	private ScoreDAO() {

	}

	public static  boolean addScore(StudentData studentData, Score score) throws SQLException {

		try (Connection connection = StudentService.getConnection()) {
			String departmentnameString = studentData.getDepartmentName();
			int departmentId = DepartmentService.getDepartmentId(connection, departmentnameString);

			String selectQueryString = "SELECT * FROM scoredetails WHERE registerno = ? AND department_id = ? AND subjectname = ?";
			try(PreparedStatement selectStatement = connection.prepareStatement(selectQueryString)){

				selectStatement.setLong(1, studentData.getRegisterNumber());
				selectStatement.setInt(2, departmentId);
				selectStatement.setString(3, score.getSubjectName());
				ResultSet resultSet = selectStatement.executeQuery();

				if (resultSet.next()) {
					return false;
				} else {
					String insertQueryString = "INSERT INTO scoredetails VALUES (?, ?, ?, ?)";
					try(PreparedStatement insertStatement = connection.prepareStatement(insertQueryString)){

						insertStatement.setLong(1, studentData.getRegisterNumber());
						insertStatement.setInt(2, departmentId);
						insertStatement.setString(3, score.getSubjectName());
						insertStatement.setDouble(4, score.getMark());

						return insertStatement.executeUpdate()>0;
					}
				}
			}
		}

	}

	public static List<Score> getScoreDetails(String registerNumber)
	{
		List<Score> scoreList = new ArrayList<>();
		try(Connection connection = StudentService.getConnection())
		{
			String displayScoreQueryString="select subjectname,mark from scoredetails where registerno=?";
			try(PreparedStatement preparedStatement = connection.prepareStatement(displayScoreQueryString)){

				preparedStatement.setString(1, registerNumber);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) 
				{
					Score score=new Score();
					score.setSubjectName(resultSet.getString("subjectname"));
					score.setMark(resultSet.getInt("mark"));
					scoreList.add(score);
				}

			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return scoreList; 
	}


}
