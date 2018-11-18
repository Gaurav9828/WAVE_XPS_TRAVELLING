<%@page import="wave.spring.model.VechileAttributes"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page import="wave.spring.Constants.SystemConstants"%>
<%@page import="wave.spring.Constants.AdminConstantsI"%>
<%@page import="wave.spring.security.Security"%>
<%@page import="wave.spring.security.SecurityI"%>
<%@page import="java.util.List" %>
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
	href="${pageContext.request.contextPath}/resources/themes/CSS/list_table_Style_Sheet.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/themes/javaScript/loginFormValidation.js"></script>
</head>
<body>
<%SecurityI security = new Security(); 
	List<VechileAttributes> vechileNameList = security.getVechileList();%>
	<div align = "center">
	<form:form id="addVechileForm" modelAttribute="MerchantAddVechile" action="addVechile" method="post" onsubmit="return validateAddVechileForm()">
	<table class = "adminLoginTable">
		<tr>
			<td class = "formText"><spring:bind path="vechileName">Vechile Name</spring:bind></td>
					<td><form:select path="vechileName" type="text">
						<%for(VechileAttributes vechile : vechileNameList){%>
							<option value="<%out.print(vechile.getVechileCompany()+" "+vechile.getVechileModel());%>">
								<%out.print(vechile.getVechileCompany()+" "+vechile.getVechileModel());%>
							</option>
						<%}%>			
					</form:select></td>
		</tr>
		<tr>			
			<td><spring:bind path="vechileNumnber">Vechile Number</spring:bind></td>
			<td><form:input path="vechileNumnber" type="text"/></td>
		</tr>
		<tr>
			<td><spring:bind path="insurenceNumber">Insurence Number</spring:bind></td>
			<td><form:input path="insurenceNumber" type="text"/></td>
		</tr>
		<tr>
			<td align = "right"><input type = "submit" class = "submitButton" value="<%out.print(AdminConstantsI.SUBMIT);%>"/></td>
			<td align = "left"><input type = "reset" class = "submitButton" value="<%out.print(AdminConstantsI.RESET);%>"/></td>
		</tr>
	</table>
	</form:form>
	</div>
</body>
</html>