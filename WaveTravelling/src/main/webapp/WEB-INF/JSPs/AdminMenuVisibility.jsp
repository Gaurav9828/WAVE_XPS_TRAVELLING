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
	<table class = "adminLoginTable">
		<tr>
			<th colspan="2" align = "left"><t style="color:white; font-size:20px;">Menu Visibility &nbsp&nbsp&nbsp</t>
				<errorMsg><span id="errorMsg"></span>${message}</errorMsg>
				<seccessMsg>${successMessage}</seccessMsg>
			</th>
		</tr>
		<tr>
			<th>Menu Name</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${menuAdmin}" var="menuAdmin">
		<tr>
			<th colspan="2">
				${menuAdmin}
			</th>
		</tr>
			<c:forEach items="${list}" var="list">
				<c:choose>
					<c:when test="${list.get(0) == menuAdmin }">
					<form:form id="addVehicleForm" modelAttribute="menuDetails" action="updateMenuDetails" method="post" onsubmit="return validateAddVehicleForm()">
						<input type="hidden" name = "menuId" value = "${list.get(3)}"/>
						<input type="hidden" name = "menuName" value = "${list.get(1)}"/>
						<input type="hidden" name = "menuAction" value = "${list.get(4)}"/>
						<input type="hidden" name = "menuAdminLevel" value = "${list.get(6)}"/>
						<input type="hidden" name = "menuDomain" value = "${list.get(5)}"/>
						<tr><td>${list.get(1)}</td>
							<td><c:choose>
								<c:when test="${list.get(2) == 'Y'}">
									<input style="background:red;" type = "submit" class = "submitButton" name = "menuVisibility" value="<%out.print(AdminConstantsI.HIDE);%>"/>
								</c:when>
								<c:otherwise>
									<input type = "submit" class = "submitButton" name = "menuVisibility" value="<%out.print(AdminConstantsI.VISIBLE);%>"/>
								</c:otherwise>
							</c:choose>	
							</td>							
						</tr>
							</form:form>
					</c:when>
				</c:choose>
			</c:forEach>
		</c:forEach>	
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