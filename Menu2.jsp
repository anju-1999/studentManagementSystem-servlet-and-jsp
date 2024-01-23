<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>STUDENT MANAGEMENT SYSTEM</title>
<meta charset="ISO-8859-1">

<style>
a{text-decoration: none; color: white;}

.flex-container {
  display: flex;
  background-color: teal;
}

.flex-container > h2 {
  margin: 15px;
  padding: 25px;
}

html,form,body{width:100%;
margin: -1px -1px -1px 0px;}
</style>

</head>
<body>
	<form class="flex-container">
	
					<h2><a href="AddEditPage2.jsp?actionAddEdit=add" >ADD STUDENT</a></h2>
				
					<h2><a href="AddDepartment2.jsp" >ADD DEPARTMENT</a></h2>
				
					<h2><a href="AddSubject2.jsp" >ADD SUBJECT</a></h2>
		
					<h2><a href="AddScorePage2.jsp?" >ADD SCORE</a></h2>
			
					<h2><a href="student?action=logout">LOGOUT</a></h2>
	</form>
</body>
</html>