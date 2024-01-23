<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
if ("edit".equals(request.getParameter("actionAddEdit"))) {
	String registerNumber = request.getParameter("registerNumber");
	request.setAttribute("registerNumber", registerNumber);
%>
<%@ include file="Menu23.jsp"%>
<%
} else {
%>
<%@ include file="Menu22.jsp"%>
<%
}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title></title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<link rel="stylesheet" href="CSScommon2.css">

<style>
label {
	color: rgb(102, 0, 102);
	font-size: 20px;
}

input[type=email] {
	box-sizing: border-box;
	height: 30px;
	width: 70%;
}

body {
	background: linear-gradient(to bottom, #33ccff 0%, #ff9999 100%);
}
</style>


</head>
<body>
	<h3 id="arrayErrors" style="color: red;"></h3>
	<div>

		<form action="student" method="get">
			<table>
				<caption></caption>
				<tr>
					<th></th>
				</tr>
				<tr>
					<td><b><label>First Name</label></b></td>
					<td><input type="text" name="firstName" id="fname"
						value="${not empty first_name ? first_name : ''}"></td>
					<td><b><label>Last Name</label></b></td>
					<td><input type="text" name="lastName" id="lname"
						value="${not empty last_name ? last_name : ''}"></td>
				</tr>

				<tr>
					<td><b><label>Register Number</label></b></td>
					<td><input type="text" name="registerNumber"
						id="registernumber"
						value="${not empty registerno ? registerno : ''}"></td>
					<td><b><label>Department Name</label></b></td>
					<td><select name="existing_department" id="departmentname">
							<option value="">select department</option>
							<c:if test="${not empty departmentList}">
								<c:forEach items="${departmentList}" var="department">
									<option value="${department.departmentName}"
										${department.departmentName == department_name ? 'selected' : ''}>
										${department.departmentName}</option>
								</c:forEach>
							</c:if>
					</select></td>
				</tr>


				<tr>
					<td><b><label>Date of Joining</label></b></td>
					<td><input style="width: 69%;" type="date"
						name="dateOfJoining" id="dateofjoining"
						value="${not empty date_of_joining ? date_of_joining : ''}"></td>
					<td><b><label>Gender </label></b></td>
					<td><select name="gender" id="gender">
							<option value="">select gender</option>
							<option value="female" ${gender == 'female' ? 'selected' : ''}>Female</option>
							<option value="male" ${gender == 'male' ? 'selected' : ''}>Male</option>
					</select></td>
				</tr>

				<tr>
					<td><b><label>Date of Birth</label></b></td>
					<td><input style="width: 69%;" type="date" name="dateOfBirth"
						id="dateofbirth"
						value="${not empty date_of_birth ? date_of_birth : ''}"></td>
					<td><b><label>Contact Number</label></b></td>
					<td><input type="text" name="contactNumber" id="contactnumber"
						value="${not empty contact_number ? contact_number : ''}"></td>
				</tr>

				<tr>
					<td><b><label>Email</label></b></td>
					<td><input type="text" name="email" id="email"
						value="${not empty email ? email : ''}"></td>
					<td><b><label>Address Line 1</label></b></td>
					<td><input type="text" name="addressLine1" id="addressline1"
						value="${not empty addressline1 ? addressline1 : ''}"></td>
				</tr>

				<tr>
					<td><b><label>Address Line 2</label></b></td>
					<td><input type="text" name="addressLine2" id="addressline2"
						value="${not empty adressline2 ? adressline2 : ''}"></td>
					<td><b><label>State</label></b></td>
					<td><input type="text" name="state" id="state"
						value="${not empty state1 ? state1 : ''}"></td>
				</tr>

				<tr>
					<td><b><label>Country</label></b></td>
					<td><input type="text" name="country" id="country"
						value="${not empty country ? country : ''}"></td>
					<td><b><label>Zip / Postal Code</label></b></td>
					<td><input type="text" name="postalCode" id="postcode"
						value="${not empty postalcode ? postalcode : ''}"></td>
				</tr>



				<tr>
					<td><input type="hidden" name="id"
						value="${param.registerNumber}"> <input type="hidden"
						name="deptname" value="${param.deptname}"> <input
						type="hidden" name="actionAddEdit" value="${param.actionAddEdit}">
						<input type="hidden" name="action" value="addEditStudent"></td>
					<td></td>
					<td></td>
					<td><input type="submit" style="width: 70%;" value="SAVE"></td>
				</tr>




			</table>
		</form>

	</div>
	<p style="color: green;">${statusstudent}</p>
	<p style="color: red;">${errorstudent}</p>
	<p style="color: red;">${erroraddstudent}</p>


	<p style="color: red;">${errordata}</p>

	<script src="ValidationAddEditPage.js"></script>



</body>
</html>



