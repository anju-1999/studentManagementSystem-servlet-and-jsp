package student.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import student.model.JqGridData;
import student.model.Score;
import student.model.StudentData;
import student.service.ScoreService;


@WebServlet("/addscore2")
public class ScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ScoreServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		
		if("scoreDetails".equals(action))
		{
		String registerNumberString=request.getParameter("registerNumber");		

		List<Score> scoreList = ScoreService.getScoreDetails(registerNumberString);

		if(scoreList.isEmpty())
		{
			request.setAttribute("errorpage", "The score data not present in the database");
			RequestDispatcher dispatcher = request.getRequestDispatcher("ScoreResultPage2.jsp");
			dispatcher.forward(request, response);
		}
		else {
		JqGridData<Score> gridDataScore = new JqGridData<>(scoreList);
		request.setAttribute("gridDataScore", gridDataScore);

		request.getRequestDispatcher("ScoreResultPage2.jsp").forward(request, response);
		}
		}
		if("addScore".equals(action))
		{
		int regiternoString = Integer.parseInt(request.getParameter("registernumber1"));
		String departmentnameString = request.getParameter("departmentname");
		String subjectnameString = request.getParameter("subjectNames");
		String scoreString = request.getParameter("score");
		int mark= Integer.parseInt(scoreString);
		
		StudentData studentData= new StudentData();
		studentData.setRegisterNumber(regiternoString);
		studentData.setDepartmentName(departmentnameString);
		Score score = new Score();
		score.setSubjectName(subjectnameString);
		score.setMark(mark);
		
		try {
			if(ScoreService.addScore(studentData,score))
			{
				request.setAttribute("scorestatus", "Score added successfully!");
				request.getRequestDispatcher("AddScorePage2.jsp").forward(request, response);
			}
			else {
				request.setAttribute("scoreerror", "Failed to add score. Score already present in the database.");
				request.getRequestDispatcher("AddScorePage2.jsp").forward(request, response);
			}
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	}

}








