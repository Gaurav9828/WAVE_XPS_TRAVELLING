<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="wave.spring.Constants.SystemConstants"%>
<%@page import="wave.spring.Constants.AdminConstantsI"%>
<%@page import="wave.spring.model.MerchantDetails"%>
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
<div align = "center">
	<%MerchantDetails applicantDetail = (MerchantDetails) request.getAttribute(SystemConstants.MERCHANT);%>
	<table class = "adminLoginTable">
		<tr align = "center">
			<th class = "formText">Name</th>
			<th class = "formText">Submission Date</th>
			<th class = "formText">Confirm</th>
			<th class = "formText">Back</th>
		</tr>
		<form:form id="applilcationForm" modelAttribute="AdminMerchantApplications"  method="post"  onsubmit = "return loadPage()">
			
			<tr align = "center">
			<input type = "hidden" name = "merchantId" value = "<%out.print(applicantDetail.getMarchantId());%>"/>
			<input name="mailId" type="hidden" value = "<%out.print(applicantDetail.getEmailId());%>"/>
			<td class = "formText"><%out.print(applicantDetail.getFirstName()+" "+applicantDetail.getLastName()); %></td>
			<td class = "formText"><%out.print(applicantDetail.getSubmissionDate());%></td>
			<td class = "formText"><input type = "submit" class = "submitButton" value="Confirm" formaction = "rejectMerchantApplications"/></td>
			<td class = "formText"><input type = "submit" class = "submitButton" value="Back" formaction = "backMerchantApplications"/></td>
			</tr>
		</form:form>
	</table>
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