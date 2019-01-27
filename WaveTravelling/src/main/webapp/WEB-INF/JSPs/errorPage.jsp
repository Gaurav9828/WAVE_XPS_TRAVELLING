<%@page import="wave.spring.Constants.SystemConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>
	<%
		out.print(SystemConstants.PROJECT_NAME);
	%>
</title>
<link rel="icon" type="image/jpg"
	href="${pageContext.request.contextPath}/resources/Images/Wave_Icon.png">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body
	background="${pageContext.request.contextPath}/resources/Images/Wave_Page_Img.png" style="background-repeat: no-repeat;">
	
	<div align="center">
		<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
		<a href = "${pageContext.request.contextPath}/AdminLogin"><button style = "width : 100px; background : red; color : white;" value = "Login">Click Here</button></a> 
	</div>
	<div align="center">
		<img
			src="${pageContext.request.contextPath}/resources/Images/errorImage.png" width="226" height="210" />
	</div>
</body>
</html>