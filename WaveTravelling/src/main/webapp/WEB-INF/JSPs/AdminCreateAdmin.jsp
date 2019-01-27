<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page import="wave.spring.Constants.SystemConstants"%>
<%@page import="wave.spring.Constants.AdminConstantsI"%>
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
		<form:form id="adminCreateForm" modelAttribute="AdminCreateAdmin" action="confirmCreateAdmin" 
		method="POST" onsubmit="return validateAdminCreationForm()" >
			<table class = "adminLoginTable">
				<tr>
			 		<th colspan="1" align = "left"><t style="color:white; font-size:20px;">Create Admin &nbsp&nbsp&nbsp</t></th>
			 		<th colspan="5" align = "left">
						<errorMsg><span id="errorMsg"></span>${message}</errorMsg>
						<seccessMsg>${successMessage}</seccessMsg>
			 		</th>
				</tr>
				<tr>
					<td><spring:bind path="firstName"><%out.print(AdminConstantsI.FIRST_NAME);%></spring:bind></td>
					<td><form:input path="firstName" type="text"/></td>
					<td><spring:bind path="lastName"><%out.print(AdminConstantsI.LAST_NAME);%></spring:bind></td>
					<td><form:input path="lastName" type="text"/></td>
				</tr>
				<tr>
					<td><spring:bind path="mobileNumber"><%out.print(AdminConstantsI.MOBILE_NUMBER);%></spring:bind></td>
					<td><form:input path="mobileNumber" type="text"/></td>
					<td><spring:bind path="emailId"><%out.print(AdminConstantsI.EMAIL_ID);%></spring:bind></td>
					<td><form:input path="emailId" type="text"/></td>
				</tr>
				<tr>
					<td><spring:bind path="adminLevel"><%out.print(AdminConstantsI.ADMIN_LEVEL);%></spring:bind></td>
					<td><form:select path="adminLevel" type="text">
							<option value="2">
								2
							</option>
							<option value="3">
								3
							</option>
					</form:select></td>
					<td><spring:bind path="status"><%out.print(AdminConstantsI.STATUS);%></spring:bind></td>
					<td><form:select path="status" type="text">
							<option value="<%out.print(SystemConstants.ACTIVE);%>">
								<%out.print(SystemConstants.ACTIVE);%>
							</option>
							<option value="<%out.print(SystemConstants.INACTIVE);%>">
								<%out.print(SystemConstants.INACTIVE);%>
							</option>
					</form:select></td>
				</tr>
				<tr>
					<td></td>
					<td align = "right"><input type = "submit" class = "submitButton" value="<%out.print(AdminConstantsI.SUBMIT);%>"/></td>
					<td align = "left"><input type = "reset" class = "submitButton" value="<%out.print(AdminConstantsI.RESET);%>"/></td>
					<td></td>
				</tr>
			</table>
		</form:form>
	</div>	
	
</body>
</html>