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
<body>
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
	try{
		employeeDetails =  (EmployeeDetails)session.getAttribute(AdminConstantsI.EMPLOYEE_DETAILS); 
		menuList = (List<List<String>>)session.getAttribute(AdminConstantsI.EMPLOYEE_MENU_LIST);
		out.print(employeeDetails.getName());
		%>
		
		<div class="dropdown">
		<button class="dropbtn">Dropdown</button>
	  	<div class="dropdown-content">
	    	<%for(List<String> sList : menuList){%>
	    		<form id="menuForm" modelAttribute="AdminWelcome" action="<%out.print(sList.get(1)); %>" method="post">
	    			<input type = "submit" value = "<%out.print(sList.get(0)); %>" />
	    		</form><%
	    		}

	}catch(Exception e){
		e.printStackTrace();
		return;
	}
    	%>	
  	</div>
</div>
</body>
</html>