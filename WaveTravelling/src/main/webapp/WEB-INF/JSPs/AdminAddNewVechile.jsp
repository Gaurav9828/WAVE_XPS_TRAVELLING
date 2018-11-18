<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/themes/CSS/Registration_Style_Sheet.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/themes/javaScript/normalFormValidation.js"></script>
</head>
<body>

	<div align = "center">
		<form:form id="newVechileAdditionForm" modelAttribute="AdminAddNewVechile" method="POST" onsubmit="return validateAddNewVechile()" >
			<table class = "adminLoginTable">
				<tr>
					<th colspan="4" align = "left"><errorMsg><span id="errorMsg"></span>
							${message}
						</errorMsg>
						<seccessMsg>${successMessage}</seccessMsg>
					</th>
				</tr>
				<tr>
					<td class = "formText"><spring:bind path="vechileCompany">Company:</spring:bind></td>
					<td><form:input path="vechileCompany" type="text"/></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td class = "formText"><spring:bind path="vechileModel">Model:</spring:bind></td>
					<td><form:input path="vechileModel" type="text"/></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td class = "formText"><spring:bind path="seats">Seats:</spring:bind></td>
					<td><form:input path="seats" type="text"/></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td class = "formText">Image:</td>
					<td><input name ="image" type="file"/></td>
					<td></td>
					<td></td>
				</tr>	
				<tr>
					<td align = "right"><input type = "submit" class = "submitButton" formaction="newVechileAddProcess"/></td>
					<td><input type = "reset" class = "submitButton"/></td>
					<td></td>
					<td></td>
				</tr>	
			</table>
		</form:form>
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