<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="Menu22.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Add subject page</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<link rel="stylesheet" href="CSScommon2.css">

<style>
label {
	color: white;
	font-size: 20px;
}

body {
	background-image: url("picture31.jpg");
	background-repeat: no-repeat;
	background-size: cover;
}
</style>

</head>
<body>
	<h3></h3>
	<div>
		<form action="addsubject2" method="post">

			<table>
				<caption></caption>
				<tr>
					<th></th>
				</tr>
				<tr>
					<td><b><label>Department Name</label></b></td>
					<td><select name="existing_department" id="departmentname">
							<option value="">select department</option>
							<c:if test="${not empty departmentList}">
								<c:forEach items="${departmentList}" var="department">
									<option value="${department.departmentName}">${department.departmentName}</option>
								</c:forEach> 
                         <  </c:if>
					</select>
				</tr>

				<tr>
					<td><b><label>Subject Name</label></b></td>
					<td><input type="text" name="subject" id="subjectname"></td>
				</tr>


				<tr>
					<td></td>
					<td><input type="submit" Value="ADD SUBJECT"
						style="width: 70%;"></td>
				</tr>


			</table>

		</form>
	</div>
	<p style="color: green;">${subjectstatus}</p>
	<p style="color: red;">${subjecterror}</p>


	<script src="ValidationAddSubjectPage.js"></script>


</body>
</html>

