<%@page import="wave.spring.model.EmailDetails"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="wave.spring.Constants.SystemConstants"%>
<%@page import="wave.spring.Constants.AdminConstantsI"%>
<%@page import="wave.spring.model.MerchantDetails"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>
	<%out.print(SystemConstants.PROJECT_NAME);%>
</title>
<link rel="icon" type="image/jpg"
	href="${pageContext.request.contextPath}/resources/Images/Wave_Icon.png">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/themes/CSS/list_table_Style_Sheet.css">
</head>
<body>
	<%List<EmailDetails> emailDetails = null;
	try{
		emailDetails = (List<EmailDetails>) request.getAttribute(AdminConstantsI.PENDING_MAIL_LIST);
	}catch(NullPointerException e){
	}%>
	<table class = "adminLoginTable">
				<tr style="background-color:transparent;">
					<th colspan="4" align = "left" ><errorMsg><span id="errorMsg"></span>
							${message}
						</errorMsg>
						<seccessMsg>${successMessage}</seccessMsg>
					</th>
				</tr>
		<tr align = "center">
			<th class = "formText">To</th>
			<th class = "formText">Subject</th>
			<th class = "formText">Date/Time</th>
			<th class = "formText">Resend</th>
		</tr>
			<%
			if(emailDetails == null){%>
			
		   <%}else{
		   	for(EmailDetails mDetails : emailDetails){
			
			%>
		<form:form id="applilcationForm" modelAttribute="AdminMerchantDetails"  method="post" >
			<input type = "hidden" name = "<% out.print(AdminConstantsI.TO);%>" value = "<%out.print(mDetails.getDestinationEmailId());%>"/>
			<input type = "hidden" name = "<% out.print(AdminConstantsI.MESSAGE);%>" value = "<%out.print(mDetails.getMessage());%>"/>
			<input type = "hidden" name = "<% out.print(AdminConstantsI.SUBJECT);%>" value = "<%out.print(mDetails.getSubject());%>"/>
			
			<tr align = "center">
			<td><%out.print(mDetails.getDestinationEmailId());%></td>
			<td><%out.print(mDetails.getSubject());%></td>
			<td><%out.print(mDetails.getDateTime());  %></td>
			<td><input type = "submit" class = "submitButton" value="Send" formaction = "resendEmailManually"/></td>
			</tr>
		</form:form>	
		   	<%} 
         }%>
	</table>
</div>	
</body>
</html>