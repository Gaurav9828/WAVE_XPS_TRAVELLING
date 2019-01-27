<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="wave.spring.Constants.AdminConstantsI"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/themes/CSS/list_table_Style_Sheet.css">
</head>
<body>
<div align = "center">
	<table class = "adminLoginTable">
		<tr>
			<th colspan="4" align = "left"><t style="color:white; font-size:20px;">My Profile</t></th>
		</tr>
		<tr>
			<td><%out.print(AdminConstantsI.FIRST_NAME);%></td>
			<td>${list.firstName }</td>
			<td><%out.print(AdminConstantsI.LAST_NAME);%></td>
			<td>${list.lastName }</td>
		</tr>
		<tr>
			<td><%out.print(AdminConstantsI.EMP_ID);%></td>
			<td>${list.employeeId }</td>	
			<td><%out.print(AdminConstantsI.EMAIL_ID);%></td>
			<td>${list.emailId }</td>	
		</tr>
		<tr>
			<td><%out.print(AdminConstantsI.MOBILE_NUMBER);%></td>
			<td>${list.mobileNumber }</td>
			<td>Last Logout</td>
			<td>${list.lastLogout }</td>
		</tr>	
		<tr>
			<td colspan="4" align = "left">
				<form action = "editProfile" method = "POST">
					<input type = "submit" class = "submitButton" value = "Edit"/>
				</form> 
			</td>
		</tr>
	</table>
</div>	
</body>
</html>