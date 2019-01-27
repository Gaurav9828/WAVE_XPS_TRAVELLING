<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page import="wave.spring.Constants.SystemConstants"%>
<%@page import="wave.spring.Constants.AdminConstantsI"%>
<%@page import="wave.spring.security.SecurityI"%>
<%@page import="wave.spring.security.Security"%>
<%@page import="java.util.HashMap" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>
	<%out.print(SystemConstants.PROJECT_NAME);%>
</title>
<link rel="icon" type="image/jpg" href="${pageContext.request.contextPath}/resources/Images/Wave_Icon.png">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/themes/CSS/Style_Sheet.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/themes/javaScript/loginFormValidation.js"></script>
</head>
<script type="text/javascript">
    window.history.forward();
    function noBack() { 
    	window.history.forward(); }
</script>
<body>
	<%
	SecurityI security = new Security();
	  HashMap<String,String> map = new HashMap();
	  map = security.generateCaptcha();%>
	<div align = "right">
		<img alt="project_name" style = "width:300px; height:170px;" src="${pageContext.request.contextPath}/resources/Images/Wave_Page_Img.png"><br>
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
		<form:form id="loginForm" modelAttribute="AdminLogin" action="WAVE" method="post" onsubmit="return validateAdminLoginForm()">
			<table class = "adminLoginTable">
				<tr>
					<th colspan="2" align = "left"><errorMsg><span id="errorMsg"></span>
							${message}
						</errorMsg>
						<seccessMsg>${successMessage}</seccessMsg>
					</th>
				</tr>
				<tr align = "left">
					<td>
						<pageName><%out.print(AdminConstantsI.ADMIN_LOGIN);%></pageName>
					</td>
					<td align = "right">
						<a href="${pageContext.request.contextPath}/AdminLogin">
						<img alt="project_name" style = "width:50px; height:40px;" 
						src="${pageContext.request.contextPath}/resources/Images/refresh_icon.png"></a>
					</td>
				</tr>
				<tr>
					<td><spring:bind path="authValue1"><%out.print(AdminConstantsI.EMP_ID);%></spring:bind></td>
					<td><form:input path="authValue1" type="text"/></td>
				</tr>
				<tr>
					<td><spring:bind path="authValue2"><%out.print(AdminConstantsI.PASSWORD);%></spring:bind></td>
					<td><form:input path="authValue2" type="password"/></td>
				</tr>
				<tr>
					<td><input type = "submit" value = "<%out.print(map.get(SystemConstants.CAPTCHA));%>" class = "captcha" disabled>
					</td>
					<td><input name="captcha" type="text" autocomplete="off"/></td>
					<td><input type="hidden" value = "<%out.print(map.get(SystemConstants.CAPTCHA));%>" name = "keyCaptcha"/>
				</tr>
				<tr>
					<td align = "right"><input type = "submit" class = "submitButton" value="<%out.print(AdminConstantsI.LOGIN);%>"/></td>
					<td align = "left"><input type = "reset" class = "submitButton" value="<%out.print(AdminConstantsI.RESET);%>"/></td>
				</tr>
			</table>
		</form:form>
		<a href = "${pageContext.request.contextPath}/AdminMemorableWordPasswordReset" style = "color:white;">
		<%out.print(AdminConstantsI.RESET_PASSWORD);%></a>
	</div>
	<div align = "center" style = " background-color: #000;
  background-color: rgba(0, 0, 0, 0.7);">
		
</div>
</body>
</html>