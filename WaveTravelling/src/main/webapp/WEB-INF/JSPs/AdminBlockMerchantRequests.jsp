<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="wave.spring.Constants.AdminConstantsI"%>
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
<div align = "center">
	<table class = "adminLoginTable">
		<tr>
			<th colspan="2" align = "left"><t style="color:white; font-size:20px;">Block/Unblock Request &nbsp&nbsp&nbsp</t></th>
			<th colspan="4" align = "left">
				<errorMsg><span id="errorMsg"></span>${message}</errorMsg>
				<seccessMsg>${successMessage}</seccessMsg>
			</th>
		</tr>
		<tr align = "center">
			<th class = "formText">Marchant Id</th>
			<th class = "formText">Name</th>
			<th class = "formText">Gmail</th>
			<th class = "formText">Request Type</th>
			<th class = "formText">Confirm</th>
			<th class = "formText">Reject</th>
		</tr>
		<c:forEach items="${list}" var="list">
		<form:form id="applilcationForm" modelAttribute="AdminBlockMerchantRequests"  method="post" >
			<tr align = "center">
				<td>${list.userId}</td>
				<td>${list.firstName}&nbsp${list.lastName}</td>
				<td>${list.emailId}</td>
				<c:choose>
					<c:when test="${list.status=='UBL'}">
						<td style="color:red;">Block</td>
						<input type="hidden" value = "<%out.print(AdminConstantsI.BLACK_LISTED_REQUEST);%>" name = "status"/>
					</c:when>
					<c:otherwise>
						<td>Unblock</td>
						<input type="hidden" value = "<%out.print(AdminConstantsI.ALREADY_MERCHANT); %>" name = "status"/>
					</c:otherwise>
				</c:choose>
				<input type="hidden" value = "${list.userId }" name = "userId"/>
				<input type="hidden" value = "${list.employeeId }" name = "empId"/>
				<td><input type = "submit" class = "submitButton" value="Confirm" formaction = "merchantConfirmBlock"/></td>
				<td><input type = "submit" class = "submitButton" value="Reject" formaction = "merchantRerjectBlockRequest"/></td>
			</tr>
		</form:form>	
		</c:forEach>
	</table>
</div>	
</body>
</html>