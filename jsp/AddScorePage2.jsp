<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="Menu22.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Add score page</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<link rel="stylesheet" href="CSScommon2.css">


<script>
	function showDepartment() {
		document.getElementById("departmentForm").submit();
	}
</script>

<style>
label {
	color: rgb(102, 0, 102);
	font-size: 20px;
}

body {
	background: linear-gradient(to bottom, #33ccff 0%, #ffccff 100%);
}
</style>

</head>
<body>
	<h3></h3>
	<div>
		<form action="adddepartment2" method="post" id="departmentForm">
			<table>
				<caption></caption>
				<tr>
					<th></th>
				</tr>
				<tr>
					<td><b><label>Register Number</label></b></td>
					<td><select name="registerNumbers" onchange="showDepartment()"
						required="required">
							<option value="">select register number</option>
							<c:if test="${not empty registernolist}">
								<c:forEach items="${registernolist}" var="registerno">
									<option value="${registerno.registerNumberString}"
										${registerno.registerNumberString == registerNoString ? 'selected' : ''}>${registerno.registerNumberString}</option>
								</c:forEach>
							</c:if>
					</select></td>
				</tr>
			</table>
			<input type="hidden" name="action" value="getDepartment">
		</form>
	</div>
	<div style="top: 25%">

		<form action="addscore2" method="get" id="addScoreForm">
			<table>
				<caption></caption>
				<tr>
					<th></th>
				</tr>
				<tr>
					<td><b><label>Department Name</label></b></td>
					<td><input type="text" name="departmentname"
						required="required"
						value="${not empty departmentNameString ? departmentNameString : ''}">
					</td>
				</tr>
				<tr>
					<td><b><label>Subject Name</label></b></td>
					<td><select name="subjectNames" required="required">
							<option value="">select subject</option>
							<c:if test="${not empty departmentSubject}">
								<c:forEach items="${departmentSubject}" var="subject">
									<option value="${subject}">${subject}</option>
								</c:forEach>
							</c:if>
					</select></td>
				</tr>
				<tr>

					<td><b><label>Score</label></b></td>

					<td><input type="text" name="score" id="score"
						required="required"></td>

				</tr>


				<tr>
					<td><input type="hidden" id="registerNumberInput"
						name="registernumber1" value="${registerNoString}"></td>
					<td><input type="hidden" name="action" value="addScore">
						<input type="submit" value="ADD SCORE" style="width: 70%;"></td>
				</tr>
			</table>
		</form>
	</div>

	<p style="color: green;">${scorestatus}</p>
	<p style="color: red;">${errordata}</p>
	<p style="color: red;">${scoreerror}</p>


	<script src="ValidationAddScorePage.js"></script>


</body>
</html>



