<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="wave.spring.Constants.SystemConstants"%>
<%@page import="wave.spring.Constants.AdminConstantsI"%>
<%@page import="wave.spring.model.MerchantDetails"%>
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
		MerchantDetails merchantDetails = null;
			try{
		merchantDetails = (MerchantDetails) request.getAttribute(SystemConstants.MERCHANT);
			}catch(NullPointerException e){
			}
	%>
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
			<td><%out.print(merchantDetails.getFirstName());%></td>
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
		<form:form id="applilcationForm" modelAttribute="AdminMerchantApplicationView"  method="post"  onsubmit = "return loadPage()">
		<input name="merchantId" type="hidden" value = "<%out.print(merchantDetails.getMarchantId());%>"/>
		<tr>
			<td></td>
			<td align = "right"><input type = "submit" class = "submitButton" value="Submit" formaction = "merchantApplicationVerified"/>
			<i class="fa fa-spinner fa-spin"></i></td>
			<td><input type = "submit" class = "submitButton" value="Back" formaction = "backMerchantApplications"/></td>
			<td></td>
		</tr>
		</form:form>
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