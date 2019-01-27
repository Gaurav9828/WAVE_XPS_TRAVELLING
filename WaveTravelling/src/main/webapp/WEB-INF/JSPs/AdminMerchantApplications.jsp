<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="wave.spring.Constants.SystemConstants"%>
<%@page import="wave.spring.model.MerchantDetails"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/themes/CSS/list_table_Style_Sheet.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/themes/javaScript/loginFormValidation.js"></script>
</head>
<body>
<div align = "center">
	<%
		List<MerchantDetails> applicantList = null;
			try{
		applicantList = (List<MerchantDetails>) session.getAttribute(SystemConstants.LIST);
			}catch(NullPointerException e){
			}
	%>
	<table class = "adminLoginTable">
		<tr>
			 <th colspan="1" align = "left"><t style="color:white; font-size:20px;">Merchant Application</t></th>
			 <th colspan="3" align = "left">
				<errorMsg><span id="errorMsg"></span>${message}</errorMsg>
				<seccessMsg>${successMessage}</seccessMsg>
			</th>
		</tr>
		<tr align = "center">
			<th class = "formText">Name</th>
			<th class = "formText">Submission Date</th>
			<th class = "formText">View</th>
			<th class = "formText">Reject</th>
		</tr>
			<%
				if(applicantList == null){
			%>
			
		   <%
					   	}else{
					   			   		   	for(MerchantDetails mDetails : applicantList){
					   %>
		<form:form id="applilcationForm" modelAttribute="AdminMerchantApplications"  method="post">
			<tr align = "center">
			<%request.setAttribute(SystemConstants.LIST, applicantList);%>		
			<input name="merchantId" type="hidden" value = "<%out.print(mDetails.getMarchantId());%>"/>		
			<td><%out.print(mDetails.getFirstName()+" "+mDetails.getLastName()); %></td>
			<td><%out.print(mDetails.getSubmissionDate());  %></td>
			<td><input type = "submit" class = "submitButton" value="View" formaction = "merchantApplicationsView"/></td>
			<td><input type = "submit" class = "submitButton" value="Reject" formaction = "confirmRejectApplication"/></td>
			</tr>
		</form:form>	
		   	<%} 
         }%>
	</table>
</div>	

</body>
</html>