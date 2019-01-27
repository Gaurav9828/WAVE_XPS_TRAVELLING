<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="wave.spring.Constants.AdminConstantsI"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/themes/CSS/list_table_Style_Sheet.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/themes/javaScript/normalFormValidation.js"></script>
</head>
<body>
<div align = "center">
<form name = "editProfileForm" action = "confirmEditProfile" method = "POST" onsubmit="return validateEditProfile()">
	<table class = "adminLoginTable">
		<tr>
			<th colspan="1" align = "left"><t style="color:white; font-size:20px;">My Profile</t></th>
			<th colspan="3" align = "left">
				<errorMsg><span id="errorMsg"></span>${message}</errorMsg>
				<seccessMsg>${successMessage}</seccessMsg>
			</th>
		</tr>
		<tr>
			<td><%out.print(AdminConstantsI.FIRST_NAME);%></td>
			<td><input value = "${list.firstName }" type = "text" name = "firstName"/></td>
			<td><%out.print(AdminConstantsI.LAST_NAME);%></td>
			<td><input value = "${list.lastName }" type = "text" name = "lastName"/></td>
		</tr>
		<tr>
			<td><%out.print(AdminConstantsI.MOBILE_NUMBER);%></td>
			<td><input value = "${list.mobileNumber }" type = "text" name = "mobileNumber"/></td>	
			<td><%out.print(AdminConstantsI.EMAIL_ID);%></td>
			<td><input value = "${list.emailId }" type = "text" name = "emailId"/></td>	
		</tr>
		<tr>
			<td>Password</td>
			<td><input value = "00000000" type = "password" name = "password"/></td>
			<td style = "color:red;"><%out.print(AdminConstantsI.CURRENT_PASSWORD);%></td>
			<td><input value = "" type = "password" name = "pass2"/></td>
		</tr>	
		<tr>
			<td colspan="4" align = "left">
					<input type = "submit" class = "submitButton" value = "Confirm"/>
			</td>
		</tr>
	</table>
</form> 
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
</div>	
</body>
</html>