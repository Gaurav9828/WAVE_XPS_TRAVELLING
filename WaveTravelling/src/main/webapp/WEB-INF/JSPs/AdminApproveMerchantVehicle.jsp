<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/themes/CSS/list_table_Style_Sheet.css">
</head>
<body>
<div align = "center">
	<table class = "adminLoginTable">
		<tr>
			<th colspan="2" align = "left"><t style="color:white; font-size:20px;">Vehicle Approval Request</t></th>
			<th colspan="6" align = "left">
				<errorMsg><span id="errorMsg"></span>${message}</errorMsg>
				<seccessMsg>${successMessage}</seccessMsg>
			</th>
		</tr>
		<tr align = "center">
			<th class = "formText">Vehicle Id</th>
			<th class = "formText">Company</th>
			<th class = "formText">Model</th>
			<th class = "formText">Vehicle Number</th>
			<th class = "formText">Owner Name</th>
			<th class = "formText">Owner Id</th>
			<th class = "formText">Approve</th>
			<th class = "formText">Reject</th>
		</tr>
		<c:forEach items="${list}" var="list">
		<form:form id="approvals" modelAttribute="AdminApproveRejectConfirmation" action ="AdminApproveRejectConfirmationOfVehicle" method="post" >
			<tr align = "center">
				<td>${list.vehicleId}</td>
				<td>${list.vehicleCompany}</td>
				<td>${list.vehicleModel}</td>
				<td>${list.vehicleNumber}</td>
				<td>${list.ownerName}</td>
				<td>${list.ownerId}</td>
				<input type="hidden" value = "${list.vehicleId }" name = "vehicleId"/>
				<input type="hidden" value = "${list.vehicleCompany }" name = "vehicleCompany"/>
				<input type="hidden" value = "${list.vehicleModel }" name = "vehicleModel"/>		
				<input type="hidden" value = "${list.vehicleNumber }" name = "vehicleNumber"/>
				<input type="hidden" value = "${list.ownerName }" name = "ownerName"/>
				<input type="hidden" value = "${list.ownerId }" name = "ownerId"/>
				<td><input type = "submit" class = "submitButton" value="Approve" name = "reqType"/></td>
				<td><input type = "submit" class = "submitButton" value="Reject" name = "reqType"/></td>
			</tr>
		</form:form>	
		</c:forEach>
	</table>
</div>	
</body>
</html>