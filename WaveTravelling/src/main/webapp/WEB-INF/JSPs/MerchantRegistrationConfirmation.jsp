<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="wave.spring.Constants.SystemConstants"%>
<%@page import="wave.spring.Constants.AdminConstantsI"%>
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
	href="${pageContext.request.contextPath}/resources/themes/CSS/Registration_Style_Sheet.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/themes/javaScript/loginFormValidation.js"></script>
</head>
<body>
	<div align = center>
		<projectName><%out.print(SystemConstants.PROJECT_NAME_ONE+"\n"+SystemConstants.PROJECT_NAME_TWO); %></projectName><br>
	</div>
	<div align = "center">
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
		<form:form id="merchantRegistrationForm" modelAttribute="MerchantRegistration" method="POST" onsubmit="return loadPage()" >
			<table class = "adminLoginTable">
				<tr>
					<th colspan="4" style="color:white;">Register here to be a part of Wave Xps Travelling and engage your vehicle with us.</th>
				</tr>
				<tr align = "left">
					<th colspan="2">
						<pageName><%out.print(AdminConstantsI.REGISTRATION_REQUEST);%></pageName>
					</th>
					<td></td>
					<td></td>
					<td align = "right">
						<a href="${pageContext.request.contextPath}/Registration">
						<img alt="project_name" style = "width:40px; height:40px;" 
						src="${pageContext.request.contextPath}/resources/Images/back_icon.png"></a>
					</td>
					
				</tr>
				<tr>
					<td><%out.print(AdminConstantsI.FIRST_NAME);%></td>
					<td><input type="text" value = "${list.firstName }" disabled/>
					<input name="firstName" type="hidden" value = "${list.firstName }"/></td>
					<td><%out.print(AdminConstantsI.LAST_NAME);%></td>
					<td><input type="text" value = "${list.lastName }" disabled/>
					<input name="lastName" type="hidden" value = "${list.lastName }" /></td>
				</tr>
				<tr>
					<td><%out.print(AdminConstantsI.MOBILE_NUMBER);%></td>
					<td><input type="text" value = "${list.mobileNumber }" disabled/>
					<input name="mobileNumber" type="hidden" value = "${list.mobileNumber }"/></td>
				</tr>
				<tr>
					<td><%out.print(AdminConstantsI.CITY);%></td>
					<td><input type="text" value = "${list.city }" disabled/>
					<input name="city" type="hidden" value = "${list.city }" /></td>
					<td><%out.print(AdminConstantsI.PIN_CODE);%></td>
					<td><input type="text" value = "${list.pinCode }" disabled/>
					<input name="pinCode" type="hidden" value = "${list.pinCode }" /></td>
				</tr>
				<tr>
					<td><%out.print(AdminConstantsI.CHOOSE_IDENTITY);%></td>
					<td><input type="text" value = "${list.identityType }" disabled/>
					<input name="identityType" type="hidden" value = "${list.identityType }" /></td>
					<td><%out.print(AdminConstantsI.ID_NUMBER);%></td>
					<td><input type="text" value = "${list.identityNumber }" disabled/>
					<input name="identityNumber" type="hidden" value = "${list.identityNumber }"/></td>
				</tr>
				<tr>
					<td><%out.print(AdminConstantsI.LAND_MARK);%></td>
					<td><input class = "textArea" type="text" value = "${list.landMark }" disabled/>
					<input name="landMark" type="hidden" value = "${list.landMark }" /></td>
					<td><%out.print(AdminConstantsI.EMAIL_ID);%></td>
					<td><input type="text" value = "${list.emailId }" disabled/>
					<input name="emailId" type="hidden" value = "${list.emailId }"/></td>
				</tr>
				<tr>
					<td></td>
					<td align = "right"><input type = "submit" class = "submitButton" value="<%out.print(AdminConstantsI.COFIRM);%>" formaction="RegistrationProcess"/></td>
					<td align = "left"><input type = "submit" class = "submitButton" value="<%out.print(AdminConstantsI.BACK);%>" formaction = "Back_To_Registration"/></td>
					<td></td>
				</tr>
			</table>
		</form:form>
	</div>	

</body>
</html>