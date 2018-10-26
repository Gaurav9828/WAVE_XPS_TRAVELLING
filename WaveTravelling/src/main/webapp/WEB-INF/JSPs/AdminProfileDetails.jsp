<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="wave.spring.Constants.SystemConstants"%>
    <%@page import = "wave.spring.Constants.AdminConstantsI" %>
    <%@page import = "wave.spring.model.EmployeeDetails" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body style="background-color:blue;">
	<%
	EmployeeDetails employeeDetails = null;
	employeeDetails =  (EmployeeDetails)session.getAttribute(AdminConstantsI.EMPLOYEE_DETAILS); %>
	<table style="width:100%">
		<tr>
			<td align = "left"><t style="color:white;">WAVE Xps Travelling</t></td>
			<td align = "right"><t style="color:white;"><%out.print(employeeDetails.getName()); %>&nbsp&nbsp
			MSISDN : <%out.print(employeeDetails.getMobileNumber()); %>&nbsp&nbsp
			Last Login : <%out.print(employeeDetails.getLastLoginDate()); %></t></td>
		</tr>
	</table>
</body>
</html>