<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add a New Locker</title>
</head>
  <script type="text/javascript" src="js/jquery-3.3.1.js"></script>  
<p>
	<a href="http://127.0.0.1:8080/PublicLockerServer/admin.jsp">Home
		Page</a>
</p>
<h2 align="center">Add a New Locker</h2>
<body>

	<form id="form" name="form" action="" method="post">

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
				<td><input type="text" name="street" id='street'
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
				<td>cellTypeI Qty:</td>
				<td></td>
				<td>cellTypeII Qty:</td>
				<td>cellTypeIII Qty:</td>

			</tr>



			<tr>
				<td><select name="cellTypeIQty">
						<option value=0>0</option>
						<option value=1>1</option>
						<option value=2>2</option>
						<option value=3>3</option>
						<option value=4>4</option>
						<option value=5 selected>5</option>
						<option value=6>6</option>
						<option value=7>7</option>
						<option value=8>8</option>
						<option value=9>9</option>
						<option value=10>10</option>
						<option value=11>11</option>
						<option value=12>12</option>
						<option value=13>13</option>
						<option value=14>14</option>
						<option value=15>15</option>
				</select></td>
				<td></td>

				<td><select name="cellTypeIIQty">
						<option value=0>0</option>
						<option value=1>1</option>
						<option value=2>2</option>
						<option value=3>3</option>
						<option value=4>4</option>
						<option value=5 selected>5</option>
						<option value=6>6</option>
						<option value=7>7</option>
						<option value=8>8</option>
						<option value=9>9</option>
						<option value=10>10</option>
						<option value=11>11</option>
						<option value=12>12</option>
						<option value=13>13</option>
						<option value=14>14</option>
						<option value=15>15</option>
				</select></td>


				<td><select name="cellTypeIIIQty" >
						<option value=0>0</option>
						<option value=1>1</option>
						<option value=2>2</option>
						<option value=3>3</option>
						<option value=4>4</option>
						<option value=5 selected>5</option>
						<option value=6>6</option>
						<option value=7>7</option>
						<option value=8>8</option>
						<option value=9>9</option>
						<option value=10>10</option>
						<option value=11>11</option>
						<option value=12>12</option>
						<option value=13>13</option>
						<option value=14>14</option>
						<option value=15>15</option>
				</select></td>


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
				url : "/PublicLockerServer/AddLockerServlet",
				type : "POST",
				data:JSON.stringify($('form').serializeObject()),
				contentType:"application/json",
				success : function() {
					alert("done2")
				}

			})
		})
		
		$.fn.serializeObject = function() {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [ o[this.name] ];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
		
		
		
		
		
	</script> 
</body>




</html>