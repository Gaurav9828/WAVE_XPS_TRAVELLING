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

<frameset name="loginPage" rows="5%, 95%">
	<frame src="${pageContext.request.contextPath}/adminProfileDetails">
	<frameset cols="15%, 80%">
		<frame name="menuFrame"
			src="${pageContext.request.contextPath}/AdminWelcome">
		<frame name="workFrame"
			src="${pageContext.request.contextPath}/welcomeAdminPage">
	</frameset>
</frameset>
</head>
<body>
</body>
</html>
