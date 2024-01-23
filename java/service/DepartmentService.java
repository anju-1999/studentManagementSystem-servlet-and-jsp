package student.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import student.dao.DepartmentDAO;
import student.model.Department;

public class DepartmentService {

	private DepartmentService() {

	}

	public static boolean addDepartment(Department department)
	{
		if (DepartmentDAO.isDepartmentExists(department.getDepartmentName())) 
		{
			return false;
		}

		return DepartmentDAO.insertDepartment(department.getDepartmentName());
	}

	public static List<Department> getDepartmentList() 
	{
		return DepartmentDAO.getDepartmentList();
	}

	public static String getDepartmentName(int registerNumber) throws SQLException
	{
		return DepartmentDAO.getDepartmentName(registerNumber);
	}

	public static int getDepartmentId(Connection connection, String departmentName) throws SQLException
	{
		return DepartmentDAO.getDepartmentId(connection, departmentName);
	}

}

