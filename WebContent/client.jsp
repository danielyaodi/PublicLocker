<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Client View</title>
<h2 align="center">Update locker</h2>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>


<script type="text/javascript">
	
<%String apiKey = (String) request.getAttribute("apiKey");%>
	var customerAPIKey =
<%=apiKey%>
	;

	$(document).ready(
			function() {
				$.ajax({
					type : "post",
					url : '/PublicLockerServer/ClientServlet',
					data : {
						action : "showClientRecord",
						apiKey : customerAPIKey
					},
					cache : false,
					dataTyep : "json",
					error : function() {
						alert("error");
					},
					success : function(json) {
						var data = $.parseJSON(json);
						$.each(data, function(i, val) {
							var s = '';
							s += '<tr><td>' + val.orderNumber + '</td><td>'
									+ val.orderTime + '</td><td>'
									+ val.deliveryTime + '</td><td>'
									+ val.pickupTime + '</td><td>' + val.street
									+ '</td><td>' + val.city + '</td><td>'
									+ val.state + '</td><td>' + val.zipCode
									+ '"/></td></tr>';
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
			<th>Order Number</th>
			<th>OrderTime</th>
			<th>DeliveryTime</th>
			<th>PickupTime</th>
			<th>Locker Street</th>
			<th>Locker City</th>
			<th>Locker State</th>
			<th>Locker Zipcode</th>
		</tr>

	</table>


</body>
</html>