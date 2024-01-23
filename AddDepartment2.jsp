<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="Menu22.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Add department page</title>

<link rel="stylesheet" href="CSScommon2.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>

<style>
label {
	color: rgb(102, 0, 102);
	font-size: 20px;
}

body {
	background: linear-gradient(to top, #66ccff 0%, #ffcc99 100%);
}
</style>

</head>



<body>
	<h3></h3>
	<div>
		<form action="adddepartment2" method="post">
			<TABLE>
			<caption></caption>
			<tr><th></th></tr>
				<tr>
					<td><label>Department Name</label></td>
					<td><input type="text" name="department_name"
						id="departmentname"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" style="width: 70%;" value="ADD"></td>
			</TABLE>
			<input type="hidden" name="action" value="addDepartment">
		</form>
	</div>
	<p style="color: green;">${status}</p>
	<p style="color: red;">${error}</p>

	<p style="color: red;">${erroraddstudent}</p>
	<p style="color: red;">${departmenterror}</p>




	<script src="ValidationAddDepartment.js">
		
	</script>
</body>
</html>








