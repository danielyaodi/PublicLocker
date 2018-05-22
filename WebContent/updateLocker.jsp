<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Locker Page</title>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<p>
	<a href="http://127.0.0.1:8080/PublicLockerServer/admin.jsp">Home
		Page</a>
</p>
<h2 align="center">Update locker</h2>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$
								.ajax({
									type : "post",
									url : '/PublicLockerServer/UpdateLockerServlet',
									data : {
										action : "showAllLockers"
									},
									cache : false,
									dataTyep : "json",
									error : function() {
										alert("error");
									},
									success : function(json) {
										alert(json)
										var data = $.parseJSON(json);
										$
												.each(
														data,
														function(i, val) {
															var s = '';
															s += '<tr><td>'
																	+ val.lockerID
																	+ '</td><td>'
																	+ val.street
																	+ '</td><td>'
																	+ val.city
																	+ '</td><td>'
																	+ val.state
																	+ '</td><td>'
																	+ val.zipcode
																	+ '</td><td>'
																	+ val.createTime
																	+ '</td><td>'
																	+ '<input type="button" name="updateBtn"'
																	+ 'value="update cells in this locker" id="btn'
																	+ i
																	+ '" onclick="toGet(&quot;updateCells&quot;,&quot;'
																	+ val.lockerID
																	+ '&quot;)"/></td>'
																	+ '<td><input type="button" name="delBtn"' 
																	+ 'value="delete this locker" id="delBtn'
																	+ i 
																	+ '" onclick="toGet(&quot;deleteLocker&quot;,&quot;'
																	+ val.lockerID
																	+'"/></td></tr>';
															$('#tb').append(s);

														});
									}
								});
					});
</script>

</head>
<body>
	<table id="tb" border="1"
		style="border-collapse: collapse; width: 590px;">
		<tr>
			<th>LockerID</th>
			<th>Street</th>
			<th>City</th>
			<th>State</th>
			<th>Zipcode</th>
			<th>CreateTime</th>
			<th>Update Cell</th>
			<th>Delete Locker</th>
		</tr>

	</table>

</body>

<script type="text/javascript">
	function toGet(valueI, valueII) {

		$.get("/PublicLockerServer/UpdateLockerServlet?action=" + valueI
				+ "&lockerID=" + valueII, function() {

			alert("posted");
			window.location.href = "/PublicLockerServer/UpdateLockerServlet";
		});
	}

	/* function jump(value) {
		$.post("/PublicLockerServer/UpdateLockerServlet", {
			action : value
		}, function() {
			alert(123);
			window.location.href = "/PublicLockerServer/AdminPageServlet"

		})

	}

	$("#btn1").click(function() {
		jump("add")
	}) */
</script>
</html>