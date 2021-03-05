<%@page import="www.shop.com.dao.po.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../template/adminHeader.jsp"%>

<title>商城 - 用户管理</title>
<style>
<!--
.rtcont form {
	width: 960px;
	text-align: center;
	margin: 20px auto;
}

.rtcont input {
	margin-top: 10px;
}
-->
</style>
</head>
<%
	String how = request.getParameter("how");
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
				<%
					if (how == null) {
				%>
				<div class="ddzxbt">用户添加</div>
				<form action="../UserServlet?action=add" method="post">
					账号：<input type="text" name="username" required value="" /><br />
					密码：<input type="text" name="password" required value="" /><br />
					身份：<input type="radio" name="rule" value="2" />管理员 <input
						type="radio" name="rule" value="1" checked />普通用户<br /> 电话：<input
						type="text" name="phone" value="" maxlength="11" /><br /> 收货地址：<input
						type="text" name="address" value="" /><br /> <input
						type="submit" value="添加" /> <input type="reset" value="重置" /><br />
					<a href="list-user.jsp"
						style="margin: 14px auto; display: block; width: 60px; color: #FF6700">返回</a>
				</form>
				<%
					} else {
					User editUser = (User) session.getAttribute("editUser");
				%>
				<div class="ddzxbt">用户编辑</div>
				<form action="../UserServlet?action=update" method="post">
					<input type="hidden" name="userid" value="<%=editUser.getId()%>" /><br />
					账号：<input type="text" name="username"
						value="<%=editUser.getUsername()%>" /><br /> 密码：<input
						type="password" name="password"
						value="<%=editUser.getPassword()%>" /><br /> 身份：<input
						type="radio" name="rule" value="2"
						<%=editUser.getRule() == 2 ? "checked" : ""%> />管理员 <input
						type="radio" name="rule" value="1"
						<%=editUser.getRule() == 1 ? "checked" : ""%> />普通用户<br /> 电话：<input
						type="text" name="phone" value="<%=editUser.getPhone()%>"
						maxlength="11" /><br /> 收货地址：<input type="text" name="address"
						value="<%=editUser.getAddress()%>" /><br /> <input type="submit"
						value="修改" /> <input type="reset" value="重置" /><br /> <a
						href="list-user.jsp"
						style="margin: 14px auto; display: block; width: 60px; color: #FF6700">返回</a>
				</form>
				<%
					}
				%>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<!-- self_info -->

	<%@ include file="../template/footer.jsp"%>
</body>
</html>