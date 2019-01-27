<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import = "wave.spring.Constants.AdminConstantsI" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style type="text/css">
.button{
	background-color: #1a1a1a;
	color: white;
	padding: 3px;
	width: 100px;
	font-size: 14px;
	border-radius: 15px;
	outline:0;
	cursor: pointer;
}
#HASH {
	display: flex;
	justify-content: space-between;
}
</style>
</head>
<body style="background-color:#1a1a1a;">
<%
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	response.setHeader("Pragma","no-cache");
	response.setHeader("Expires","0");	
%>
<div id="HASH">
	<span>
		<form action = "viewAdminProfile" name = "form" method = "POST" target = "workFrame">
			<input class = "button" type = "submit" name = "My_Profile" value = "My Profile"/>
		</form>
	</span>
	<table style="width:94%">
		<tr>
			<td align = "left"><t style="color:white;"> WAVE Xps Travelling </t></td>
			<td align = "right">
				<t style="color:white;"><b>Hello ${employeeDetails.firstName }</b>&nbsp&nbsp&nbsp&nbspMSISDN : ${employeeDetails.mobileNumber }&nbsp&nbsp&nbsp&nbsp
					<c:choose>
						<c:when test="${employeeDetails.lastLogout != null }">
							Last Logout : ${employeeDetails.lastLogout }
						</c:when>
					</c:choose>
				</t>
			</td>
		</tr>
	</table>
</div>	
</body>
</html>