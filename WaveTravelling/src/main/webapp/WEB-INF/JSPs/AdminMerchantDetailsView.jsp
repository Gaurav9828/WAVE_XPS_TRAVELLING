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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/themes/javaScript/loginFormValidation.js"></script>
</head>
<body>
<div align = "center">
	<%MerchantDetails merchantDetails = null;
	try{
		merchantDetails = (MerchantDetails) request.getAttribute(SystemConstants.MERCHANT_DETAILS);
	}catch(NullPointerException e){
	}%>
	<table class = "adminLoginTable">
		<tr>
			<th align = "left" colspan = "1" style = "color:white; font-size:20px;">Applicant Details</th>
			<th colspan="4" align = "left" ><errorMsg><span id="errorMsg"></span>
							${message}
						</errorMsg>
						<seccessMsg>${successMessage}</seccessMsg>
					</th>
		</tr>
		<tr>
			<td><%out.print(AdminConstantsI.FIRST_NAME);%></td>
			<td><%out.print(merchantDetails.getFirstName()); %></td>
			<td><%out.print(AdminConstantsI.LAST_NAME);%></td>
			<td><%out.print(merchantDetails.getLastName()); %></td>
		</tr>
		<tr>
			<td><%out.print(AdminConstantsI.MOBILE_NUMBER);%></td>
			<td><%out.print(merchantDetails.getMobileNumber()); %></td>	
			<td><%out.print(AdminConstantsI.EMAIL_ID);%></td>
			<td><%out.print(merchantDetails.getEmailId()); %></td>	
		</tr>
		<tr>
			<td>City</td>
			<td><%out.print(merchantDetails.getCity()); %></td>
			<td><%out.print(AdminConstantsI.PIN_CODE);%></td>
			<td><%out.print(merchantDetails.getPinCode()); %></td>
		</tr>	
		<tr>
			<td><%out.print(merchantDetails.getIdentityType()+" "); %>Number</td>
			<td><%out.print(merchantDetails.getIdentityNumber()); %></td>
			<td><%out.print(AdminConstantsI.LAND_MARK);%></td>
			<td><%out.print(merchantDetails.getLandMark()); %></td>
		</tr>
		<form:form id="applilcationForm" modelAttribute="AdminMerchantApplicationView"  method="post" >
		<input name="merchantId" type="hidden" value = "<%out.print(merchantDetails.getMarchantId());%>"/>
		<tr>
			<td  <%if(merchantDetails.getStatus().equals(AdminConstantsI.BLACK_LISTED_REQUEST)){%>style = "color:red;"<%}
			else if(merchantDetails.getStatus().equals(AdminConstantsI.UNDER_BLACKLIST_PROCESS) || merchantDetails.getStatus().equals(AdminConstantsI.UNDER_CANCLE_BLACKLIST_PROCESS))
			{%>style = "color:yellow;"<%} %>>
			<% if(merchantDetails.getStatus().equals(AdminConstantsI.BLACK_LISTED_REQUEST)){
			out.print("Black Listed");
			}else if(merchantDetails.getStatus().equals(AdminConstantsI.UNDER_BLACKLIST_PROCESS)){
				out.print("Under Black List Process");
			}else if(merchantDetails.getStatus().equals(AdminConstantsI.UNDER_CANCLE_BLACKLIST_PROCESS)){
				out.print("Under Black Listed Cancellation");
			}
			%>
			</td>
			<td align = "right"><input name = "submit" type = "submit" class = "submitButton" value=
			<% if(merchantDetails.getStatus().equals(AdminConstantsI.BLACK_LISTED_REQUEST)){
				out.print("Unblock");
			}else if(merchantDetails.getStatus().equals(AdminConstantsI.UNDER_BLACKLIST_PROCESS)){
				out.print("Undo");
			}else if(merchantDetails.getStatus().equals(AdminConstantsI.UNDER_CANCLE_BLACKLIST_PROCESS)){
				out.print("Cancle");
			}else{
				out.print("Block");
			}
			%> formaction = "blockUnblockMerchantProcess"/></td>
			<td align = "left"><input type = "submit" class = "submitButton" value="Back" formaction = "backAdminMerchantDetails"/></td>
			
			<td></td>
		</tr>
		</form:form>
	</table>
</div>	
</body>
</html>