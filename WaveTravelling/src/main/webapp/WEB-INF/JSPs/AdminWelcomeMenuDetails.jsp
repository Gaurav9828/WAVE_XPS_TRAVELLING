<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import = "wave.spring.Constants.AdminConstantsI" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/themes/CSS/Style_Sheet_Two.css">
</head>
<script type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</script>
<body style="background-color:#262626;">
<%try{
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	response.setHeader("Pragma","no-cache");
	response.setHeader("Expires","0");	
	}catch(Exception e){
		e.printStackTrace();
		
	}
%>
	<div class = "sidenav">
		<c:forEach items = "${employeeMenuDomainList}" var = "employeeMenuDomainList">
			<button class="dropdown-btn">${employeeMenuDomainList}<i class="fa fa-caret-down"></i></button>
	  		<div class="dropdown-container" style="background-color:#262626;">
	    		<c:forEach items="${employeeMenuList}" var="employeeMenuList">
	    				<c:if test = "${employeeMenuList.get(2) == employeeMenuDomainList}">
	    					<form id="menuForm" action="${employeeMenuList.get(1)}" method="post" target = "workFrame" >
	    						<input class = "dropbtnLevel2" type = "submit" value = "${employeeMenuList.get(0)}"/>
	    					</form>
	    				</c:if>
	  			</c:forEach>
	    	</div>
		</c:forEach>		
		<form id="menuForm" action="Logout" method="post" target = "loginPage">
	    	<input class = "logoutButton" type = "submit" value = "Logout"/>
		</form>
	</div>	
	<script>
var dropdown = document.getElementsByClassName("dropdown-btn");
var i;

for (i = 0; i < dropdown.length; i++) {
  dropdown[i].addEventListener("click", function() {
    this.classList.toggle("active");
    var dropdownContent = this.nextElementSibling;
    if (dropdownContent.style.display === "block") {
      dropdownContent.style.display = "none";
    } else {
      dropdownContent.style.display = "block";
    }
  });
}

</script>
	<div style="position:absolute; bottom:5px;">
		<t style="color:white; font-size:11px;"><%out.print(AdminConstantsI.DEVELOPER_SIGN);%></t>
	</div>
</body>
</html>