<%@page import="wave.spring.Constants.SystemConstants"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>
	<%
		out.print(SystemConstants.PROJECT_NAME);
	%>
</title>
<link rel="icon" type="image/jpg"
	href="${pageContext.request.contextPath}/resources/Images/Wave_Icon.png">
<frameset name = "loginPage" rows="5%, 95%" frameborder="0" framespacing="0" noresize>
	<frame src="${pageContext.request.contextPath}/adminWelcomeProfileDetails">
	<frameset cols="15%, 80%">
		<frame name="menuFrame" src="${pageContext.request.contextPath}/adminWelcomeMenuDetails">
		<frame name="workFrame" src="${pageContext.request.contextPath}/adminWelcomeWorkingPage">
	</frameset>
</frameset>
</head>
<body onload="noBack();"
    onpageshow="if (event.persisted) noBack();" onunload="">
	<%try{
		if(request.getSession() == null){
			RequestDispatcher rd = request.getRequestDispatcher("error");
		}
	}catch(Exception e){
	}%>
</body>
</html>
