<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">






<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Cell Page</title>

<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<p>
	<a href="http://127.0.0.1:8080/PublicLockerServer/admin.jsp">Home
		Page</a>
</p>
<h2 align="center">Update Cell</h2>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						alert("ready");
						$
								.ajax({
									type : "post",
									url : '/PublicLockerServer/UpdateLockerServlet',
									data : {
										action : "requestCell"
									},
									cache : false,
									/* dataType : "json", */
									error : function() {
										alert("updateCell cannot request data")
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
																	+ val.cellID
																	+ '</td><td>'
																	+ val.lockerID
																	+ '</td><td>'
																	+ val.cellType
																	+ '</td><td>'
																	+ val.cellAvailability
																	+ '</td><td>'
																	+ val.cellCommitted
																	+ '</td><td>'
																	+ val.cellHealth
																	+ '</td><td>'
																	+ '<input type="button" name="updateBtn"'
																	+ 'value="update this cell" id="btn'
																	+ i
																	+ '" onclick="toGet(&quot;updateThisCells&quot;,&quot;'
																	+ val.cellID
																	+ '&quot;)"/></td>'
																	+ '<td><input type="button" name="delBtn"' 
						+ 'value="delete this cell" id="delBtn'
						+ i 
						+ '" onclick="toGet(&quot;deleteThisCell&quot;,&quot;'
						+ val.cellID
						+'"/></td></tr>';
															$('#tb').append(s);
														})
									}
								})
					})
</script>



</head>
<body>
	<table id="tb" border="1"
		style="border-collapse: collapse; width: 590px;">
		<tr>
			<th>cellID</th>
			<th>lockerID</th>
			<th>cellType</th>
			<th>cellAvailability</th>
			<th>cellCommitted</th>
			<th>cellHealth</th>
			<th>Update Cell</th>
			<th>Delete Cell</th>
		</tr>

	</table>
</body>
<script type="text/javascript">
	function toGet(valueI, valueII) {

		$.get("/PublicLockerServer/UpdateLockerServlet?action=" + valueI
				+ "&cellID=" + valueII, function() {

			alert("posted");
			window.location.href = "/PublicLockerServer/UpdateLockerServlet";
		});
	}
</script>


</html>