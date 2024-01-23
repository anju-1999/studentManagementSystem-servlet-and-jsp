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
import student.model.Department;
import student.service.DepartmentService;
import student.service.SubjectService;


@WebServlet("/adddepartment2")
public class DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");


		if ("addDepartment".equals(action))
		{
			String departmentName = request.getParameter("department_name");
			Department department = new Department();
			department.setDepartmentName(departmentName);

			boolean departmentAdded = DepartmentService.addDepartment(department);
			if (departmentAdded) {
				request.setAttribute("status", "Department added successfully!");
			} else {
				request.setAttribute("error", "Department already exists or failed to add department.");
			}

			List<Department> departmentList = DepartmentService.getDepartmentList();
			HttpSession session = request.getSession();
			session.setAttribute("departmentList", departmentList);

			request.getRequestDispatcher("AddDepartment2.jsp").forward(request, response);
		}

		if ("getDepartment".equals(action)) {
			String registerNumberString = request.getParameter("registerNumbers");
			if ("".equals(registerNumberString)) {
				request.getRequestDispatcher("AddScorePage2.jsp").forward(request, response);
			} else {
				int registerNo = Integer.parseInt(registerNumberString);

				try {
					String departmentNameString = DepartmentService.getDepartmentName(registerNo);
					request.setAttribute("departmentNameString", departmentNameString);
					request.setAttribute("registerNoString", registerNo);

					List<String> subjectList = SubjectService.getStudentDepartmentSubjectList(departmentNameString);
					request.setAttribute("departmentSubject", subjectList);


					request.getRequestDispatcher("AddScorePage2.jsp").forward(request, response);
				} catch ( SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
}





