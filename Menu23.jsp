<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>STUDENT MANAGEMENT SYSTEM</title>
<meta charset="ISO-8859-1">

<link rel="stylesheet" href="CSSmenu2.css">

</head>
<body>

	<form  class="flex-container">
	
		<h2><b><a href="SearchPage2.jsp">HOME</a></b></h2>
			
		<h2><b><a href="addscore2?action=scoreDetails&registerNumber=<%= request.getParameter("registerNumber") %>">SCORE DETAILS</a></b></h2>     
				
		<h2><b><a href="student?action=logout" style="padding-left: 500px;">LOGOUT</a></b></h2>
	
	</form>

</body>
</html>