<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
	<%
	  	EmployeeDetails empDetails = new EmployeeDetails();
		try{
		    empDetails = (EmployeeDetails) session.getAttribute(AdminConstantsI.EMPLOYEE_DETAILS);
		}catch(NullPointerException e){
		}
	%>
	<div align="center">
		<table class="adminLoginTable">
		<%
			if(empDetails.getAdminLevel().equals("3")){
		%>
		<tr>
			<th colspan="1"><t style="color:white; font-size:20px;">Vehicles &nbsp&nbsp&nbsp</t></th>
			<th colspan="5" align = "left" >
				<errorMsg><span id="errorMsg"></span>${message}</errorMsg>
				<seccessMsg>${successMessage}</seccessMsg>
			</th>
		</tr>
		<%
			}
		%>	
			<tr align="center">
				<th>Company</th>
				<th>Model</th>
				<th>Seats</th>
				<th>Image</th>
				<%
					if(empDetails.getAdminLevel().equals("3")){
				%>
				<th>Action</th>
				<%
					}else{
				%>
				<th>Status</th>
				<%
					}
				%>
			</tr>
			<c:forEach items="${list}" var="list">
				<tr align="center">
					<td>${list.vehicleCompany}</td>
					<td>${list.vehicleModel}</td>
					<td>${list.seats}</td>
					<td><img src="data:image/jpg;base64,${list.base64Image}" width="230" height="130" /></td>
					<form:form id="applilcationForm" method="post" action="blockUnblockVehicle">
						<input type = "hidden" value = "${list.ID}" name = "ID"/>
						<input type = "hidden" value = "${list.vehicleCompany}" name = "vehicleCompany"/>
						<input type = "hidden" value = "${list.vehicleModel}" name = "vehicleModel"/>
						<input type = "hidden" value = "${list.seats}" name = "seats"/>
						<input type = "hidden" value = "${list.addedBy}" name = "addedBy"/>
						<input type = "hidden" value = "${list.approvedBy}" name = "approvedBy"/>
						<input type = "hidden" value = "${list.rejectedBy}" name = "rejectedBy"/>
						<input type = "hidden" value = "${list.blockedBy}" name = "blockedBy"/>
						<input type = "hidden" value = "${list.unblockedBy}" name = unblockedBy/>
						<input type = "hidden" value = "${list.base64Image}" name = "base64Image"/>						
						<%if(empDetails.getAdminLevel().equals("3")){ %>
							<td>
								<c:choose>
									<c:when test = "${list.status == 'BL' }">
										<input type="submit" class="submitButton" value="Unblock"/>
										<input type="hidden" value="<%out.print(AdminConstantsI.ALREADY_ADDED);%>" name="status" />
									</c:when>
									<c:when test = "${list.status == 'RJ' }">
										<input type="submit" class="submitButton" value="Undo" style="background-color: red; color: white;" />
										<input type="hidden" value="<%out.print(AdminConstantsI.WAITING_LIST);%>" name="status" />
									</c:when>
									<c:otherwise>
										<input type="submit" class="submitButton" value="Block" style="background-color: red; color: white;" />
										<input type="hidden" value="<%out.print(AdminConstantsI.BLOCKED);%>" name="status" />
									</c:otherwise>
								</c:choose>
							</td>
						<%}else if(empDetails.getAdminLevel().equals("2")){ %>
							<td>
								<c:choose>
									<c:when test="${list.status == 'BL' }">
										<errorMsg>Inactive</errorMsg>
									</c:when>
									<c:when test="${list.status == 'RJ' }">
										<errorMsg>Rejected</errorMsg>
									</c:when>
									<c:otherwise>
										<seccessMsg>Active</seccessMsg>									
									</c:otherwise>
								</c:choose>
							</td>
						<%} %>
					</form:form>						
				</tr>
			</c:forEach>	
		</table>
	</div>
</body>
</html>