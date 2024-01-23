package student.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterConfiguration;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import student.model.Department;
import student.model.JqGridData;
import student.model.Score;
import student.model.StudentData;
import student.model.StudentMainReport;
import student.model.Subject1;
import student.service.DepartmentService;
import student.service.ScoreService;
import student.service.StudentService;
import student.service.SubjectService;


@WebServlet("/student")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(StudentServlet.class);
	private static final String CONST_REGISTER_NUMBER = "registerNumber";
	private static final String CONST_ERROR_STUDENT = "errorstudent";
	private static final String CONST_ERROR_ADD_STUDENT = "erroraddstudent";
	private static final String CONST_CONTENT_DISPOSITION = "Content-Disposition";
	private static final String CONST_ADD_EDIT_PAGE = "AddEditPage2.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		if("login".equals(action))
		{
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			boolean loginFlag=StudentService.getlogin(username, password);

			checkLogin(loginFlag, username, request, response);
		}

		if("searchStudentDetails".equals(action))
		{
			String firstName=request.getParameter("firstName");
			String lastName=request.getParameter("lastName");

			String registerNumberParameter = request.getParameter(CONST_REGISTER_NUMBER);
			int registerNumber = returnIntByCheckValueNullEmpty(registerNumberParameter);

			String dateOfJoining=request.getParameter("dateOfJoining");
			String dateOfJoining2=request.getParameter("dateOfJoining2");
			String gender=request.getParameter("gender");

			String ageParameter = request.getParameter("age1");
			int age  = returnIntByCheckValueNullEmpty(ageParameter);

			String departmentName=request.getParameter("existing_department");
			String state=request.getParameter("state");

			StudentData studentData = new StudentData();
			studentData.setFirstName(firstName);
			studentData.setLastName(lastName);
			studentData.setRegisterNumber(registerNumber);
			studentData.setDateOfJoining(dateOfJoining);
			studentData.setDateOfJoining2(dateOfJoining2);
			studentData.setGender(gender);
			studentData.setAge(age);
			studentData.setDepartmentName(departmentName);
			studentData.setState(state);


			List<StudentData> resultList=StudentService.getSearchResult(studentData);

			request.setAttribute("searchResult", studentData);

			if(resultList.isEmpty())
			{
				request.setAttribute("errorpage", "The searched data not available in the database");
				RequestDispatcher dispatcher = request.getRequestDispatcher("ResultPage2.jsp");
				dispatcher.forward(request, response);
			}
			else {

				JqGridData<StudentData> gridData = new JqGridData<>(resultList);
				request.setAttribute("gridData", gridData);

				RequestDispatcher dispatcher = request.getRequestDispatcher("ResultPage2.jsp");
				dispatcher.forward(request, response);
			}
		}

		if("report".equals(action))
		{
			String regnoString = request.getParameter("regno");
			int regno = Integer.parseInt(regnoString);
			String type= request.getParameter("reportType");

			JasperPrint jasperPrint = compileAndFillReport(regno, request, regnoString);

			try {
				exportReport(type, jasperPrint, response);
			}catch (Exception e) {
				LOG.error("Error in export report ", e );
			}

		}

		if("studentDataDisplay".equals(action))
		{
			int id =Integer.parseInt(request.getParameter(CONST_REGISTER_NUMBER));
			request.setAttribute("action1", "edit");

			StudentService.fetchStudentData1(id, request);

			request.getRequestDispatcher(CONST_ADD_EDIT_PAGE).forward(request, response);
		}

		if("logout".equals(action))
		{
			HttpSession session = request.getSession(false);
			logout(session, response);
		}

		if("addEditStudent".equals(action))
		{
			HttpSession session=request.getSession(false);

			String actionAddEdit = request.getParameter("actionAddEdit");

			String  firstName = request.getParameter("firstName");
			String	lastName = request.getParameter("lastName");
			String	registerNo =request.getParameter(CONST_REGISTER_NUMBER);
			String	departmentName = request.getParameter("existing_department");
			String	dateOfJoining = request.getParameter("dateOfJoining");
			String	gender = request.getParameter("gender");
			String	dateOfBirth = request.getParameter("dateOfBirth");
			String	contactNumber = request.getParameter("contactNumber");
			String	addressLine1 = request.getParameter("addressLine1");
			String	addressLine2 = request.getParameter("addressLine2");
			String	state = request.getParameter("state");
			String	country = request.getParameter("country");
			String	postalCode1 =request.getParameter("postalCode");
			String  email = request.getParameter("email");

			int registerNumber=Integer.parseInt(registerNo);
			int postalCode=Integer.parseInt(postalCode1);

			LocalDate birthDate = LocalDate.parse(dateOfBirth);
			int age = Period.between(birthDate, LocalDate.now()).getYears();

			StudentData studentData = new StudentData();
			studentData.setFirstName(firstName);
			studentData.setRegisterNumber(registerNumber);
			studentData.setLastName(lastName);
			studentData.setDateOfJoining(dateOfJoining);
			studentData.setDateOfBirth(dateOfBirth);
			studentData.setGender(gender);
			studentData.setContactNumber(contactNumber);
			studentData.setEmail(email);
			studentData.setAddressLine1(addressLine1);
			studentData.setAddressLine2(addressLine2);
			studentData.setState(state);
			studentData.setCountry(country);
			studentData.setPostalCode(postalCode);
			studentData.setDepartmentName(departmentName);
			studentData.setAge(age);

			try {
				addEditStudent(actionAddEdit, studentData, registerNumber, departmentName, request, response, session);
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute(CONST_ERROR_STUDENT, "Failed to process student data.");
				request.getRequestDispatcher(CONST_ADD_EDIT_PAGE).include(request, response);
			}
		}
	}	

	private static void addStudentData(HttpServletRequest request, HttpServletResponse response, StudentData studentData)
			throws SQLException, IOException, ServletException 
	{
		try (Connection connection = StudentService.getConnection()) 
		{
			String departmentName = studentData.getDepartmentName();
			int departmentId = DepartmentService.getDepartmentId(connection, departmentName);

			if (departmentId != -1) {
				if (!StudentService.isRegisterNumberExists(connection, studentData.getRegisterNumber())) 
				{
					StudentService.insertStudentDetails(connection, studentData, departmentId);
					request.setAttribute("statusstudent", "Student added successfully!");
				}
				else 
				{
					request.setAttribute(CONST_ERROR_ADD_STUDENT,
							"Error in adding student. The entered register number already exists in the database.");
				}
			} else {
				request.setAttribute(CONST_ERROR_ADD_STUDENT, "Error in adding student. Department not found in the database.");
			}

			request.getRequestDispatcher(CONST_ADD_EDIT_PAGE).include(request, response);
		}
	}

	private void editStudentData(HttpServletRequest request, HttpServletResponse response, StudentData studentData) throws SQLException, ServletException, IOException 
	{
		try (Connection connection = StudentService.getConnection()) 
		{
			String departmentName = studentData.getDepartmentName();
			int departmentId = DepartmentService.getDepartmentId(connection, departmentName);

			if (departmentId != -1) {

				StudentService.updateStudentDetails(connection, studentData);
				StudentService.fetchStudentData1(studentData.getRegisterNumber(), request);
				request.setAttribute("statusstudent", "Student data updated successfully!");

			} else {
				request.setAttribute(CONST_ERROR_ADD_STUDENT, "Error in adding student. Department not found in the database.");
			}

			request.getRequestDispatcher(CONST_ADD_EDIT_PAGE).include(request, response);
		}
	}


	private JasperPrint compileAndFillReport(int registerNumber, HttpServletRequest request, String registerNumberStrng) {
		try {

			ServletContext context = getServletContext();

			String mainReportPath = "//WEB-INF//ireport//studentReport.jrxml";
			String originalMainReportPath = context.getRealPath(mainReportPath);
			String subReportPath = "//WEB-INF//ireport//studentMarkReport.jrxml";
			String originalSubReportPath = context.getRealPath(subReportPath);

			JasperReport report = JasperCompileManager.compileReport(originalMainReportPath);
			JasperCompileManager.compileReportToFile(originalSubReportPath);

			StudentData studentData = StudentService.fetchStudentData1(registerNumber, request);	
			StudentMainReport view= new StudentMainReport();
			view.setFirstName(studentData.getFirstName());
			view.setRegisterNumber(studentData.getRegisterNumber());
			view.setLastName(studentData.getLastName());
			view.setDateOfJoining(studentData.getDateOfJoining());
			view.setGender(studentData.getGender());
			view.setDateOfBirth(studentData.getDateOfBirth());
			view.setContactNumber(studentData.getContactNumber());
			view.setEmail(studentData.getEmail());
			view.setDepartmentName(studentData.getDepartmentName());
			view.setAge(studentData.getAge());
			view.setAddressLine1(studentData.getAddressLine1());
			view.setAddressLine2(studentData.getAddressLine2());
			view.setState(studentData.getState());
			view.setCountry(studentData.getCountry());
			view.setPostalCode(studentData.getPostalCode());

			List<Score> studentScoreList = ScoreService.getScoreDetails(registerNumberStrng);
			view.setStudentScoreList(studentScoreList);

			List<StudentMainReport> viewList = new ArrayList<>();
			viewList.add(view);

			Locale locale = new Locale("en");

			ResourceBundle messages = ResourceBundle.getBundle("student.login.resourceBundle.bundle", locale);

			Map<String, Object> parameters = new HashMap<>();
			parameters.put(JRParameter.REPORT_LOCALE, locale);
			parameters.put("REPORT_RESOURCE_BUNDLE", messages);

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(viewList);

			return JasperFillManager.fillReport(report, parameters,dataSource);

		}catch (Exception e) {
			LOG.error("Error in compile and fill report ", e);
		}
		return null;

	}


	private void checkLogin(boolean loginFlag, String username, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (Boolean.TRUE.equals(loginFlag)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);

			List<Department> departmentList = DepartmentService.getDepartmentList();
			session.setAttribute("departmentList", departmentList);

			List<Subject1> subjectList = SubjectService.getSubjectList();
			session.setAttribute("subjectList", subjectList);

			List<StudentData> registernoList = StudentService.getRegisterNumberList();
			session.setAttribute("registernolist", registernoList);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("SearchPage2.jsp");
			requestDispatcher.forward(request, response);
		} else {
			String error = "Invalid user name or password";
			request.setAttribute("error", error);
			request.getRequestDispatcher("LoginPage2.jsp").include(request, response);
		}
	}


	private int returnIntByCheckValueNullEmpty(String value) {
		if (value != null && !value.isEmpty()) {
			return Integer.parseInt(value);
		}
		return 0;
	}

	private void exportReport(String type, JasperPrint jasperPrint, HttpServletResponse response) throws JRException, IOException {
		if ("pdf".equals(type)) {
			response.setContentType("application/pdf");
			response.setHeader(CONST_CONTENT_DISPOSITION, "attachment; filename=\"student_report.pdf\"");

			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
		} else if ("excel".equals(type)) {
			response.setContentType("application/vnd.ms-excel"); 
			response.setHeader(CONST_CONTENT_DISPOSITION, "attachment; filename=\"student_report.xls\""); 
			JRXlsExporter xlsExporter = new JRXlsExporter();
			xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

			xlsExporter.exportReport();

		} else if ("html".equals(type)) {
			response.setContentType("text/html");
			response.setHeader(CONST_CONTENT_DISPOSITION, "attachment; filename=\"student_report.html\"");

			SimpleHtmlExporterConfiguration exporterConfig = new SimpleHtmlExporterConfiguration();
			exporterConfig.setHtmlHeader("<html><head><title>Student Report</title></head><body>");
			exporterConfig.setHtmlFooter("</body></html>");
			HtmlExporter exporter = new HtmlExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getOutputStream()));
			exporter.setConfiguration(exporterConfig);
			exporter.exportReport();
		} 
	}

	private void logout(HttpSession session,HttpServletResponse response) throws IOException {
		if (session != null) {
			session.invalidate();
		}
		response.sendRedirect("LoginPage2.jsp");
	}
	
	private void addEditStudent(String action,StudentData studentData,int registerNumber,String departmentName, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws SQLException, IOException, ServletException {
		if ("add".equals(action)) 
		{
			addStudentData(request, response, studentData);
			List<StudentData> registernoList = StudentService.getRegisterNumberList();
			session.setAttribute("registernolist", registernoList);
		} 
		else if ("edit".equals(action)) 
		{
			int registerNoId=Integer.parseInt(request.getParameter("id"));
			String deptString=DepartmentService.getDepartmentName(registerNoId);

			if(registerNoId==registerNumber &&  departmentName.equals(deptString))
			{
				editStudentData(request, response,studentData);
			}else {
				StudentService.fetchStudentData1(registerNoId, request);
				request.setAttribute(CONST_ERROR_STUDENT, "Register Number and department name cannot be changed");
				request.getRequestDispatcher(CONST_ADD_EDIT_PAGE).forward(request, response);
			}


		} else 
		{
			request.setAttribute(CONST_ERROR_STUDENT, "Invalid action.");
			request.getRequestDispatcher(CONST_ADD_EDIT_PAGE).include(request, response);
		}
	
	}

}















