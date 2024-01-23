package student.service;

import java.sql.SQLException;
import java.util.List;

import student.dao.SubjectDAO;
import student.model.Subject1;

public class SubjectService {

	private SubjectService() {

	}

	public static int getDepartmentId(String departmentName) throws SQLException {
		return SubjectDAO.getDepartmentId(departmentName);
	}

	public static boolean insertSubject(int departmentId,Subject1 subject) throws SQLException {
		return SubjectDAO.insertSubject(departmentId, subject);
	}

	public static List<Subject1> getSubjectList() {
		return SubjectDAO.getSubjectList();
	}

	public static List<String> getStudentDepartmentSubjectList(String departmentname)
	{

		return SubjectDAO.getStudentDepartmentSubjectList(departmentname); 
	}
}
