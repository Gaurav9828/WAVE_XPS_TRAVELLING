<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/themes/CSS/list_table_Style_Sheet.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/themes/javaScript/normalFormValidation.js"></script>
</head>
<body>

	<div align = "center">
		<form id="newVehicleAdditionForm" enctype="multipart/form-data"  method="POST" onsubmit="return validateAddNewVehicle()" >
			<table class = "adminLoginTable">
				<tr>
			 		<th colspan="1" align = "left"><t style="color:white; font-size:20px;">Add New Vehicle</t></th>
			 		<th colspan="3" align = "left">
						<errorMsg><span id="errorMsg"></span>${message}</errorMsg>
						<seccessMsg>${successMessage}</seccessMsg>
			 		</th>
				</tr>
				<tr>
					<td>Company:</td>
					<td><input name="vehicleCompany" type="text"/></td>
					<td>Model:</td>
					<td><input name="vehicleModel" type="text"/></td>
				</tr>
				<tr>
					<td>Seats:</td>
					<td><input name="seats" type="text"/></td>
					<td>Image:</td>
					<td><input name="image" type="file" style="color:white;"/></td>
				</tr>
				<tr>
					<td colspan="2" align = "right"><input type = "submit" class = "submitButton" formaction="newVehicleAddProcess"/></td>
					<td colspan="2"> <input type = "reset" class = "submitButton" /></td>
				</tr>	
			</table>
		</form>
	</div>	
	
	<script>
	function loadPage() {
	    var div = document.createElement('div');
        var img = document.createElement('img');
        img.src = '${pageContext.request.contextPath}/resources/Images/pageLoading.gif';
        div.innerHTML = "<br><br><br><br><br><br><br><br><br><br><br><br><br><br>";
        div.style.cssText = 'position: fixed; z-index: 5000; top:0%; left:0%; width: 100%; height: 100%; text-align: center; background: rgba(0,0,0,0.7);';
        div.appendChild(img);
        document.body.appendChild(div);
	}
</script>
</body>
</html>