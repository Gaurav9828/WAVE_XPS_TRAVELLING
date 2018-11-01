<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "wave.spring.model.EmployeeDetails" %>
<%@page import = "wave.spring.model.Employee1MenuList" %>
<%@page import = "wave.spring.Constants.AdminConstantsI" %>
<%@page import = "java.util.List" %>
<%@page import = "java.util.ArrayList" %>
<%@page import="wave.spring.Constants.SystemConstants"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>
	<%out.print(SystemConstants.PROJECT_NAME);%>
</title>
<link rel="icon" type="image/jpg"
	href="${pageContext.request.contextPath}/resources/Images/Wave_Icon.png">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/themes/CSS/Style_Sheet_Two.css">
</head>
<body style="background-color: #f5f5f5;">
	<%
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	response.setHeader("Pragma","no-cache");
	response.setHeader("Expires","0");	
	EmployeeDetails employeeDetails = null;
	String Fname = "",
			Lname = "",
			Profile = "", 
			PIN = "",
			emailId = "";
	int notification = 0,
		 bal = 0,
		 N = 0;
	List<List<String>> menuList = new  ArrayList(); 
	List<String> menuDomain = new  ArrayList();
	try{
		employeeDetails =  (EmployeeDetails)session.getAttribute(AdminConstantsI.EMPLOYEE_DETAILS); 
		menuList = (List<List<String>>)session.getAttribute(AdminConstantsI.EMPLOYEE_MENU_LIST);
		menuDomain = (List<String>)session.getAttribute(AdminConstantsI.EMPLOYEE_MENU_DOMAIN_LIST);
		%>
		<div class = "sidenav">
		<%for(String menuDomainValue : menuDomain){%>
			<button class="dropdown-btn"><%out.print(menuDomainValue); %><i class="fa fa-caret-down"></i></button>
		  	<div class="dropdown-container">
		    	<%for(List<String> sList : menuList){
		    	 	if(sList.get(2).equals(menuDomainValue)){%>
		    		<form id="menuForm" modelAttribute="AdminWelcome" action="<%out.print(sList.get(1)); %>" method="post" target = "workFrame">
		    			<input class = "dropbtnLevel2" type = "submit" value = "<%out.print(sList.get(0)); %>" />
		    		</form><%
		    		}
		  		}
		    	%>
		    	</div>
<%
			}
		}catch(Exception e){
			e.printStackTrace();
			return;
		}%>	
		<form id="menuForm" modelAttribute="AdminWelcome" action="logout" method="post" target = "loginPage">
		    	<input class = "logoutButton" type = "submit" value = "Logout" />
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
</body>
</html>