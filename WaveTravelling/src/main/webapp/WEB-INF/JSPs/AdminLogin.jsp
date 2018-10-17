<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="wave.spring.Constants.SystemConstants"%>
<%@page import="wave.spring.Constants.AdminConstantsI"%>
<%@page import="wave.spring.security.SecurityI"%>
<%@page import="wave.spring.security.Security"%>
<%@page import="java.util.HashMap" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>
	<%out.print(SystemConstants.PROJECT_NAME);%>
</title>
<link rel="icon" type="image/jpg"
	href="${pageContext.request.contextPath}/resources/Images/Wave_Icon.png">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/themes/CSS/Style_Sheet.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/themes/javaScript/loginFormValidation.js"></script>
</head>
<body>
	<%SecurityI security = new Security();
	  HashMap<String,String> map = new HashMap();
	  map = security.generateCaptcha();%>
	<div align = "right">
		<img alt="project_name" style = "width:300px; height:170px;" src="${pageContext.request.contextPath}/resources/Images/Wave_Page_Img.png"><br>
		<projectName><%out.print(SystemConstants.PROJECT_NAME_ONE+"\n"+SystemConstants.PROJECT_NAME_TWO); %></projectName><br>
	</div>
	
	<div align = "center">
		<form:form id="loginForm" modelAttribute="AdminLogin" action="loginProcess" method="post" onsubmit="return validate()">
			<table class = "adminLoginTable">
				<tr>
					<td><errorMsg><span id="errorMsg"></span>
							${message}
						</errorMsg>
					</td>
				</tr>
				<tr align = "left">
					<td>
						<pageName><%out.print(AdminConstantsI.LOGIN);%></pageName>
					</td>
				</tr>
				<tr>
					<td class = "formText"><spring:bind path="id"><%out.print(AdminConstantsI.EMP_ID);%></spring:bind></td>
					<td><form:input path="id" type="text"/></td>
				</tr>
				<tr>
					<td class = "formText"><spring:bind path="password"><%out.print(AdminConstantsI.PASSWORD);%></spring:bind></td>
					<td><form:input path="password" type="password"/></td>
				</tr>
				<tr>
					<td class = "formText"><spring:bind path="captcha"><input type = "submit" value = "<%out.print(map.get(SystemConstants.CAPTCHA));%>"
					class = "captcha" disabled>
					</spring:bind></td>
					<td><form:input path="captcha" type="text"/></td>
					<td><input type="hidden" value = "<%out.print(map.get(SystemConstants.CAPTCHA));%>" name = "keyCaptcha"/>
				</tr>
				<tr>
					<td align = "right"><input type = "submit" class = "submitButton" value="<%out.print(AdminConstantsI.SUBMIT);%>"/></td>
					<td align = "left"><input type = "reset" class = "submitButton" value="<%out.print(AdminConstantsI.RESET);%>"/></td>
				</tr>
			</table>
		</form:form>
		<a href = "" style = "color:white;"><%out.print(AdminConstantsI.RESET_PASSWORD);%></a>
	</div>	
</body>
</html>