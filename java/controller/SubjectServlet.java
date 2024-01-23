package student.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import student.model.StudentData;
import student.model.Subject1;
import student.service.SubjectService;

@WebServlet("/addsubject2")

public class SubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SubjectServlet() {
		super();

	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String departmentName=request.getParameter("existing_department");
		String subjectName=request.getParameter("subject");		

		StudentData studentData = new StudentData();
		studentData.setDepartmentName(departmentName);
		Subject1 subject = new Subject1();
		subject.setSubjectName(subjectName);

		try {
			int departmentId = SubjectService.getDepartmentId(departmentName);

			if (SubjectService.insertSubject(departmentId,subject)) {
				request.setAttribute("subjectstatus", "Subject added successfully!");
				request.setAttribute("subjectname", subjectName);

				List<Subject1> subjectList = SubjectService.getSubjectList();
				HttpSession session = request.getSession();
				session.setAttribute("subjectList", subjectList);

				request.getRequestDispatcher("AddSubject2.jsp").forward(request, response);
			} else {
				request.setAttribute("subjecterror", "Error in adding subject. Subject already present in the database");
				request.getRequestDispatcher("AddSubject2.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}




	}
}




