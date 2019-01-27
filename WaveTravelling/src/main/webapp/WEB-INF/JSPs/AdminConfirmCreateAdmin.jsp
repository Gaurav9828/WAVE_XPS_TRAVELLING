<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page import="wave.spring.Constants.AdminConstantsI"%>
<%@page import="wave.spring.model.EmployeeDetails"%>
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
	<%EmployeeDetails employeeDetails = new EmployeeDetails(); 
	try{
		employeeDetails = (EmployeeDetails) request.getAttribute(AdminConstantsI.EMPLOYEE_DETAILS);
	}catch(Exception e){
		e.printStackTrace();
	}%>
	<div align = "center">
		<form:form id="adminCreateForm" modelAttribute="AdminConfirmCreateAdmin" method="POST" onsubmit="return loadPage()">
			<table class = "adminLoginTable">
				<tr>
					<th colspan="4" style="color:white; font-size:25px;">Confirm Admin Partner Details</th>
				</tr>
				<tr>
					<th colspan="4" align = "left"><errorMsg><span id="errorMsg"></span>
							${message}
						</errorMsg>
						<seccessMsg>${successMessage}</seccessMsg>
					</th>
				</tr>
				<tr>
					<td class = "formText"><spring:bind path="firstName"><%out.print(AdminConstantsI.FIRST_NAME);%></spring:bind></td>
					<td><form:input path="firstName" type="text" value="${employeeDetails.firstName}" /></td>
					<td class = "formText"><spring:bind path="lastName"><%out.print(AdminConstantsI.LAST_NAME);%></spring:bind></td>
					<td><form:input path="lastName" type="text" value="${employeeDetails.lastName}"/></td>
				</tr>
				<tr>
					<td class = "formText"><spring:bind path="mobileNumber"><%out.print(AdminConstantsI.MOBILE_NUMBER);%></spring:bind></td>
					<td><form:input path="mobileNumber" type="text" value="${employeeDetails.mobileNumber}"/></td>
					<td class = "formText"><spring:bind path="emailId"><%out.print(AdminConstantsI.EMAIL_ID);%></spring:bind></td>
					<td><form:input path="emailId" type="text" value="${employeeDetails.emailId}"/></td>
				</tr>
				<tr>
					<td class = "formText"><spring:bind path="adminLevel"><%out.print(AdminConstantsI.ADMIN_LEVEL);%></spring:bind></td>
					<td><form:input path="adminLevel" type="text" value="${employeeDetails.adminLevel}"/></td>
					<td class = "formText"><spring:bind path="status"><%out.print(AdminConstantsI.STATUS);%></spring:bind></td>
					<td><form:input path="status" type="text" value="${employeeDetails.status}"/></td>
				</tr>
				<tr>
					<td></td>
					<td align = "right"><input type = "submit" class = "submitButton" value="<%out.print(AdminConstantsI.ÇONFIRM);%>" formaction="doneCreateAdmin" /></td>
					<td align = "left"><input type = "submit" class = "submitButton" value="<%out.print(AdminConstantsI.BACK);%>" formaction="createAdmin"/></td>
					<td></td>
				</tr>
			</table>
		</form:form>
	</div>	
	
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
</body>
</html>