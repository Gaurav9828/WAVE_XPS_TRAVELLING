<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
			 <th colspan="1" align = "left"><t style="color:white; font-size:20px;">My Vehicle</t></th>
			 <th colspan="5" align = "left">
				<errorMsg><span id="errorMsg"></span>${message}</errorMsg>
				<seccessMsg>${successMessage}</seccessMsg>
			</th>
		</tr>
		<tr align = "center">
			<th>Vehicle Id</th>
			<th>Company</th>
			<th>Model</th>
			<th>Vehicle Number</th>
			<th>Status</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${list}" var="list">
		<form:form id="approvals" modelAttribute="AdminApproveRejectConfirmation" action ="myVehicleAction" method="post" >
			<tr align = "center">
				<td>${list.vehicleId}</td>
				<td>${list.vehicleCompany}</td>
				<td>${list.vehicleModel}</td>
				<td>${list.vehicleNumber}</td>
				<c:choose>
					<c:when test="${list.status == 'AA'}">
						<td style="color:#40FF00;">${list.message }</td>
						<td><input style="background:red;" type = "submit" class = "submitButton" value="Block" name = "reqType"/></td>
					</c:when>
					<c:when test="${list.status == 'BL'}">
						<td style="color:red;">${list.message }</td>
						<td><input type = "submit" class = "submitButton" value="Unblock" name = "reqType"/></td>
					</c:when>
					<c:otherwise>
						<td>${list.message }</td>
						<td><input type = "submit" class = "submitButton" value="Delete" name = "reqType"/></td>
					</c:otherwise>
				</c:choose>
			</tr>
			<input type = "hidden" name = "vehicleId" value = "${list.vehicleId}"/>
			<input type = "hidden" name = "vehicleNumber" value = "${list.vehicleNumber}"/>
			<input type = "hidden" name = "vehicleCompany" value = "${list.vehicleCompany}"/>
			<input type = "hidden" name = "vehicleModel" value = "${list.vehicleModel}"/>
			<input type = "hidden" name = "ownerId" value = "${list.ownerId}"/>
			<input type = "hidden" name = "ownerName" value = "${list.ownerName}"/>
			<input type = "hidden" name = "approvedBy" value = "${list.approvedBy}"/>
			<input type = "hidden" name = "rejectedBy" value = "${list.rejectedBy}"/>
			<input type = "hidden" name = "addedOn" value = "${list.addedOn}"/>
			
		</form:form>	
		</c:forEach>
	</table>
</div>	
</body>
</html>