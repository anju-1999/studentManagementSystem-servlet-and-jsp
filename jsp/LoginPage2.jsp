<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="CSSlogin2.css">

</head>
<body>

	<div>
		<form action="student" method="get">
			<table>
				<caption></caption>
				<tr>
					<th></th>
				</tr>
				<tr>
					<td><label>Username</label></td>
				</tr>

				<tr>
					<td><input type="text" name="username"
						placeholder="Enter Username" required="required"></td>
				</tr>

				<tr>
					<td class="distance"><label>Password</label></td>
				</tr>

				<tr>
					<td><input type="password" name="password"
						placeholder="Enter Password" required="required"></td>
				</tr>

				<tr>
					<td></td>
				</tr>

				<tr>
					<td class="distance"><input type="hidden" name="action"
						value="login"> <input type="submit" value="LOGIN"></td>
				</tr>

			</table>
			<p style="color: red;">${error}</p>
		</form>

	</div>

	<script>
	
	
</script>

</body>
</html>