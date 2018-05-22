<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html>
<head>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Public Locker Admin Page</title>
</head>
<body>
	<h1 align="center">Public Locker Admin Page</h1>


	<table align="center" height="200">
		<tr>
			<td>
				<button style="width: 300px; height: 25px; font-size: 18px" id="add">Add
					new Locker</button>
			</td>
		</tr>

		<tr>
			<td>
				<button style="width: 300px; height: 25px; font-size: 18px"
					id="update">Update Existing Locker</button>
			</td>
		</tr>

		<tr>
			<td>
				<button style="width: 300px; height: 25px; font-size: 18px"
					id="client">View Client</button>
			</td>
		</tr>




	</table>

</body>




<script type="text/javascript">
	function jump(value) {
		$
				.get(
						"/PublicLockerServer/AdminPageServlet?action=" + value,
						function() {
							window.location.href = "/PublicLockerServer/AdminPageServlet";
						})

	}

	$("#add").click(function() {
		jump("add")
	})
	$("#update").click(function() {
		jump("update")

	})

	$("#client").click(function() {
		jump("client")

	});
</script>

</html>