<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/themes/javaScript/normalFormValidation.js"></script>
</head>
<body>
	<div align = "center">
	<form:form id="addVehicleForm" modelAttribute="MerchantAddVehicle" action="addVehicle" method="post" onsubmit="return validateAddVehicleForm()">
	<table class = "adminLoginTable">
		<tr>
			<th colspan="2" align = "left"><t style="color:white; font-size:20px;">Add Vehicle&nbsp&nbsp&nbsp</t>
				<errorMsg><span id="errorMsg"></span>${message}</errorMsg>
				<seccessMsg>${successMessage}</seccessMsg>
			</th>
		</tr>
		<tr>
			<td><spring:bind path="vehicleCompany">Vehicle Company</spring:bind></td>
					<td><form:select path="vehicleCompany" type="text" id="sel1" onchange="giveSelection(this.value)">
						<option value="" disabled selected></option>
						<c:forEach items="${vehicleCompany}" var="vehicleCompany">
							<option value="${vehicleCompany}"> ${vehicleCompany}</option>
						</c:forEach>			
					</form:select></td>
		</tr>
		<tr>
			<td><spring:bind path="vehicleModel">Vehicle Model</spring:bind></td>
					<td><form:select path="vehicleModel" type="text" id="sel2">
						<c:forEach items="${list}" var="list">
							<option value="${list.get(1)}" data-option="${list.get(0)}"> ${list.get(1)}</option>
						</c:forEach>			
					</form:select></td>
		</tr>
		<tr>			
			<td><spring:bind path="vehicleNumber">Vehicle Number</spring:bind></td>
			<td><form:input path="vehicleNumber" type="text" placeholder = "UP 53 Q 2838"/></td>
		</tr>
		<tr>
			<td align = "right"><input type = "submit" class = "submitButton" value="<%out.print(AdminConstantsI.SUBMIT);%>"/></td>
			<td align = "left"><input type = "reset" class = "submitButton" value="<%out.print(AdminConstantsI.RESET);%>"/></td>
		</tr>
	</table>
	</form:form>
	</div>
	<script>
		var sel1 = document.querySelector('#sel1');
		var sel2 = document.querySelector('#sel2');
		var options2 = sel2.querySelectorAll('option');

		function giveSelection(selValue) {
	  		sel2.innerHTML = '';
	  		for(var i = 0; i < options2.length; i++) {
	    	if(options2[i].dataset.option === selValue) {
	      		sel2.appendChild(options2[i]);
	    	}
	  	}
		}

	giveSelection(sel1.value);
	
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