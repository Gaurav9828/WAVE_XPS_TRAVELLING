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
	href="${pageContext.request.contextPath}/resources/themes/CSS/Registration_Style_Sheet.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/themes/javaScript/loginFormValidation.js"></script>
</head>
<body>
	<%SecurityI security = new Security();
	  HashMap<String,String> map = new HashMap();
	  map = security.generateCaptcha();%>
	<div align = "right">
		<img alt="project_name" style = "width:250px; height:130px;" src="${pageContext.request.contextPath}/resources/Images/Wave_Page_Img.png"><br>
		<projectName><%out.print(SystemConstants.PROJECT_NAME_ONE+"\n"+SystemConstants.PROJECT_NAME_TWO); %></projectName><br>
	</div>
	<div align = "center">
		<form:form id="adminLevel1Registration" modelAttribute="AdminLevel1RegistrationRequest" action="AdminLevel1SelfRegistrationRequest" 
		method="post" onsubmit="return validateAdminLevel1SelfRegistartionForm()">
			<table class = "adminLoginTable">
				<tr>
					<th colspan="4" style="color:white;">Register here to be a part of Wave Xps travelling and engage your vechile with us.</th>
				</tr>
				<tr>
					<th colspan="4" align = "left"><errorMsg><span id="errorMsg"></span>
							${message}
						</errorMsg>
						<seccessMsg>${successMessage}</seccessMsg>
					</th>
				</tr>
				<tr align = "left">
					<th colspan="2">
						<pageName><%out.print(AdminConstantsI.REGISTRATION_REQUEST);%></pageName>
					</th>
					<td></td>
					<td></td>
					<td align = "right">
						<a href="${pageContext.request.contextPath}/AdminLogin">
						<img alt="project_name" style = "width:40px; height:40px;" 
						src="${pageContext.request.contextPath}/resources/Images/back_icon.png"></a>
					</td>
					
				</tr>
				<tr>
					<td class = "formText"><spring:bind path="firstName"><%out.print(AdminConstantsI.FIRST_NAME);%></spring:bind></td>
					<td><form:input path="firstName" type="text"/></td>
					<td class = "formText"><spring:bind path="lastName"><%out.print(AdminConstantsI.LAST_NAME);%></spring:bind></td>
					<td><form:input path="lastName" type="text"/></td>
				</tr>
				<tr>
					<td class = "formText"><spring:bind path="secretWord"><%out.print(AdminConstantsI.SECRET_WORD);%></spring:bind></td>
					<td><form:input path="secretWord" type="text"/></td>
					<td class = "formText"><spring:bind path="mobileNumber"><%out.print(AdminConstantsI.MOBILE_NUMBER);%></spring:bind></td>
					<td><form:input path="mobileNumber" type="text"/></td>
				</tr>
				<tr>
					<td class = "formText"><spring:bind path="city"><%out.print(AdminConstantsI.CITY);%></spring:bind></td>
					<td><form:input path="city" type="text"/></td>
					<td class = "formText"><spring:bind path="pinCode"><%out.print(AdminConstantsI.PIN_CODE);%></spring:bind></td>
					<td><form:input path="pinCode" type="text"/></td>
				</tr>
				<tr>
					<td class = "formText"><spring:bind path="identityType"><%out.print(AdminConstantsI.CHOOSE_IDENTITY);%></spring:bind></td>
					<td><form:select path="identityType" type="text">
							<option value="<%out.print(AdminConstantsI.ADHAR_ID);%>">
								<%out.print(AdminConstantsI.ADHAR_ID);%>
							</option>
							<option value="<%out.print(AdminConstantsI.PAN_ID);%>">
								<%out.print(AdminConstantsI.PAN_ID);%>
							</option>
							<option value="<%out.print(AdminConstantsI.PASSPORT_ID);%>">
								<%out.print(AdminConstantsI.PASSPORT_ID);%>
							</option>
					</form:select></td>
					<td class = "formText"><spring:bind path="identityNumber"><%out.print(AdminConstantsI.ID_NUMBER);%></spring:bind></td>
					<td><form:input path="identityNumber" type="text"/></td>
				</tr>
				<tr>
					<td class = "formText"><spring:bind path="landMark"><%out.print(AdminConstantsI.LAND_MARK);%></spring:bind></td>
					<td><form:textarea path="landMark" type="text"/></td>
					<td class = "formText"><spring:bind path="emailId"><%out.print(AdminConstantsI.EMAIL_ID);%></spring:bind></td>
					<td><form:input path="emailId" type="text"/></td>
				</tr>
				<tr>
					<td class = "formText"><spring:bind path="captcha"><button type = "submit"
					class = "captcha" disabled><%out.print(map.get(SystemConstants.CAPTCHA));%></button>
					</spring:bind></td>
					<td><form:input path="captcha" type="text" autocomplete="off"/></td>
					<td><input type="hidden" value = "<%out.print(map.get(SystemConstants.CAPTCHA));%>" name = "keyCaptcha"/>
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