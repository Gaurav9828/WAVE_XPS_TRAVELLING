<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
	<div align="center">
	<table class = "adminLoginTable">
		<tr>
			 <th colspan="3" align = "left"><t style="color:white; font-size:20px;">Approve New Vehicle &nbsp&nbsp&nbsp</t></th>
			 <th colspan="3" align = "left">
				<errorMsg><span id="errorMsg"></span>${message}</errorMsg>
				<seccessMsg>${successMessage}</seccessMsg>
			</th>
		</tr>
		<tr align = "center">
			<th class = "formText">Company</th>
			<th class = "formText">Model</th>
			<th class = "formText">Seats</th>
			<th class = "formText">Image</th>
			<th class = "formText">Approve</th>
			<th class = "formText">Reject</th>	
		</tr>
		<c:forEach items="${list}" var="list">
			<form:form id="applilcationForm" method="post" >
				<tr align = "center">
					<td>${list.vehicleCompany}</td>
					<td>${list.vehicleModel}</td>
					<td>${list.seats}</td>
					<td><img src="data:image/jpg;base64,${list.base64Image}" width="230" height="130" /></td>
					<input type = "hidden" value = "${list.ID}" name = "ID"/>
					<input type = "hidden" value = "${list.vehicleCompany}" name = "vehicleCompany"/>
					<input type = "hidden" value = "${list.vehicleModel}" name = "vehicleModel"/>
					<input type = "hidden" value = "${list.seats}" name = "seats"/>
					<input type = "hidden" value = "${list.status}" name = "status"/>
					<input type = "hidden" value = "${list.addedBy}" name = "addedBy"/>
					<input type = "hidden" value = "${list.approvedBy}" name = "approvedBy"/>
					<input type = "hidden" value = "${list.rejectedBy}" name = "rejectedBy"/>
					<input type = "hidden" value = "${list.blockedBy}" name = "blockedBy"/>
					<input type = "hidden" value = "${list.unblockedBy}" name = unblockedBy/>
					<input type = "hidden" value = "${list.base64Image}" name = "base64Image"/>
					<td><input type = "submit" class = "submitButton" value="Approve" formaction = "confirmApproval"/></td>
					<td><input type = "submit" class = "submitButton" value="Reject" formaction = "confirmReject"/></td>
				</tr>
			</form:form>	
		 </c:forEach>
	</table>
</div>	
</body>
</html>