package student.service;

import java.sql.SQLException;
import java.util.List;

import student.dao.ScoreDAO;
import student.model.Score;
import student.model.StudentData;

public class ScoreService {

	private ScoreService() {

	}

	public static boolean addScore(StudentData studentData, Score score) throws SQLException
	{
		return ScoreDAO.addScore(studentData, score);
	}
	public static List<Score> getScoreDetails(String registerNumber)
	{
		return ScoreDAO.getScoreDetails(registerNumber);
	}
}
