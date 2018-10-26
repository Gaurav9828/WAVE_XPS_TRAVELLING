<frameset name = "loginPage" rows = "5%, 95%">
	<frame src = "${pageContext.request.contextPath}/adminProfileDetails">
	<frameset  cols = "15%, 80%">
		<frame src = "${pageContext.request.contextPath}/AdminWelcome">
		<frame name = "workFrame" src = "${pageContext.request.contextPath}/merchantApplications">
	</frameset>
</frameset>