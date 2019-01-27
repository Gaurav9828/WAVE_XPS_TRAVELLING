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
		
		<tr align = "center">
			<th>Company</th>
			<th>Model</th>
			<th>Seats</th>
			<th>Image</th>
			<th>Status</th>
		</tr>
		<c:forEach items="${list}" var="list">
			<tr align = "center">
				<td>${list.vehicleCompany}</td>
				<td>${list.vehicleModel}</td>
				<td>${list.seats}</td>
				<td><img src="data:image/jpg;base64,${list.base64Image}" width="230" height="130" /></td>
				<c:choose>
					<c:when test="${list.status == 'AA'}">
						<td style="color:#40FF00;">Active</td>
					</c:when>
					<c:when test="${list.status == 'BL'}">
						<td style="color:red;">Blocked</td>
					</c:when>
					<c:when test="${list.status == 'RJ'}">
						<td style="color:red;">Rejected</td>
					</c:when>
					<c:otherwise>
						<td style="color:white;">Waiting</td>
					</c:otherwise>
				</c:choose>
			</tr>
			
		</c:forEach>
	</table>
</div>	
</body>
</html>