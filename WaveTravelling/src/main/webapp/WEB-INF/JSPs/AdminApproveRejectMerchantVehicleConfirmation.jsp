<%@page import="wave.spring.Constants.AdminMessageConstants"%>
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
<div align = "center">
	<table class = "adminLoginTable">
				<tr>
					<th colspan="9" align = "left" ><errorMsg><span id="errorMsg"></span>
							${message}
						</errorMsg>
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
			<%if(request.getAttribute("reqType").equals("Approve")){ %>
				<th class = "formText">Approve</th>
			<%}else{ %>
				<th class = "formText">Resaon</th>
				<th class = "formText">Reject</th>
			<%} %>
			<th class = "formText">Back</th>
		</tr>
		<form:form id="approvals" modelAttribute="AdminApproveRejectConfirmation"  method="post" >
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
				<%if(request.getAttribute("reqType").equals("Approve")){%>
					<td><input type = "submit" class = "submitButton" value="Confirm" formaction="AdminApproveConfirmationOfVehicle"/></td>
				<%}else{ %>	
					<td><select name="message" type="text">
							<option value="<%out.print(AdminMessageConstants.INVALID_OWNER);%>"><%out.print(AdminMessageConstants.INVALID_OWNER);%></option>
							<option value="<%out.print(AdminMessageConstants.INSURENCE_PAPER_ERROR);%>"><%out.print(AdminMessageConstants.INSURENCE_PAPER_ERROR);%></option>		
							<option value="<%out.print(AdminMessageConstants.POLUTION_PAPER_ERROR);%>"><%out.print(AdminMessageConstants.POLUTION_PAPER_ERROR);%></option>
							<option value="<%out.print(AdminMessageConstants.INVALID_VEHICLE_NUMBER);%>"><%out.print(AdminMessageConstants.INVALID_VEHICLE_NUMBER);%>
					</select></td>
					<td><input type = "submit" class = "submitButton" value="Reject" formaction="AdminRejectConfirmationOfVehicle"/></td>
				<%} %>
				<td><input type = "submit" class = "submitButton" value="Back" formaction="vehicleApprovalRequests" /></td>	
			</tr>
		</form:form>	
	</table>
</div>	
</body>
</html>