<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register A New Account</title>
<h2 align="center">Register A New Account</h2>
</head>
<body>
	<form action="/PublicLockerServer/ClientServlet" method="post">
		<table align="center" height="200">
			<tr>
				<td>Please register a new account:</td>
			</tr>
			<tr>
				<td>Customer Name:</td>
				<td><input type="text" name="customerName" id="customerName"
					style="width: 200px; height: 20px; font-size: 15px"></td>

			</tr>
			<tr>
				<td>User Name:</td>
				<td><input type="text" name="userName" id="userName"
					style="width: 200px; height: 20px; font-size: 15px"></td>

			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="text" name="password" id="password"
					style="width: 200px; height: 20px; font-size: 15px"></td>
				<td><input type="hidden" name="action" id="action"
					value="register"></td>
			</tr>
			<tr>
				<td>verify Password:</td>
				<td><input type="text" name="password2" id="password2"
					style="width: 200px; height: 20px; font-size: 15px"></td>

			</tr>
			<tr>
				<td><input type="reset" name="reset" id="reset"
					style="width: 100px; height: 40px; font-size: 18px"></td>

				<td align="right"><input type="submit" name="submit"
					value="register" id="submit"
					style="width: 100px; height: 40px; font-size: 18px"></td>

			</tr>

		</table>
	</form>

</body>
</html>