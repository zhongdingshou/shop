<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="static/images/favicon.ico" rel="shortcut icon"
	type="image/x-icon" media="screen">
<title>消息页</title>
</head>
<%
	request.setCharacterEncoding("utf-8");
String message = request.getParameter("message");
String where = request.getParameter("where");
if (message == null || where == null) {
	response.sendRedirect("index.jsp");
	return;
}
%>
<body>
	<h1 align="center"><%=message%></h1>
</body>
<script type="text/javascript">
window.onload = function() {
	setTimeout(function() {
		window.location.href="<%=where%>
	";
		}, 3000);
	}
</script>
</html>