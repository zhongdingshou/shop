<%@page import="www.shop.com.dao.po.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../template/adminHeader.jsp"%>

<title>商城 - 用户管理</title>
<style>
<!--
table {
	width: 960px;
	text-align: center;
	margin: 20px auto;
}

h1 {
	text-align: center;
	margin: auto;
}

table tr:nth-child(odd) {
	background: #F6D7C2;
}

table tr:nth-child(even) {
	background: #FFF;
}

table tr:nth-child(1) {
	background: #FF6700;
	color: #FFF;
}

table tr:hover {
	background: #FF6700;
	color: #FFF;
}
-->
</style>
</head>
<%
	List<User> allUsers = (List<User>) session.getAttribute("allUsers");
if (allUsers == null) {
	response.sendRedirect("../UserServlet?action=listAll");
	return;
}
%>
<body>
	<!-- start header -->
	<%@ include file="../template/admin-nav.jsp"%>
	<!-- end banner_x -->
	<!-- self_info -->
	<div class="grzxbj">
		<div class="selfinfo center">

			<%@ include file="../template/admin-left.jsp"%>

			<div class="rtcont fr"
				style="height: auto; margin-bottom: 60px; padding-bottom: 60px;">
				<div class="ddzxbt">用户列表</div>
				<table border="1" cellspacing="1" cellpadding="1">
					<tr>
						<th>ID</th>
						<th>用户名</th>
						<th>密码</th>
						<th>身份</th>
						<th>手机</th>
						<th>地址</th>
						<th>操作</th>
					</tr>
					<%
						for (User user : allUsers) {
					%>
					<tr>
						<td><%=user.getId()%></td>
						<td><%=user.getUsername()%></td>
						<%
							if (operator.getId() == user.getId() || operator.getRule() > user.getRule()) {
						%>
						<td><%=user.getPassword()%></td>
						<%
							} else {
						%>
						<td>********</td>
						<%
							}
						%>
						<%
							if (user.getRule() == 2) {
						%>
						<td>管理员</td>
						<%
							} else {
						%>
						<td>用户</td>
						<%
							}
						%>
						<td><%=user.getPhone()%></td>
						<td><%=user.getAddress()%></td>
						<td><a
							href="../UserServlet?action=getById&id=<%=user.getId()%>">修改</a>
							| <a href="../UserServlet?action=delete&id=<%=user.getId()%>"
							onclick="return confirm('确定删除吗？')">删除</a></td>
					</tr>
					<%
						}
					%>
				</table>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<!-- self_info -->

	<%@ include file="../template/footer.jsp"%>
</body>
</html>