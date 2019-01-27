<%@page import="wave.spring.Constants.SystemConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>
	<%out.print(SystemConstants.PROJECT_NAME);%>
</title>
<link rel="icon" type="image/jpg"
	href="${pageContext.request.contextPath}/resources/Images/Wave_Icon.png">
<style type="text/css">
button {
    border-radius: 100%;
	background-color: white;
	border: none;
	color: Red;
	font-size: 38px;
	width: 132px;
	height: 60px;
	cursor: pointer;
	margin: 5px;
	outline:0;
}

.theam{
	color: black;
	font-size: 30px;
	font-weight: bold;
}
.signature{
	color: black;
	font-size: 28px;
	font-weight: bold;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body background="${pageContext.request.contextPath}/resources/Images/HomePageBg.jpg">
<br><br>
	<table align="right">
		<tr>
			<td><a href="${pageContext.request.contextPath}/AdminLogin"><button name = "Login">Login</button></a>
			&nbsp&nbsp
			</td>
			<td><a href="${pageContext.request.contextPath}/Registration"><button name = "Registration">Sign In</button></a>
			&nbsp&nbsp
			</td>
		</tr>
	</table>
	<table align = center>
		<tr>
			<td align = "left"><img alt="project_name" style = "width:800px; height:470px;" 
			src="${pageContext.request.contextPath}/resources/Images/Wave_Page_Img.png"></td>
		</tr>
		<tr>
			<td align = "right"><projectName style = "color: black; font-size: 130px; font-weight: bold;">
			<%out.print(SystemConstants.PROJECT_NAME_ONE+"\n"+SystemConstants.PROJECT_NAME_TWO); %></projectName><br>
			</td>
		</tr>
	</table>
	<br><br><br><br><br><br>
	<div align = "right">
		<i><t class="theam"><%out.print(SystemConstants.THEAM+" ("); %></t><t class="signature"><%out.print(SystemConstants.DEVELOPERS+")"); %></i>
	</div>
</body>
</html>