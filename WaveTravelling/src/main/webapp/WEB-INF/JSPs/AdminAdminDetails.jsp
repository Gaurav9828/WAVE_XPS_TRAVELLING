<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="wave.spring.Constants.AdminConstantsI"%>
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
	<div align="center">
		<table class="adminLoginTable">
			<tr>
			 	<th colspan="1" align = "left"><t style="color:white; font-size:20px;">Admin Details &nbsp&nbsp&nbsp</t></th>
			 	<th colspan="5" align = "left">
					<errorMsg><span id="errorMsg"></span>${message}</errorMsg>
					<seccessMsg>${successMessage}</seccessMsg>
			 	</th>
			</tr>
			<tr align="center">
				<th><%out.print(AdminConstantsI.NAME); %></th>
				<th><%out.print(AdminConstantsI.EMP_ID); %></th>
				<th><%out.print(AdminConstantsI.EMAIL_ID); %></th>
				<th><%out.print(AdminConstantsI.MOBILE_NUMBER); %></th>
				<th><%out.print(AdminConstantsI.ADMIN_LEVEL); %></th>
				<th><%out.print(AdminConstantsI.ACTION); %></th>
			</tr>
			<c:forEach items="${list}" var="list">
				<tr align="center">
						<td>${list.firstName} &nbsp ${list.lastName}</td>
						<td>${list.employeeId}</td>
						<td>${list.emailId}</td>
						<td>${list.mobileNumber}</td>
						<td>
							<c:choose>
								<c:when test="${list.adminLevel == '2'}">
									<%out.print(AdminConstantsI.NORMAL_ADMIN);%>
								</c:when>
								<c:otherwise>
									<%out.print(AdminConstantsI.SUPER_ADMIN);%>
								</c:otherwise>
							</c:choose>
						</td>
						<form:form action = "blockUnblockAdmin" method = "POST">
							<input type = "hidden" name="userId" value="${list.userId}" />
							<input type = "hidden" name="employeeId" value="${list.employeeId }" />
							<input type = "hidden" name="mobileNumber" value="${list.mobileNumber }" />
							<input type = "hidden" name="password" value="${list.password }" />
							<input type = "hidden" name="memorableWord" value="${list.memorableWord }" />
							<input type = "hidden" name="adminLevel" value="${list.adminLevel }" />
							<input type = "hidden" name="firstName" value="${list.firstName }" />
							<input type = "hidden" name="lastName" value="${list.lastName }" />
							<input type = "hidden" name="emailId" value="${list.emailId }" />
							<input type = "hidden" name="lastLogin" value="${list.lastLogin }" />
							<input type = "hidden" name="lastLogout" value="${list.lastLogout }" />
							<input type = "hidden" name="invalidPasswordAttempts" value="${list.invalidPasswordAttempts }" />
							<input type = "hidden" name="addedByEmpId" value="${list.addedByEmpId }" />
							<td>
								<c:choose>
									<c:when test="${list.status == 'Y'}">
										<input type = "hidden" name="status" value="N"/>
										<input type="submit" class="submitButton" value="Block" style="background-color: red; color: white;"/>
									</c:when>
									<c:otherwise>
										<input type = "hidden" name="status" value="Y" />
										<input type="submit" class="submitButton" value="UnBlock"/>
									</c:otherwise>
								</c:choose>
							</td>				
						</form:form>	
					</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>