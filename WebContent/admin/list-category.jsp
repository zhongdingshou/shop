<%@page import="www.shop.com.dao.po.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../template/adminHeader.jsp"%>

<title>商城 - 商品类别管理</title>
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
				<div class="ddzxbt">商品菜单列表</div>
				<table border="1" cellspacing="1" cellpadding="1">
					<tr>
						<th>ID</th>
						<th>商品类别名称</th>
						<th>介绍</th>
						<th>操作</th>
					</tr>
					<%
						for (Category category : categories) {
					%>
					<tr>
						<td><%=category.getId()%></td>
						<td><%=category.getName()%></td>
						<td><%=category.getRepresent()%></td>
						<td><a
							href="../CategoryServlet?action=getById&id=<%=category.getId()%>">修改</a>
							| <a
							href="../CategoryServlet?action=delete&id=<%=category.getId()%>"
							onclick="return confirm('警告！！\n确定删除吗？确定将删除该分类以及其所属的全部商品')">删除</a></td>
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