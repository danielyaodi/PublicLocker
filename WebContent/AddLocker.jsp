<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<p>
	<a href="http://127.0.0.1:8080/PublicLockerServer/admin.jsp">Home
		Page</a>
</p>
<h2 align="center">Add a New Locker</h2>
<body>

	<form id="form" name="form" action="/AddLockerServlet" method="post">

		<table align="center" height="300">
			<tr>
				<td>Please add the locker address:</td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td>Street Number:</td>
				<td></td>
				<td><input type="text" name="address" id='address'
					style="width: 200px; height: 20px; font-size: 15px" /></td>
			</tr>
			<tr>
				<td>City:</td>
				<td></td>
				<td><input type="text" name="city" id='city'
					style="width: 200px; height: 20px; font-size: 15px" /></td>
			</tr>
			<tr>
				<td>State:</td>
				<td></td>
				<td><input type="text" name="state" id='state'
					style="width: 200px; height: 20px; font-size: 15px" /></td>
			</tr>
			<tr>
				<td>Zipcode:</td>
				<td></td>
				<td><input type="text" name="zipcode" id='zipcoe'
					style="width: 200px; height: 20px; font-size: 15px" /></td>
			</tr>

			<tr>
			</tr>
			<tr>
				<td><input type="reset" name="reset" id="reset"
					style="width: 100px; height: 40px; font-size: 18px"></td>
				<td></td>
				<td align="right"><input type="submit" name="submit"
					value="Add" id="submit"
					style="width: 100px; height: 40px; font-size: 18px"></td>
			</tr>


		</table>

 

	</form>



	<script type="text/javascript">
		$("#submit").click(function() {
			alert("done");
			$.ajax({
				url : "/AddLockerServlet",
				type : "POST",
				data : ('form').serializeArray(),
				contentType : "application/x-www-form-urlencoded",
				success : function() {
				}

			})
		})
	</script>
</body>




</html>