<%@page import="www.shop.com.dao.po.Goods"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../template/adminHeader.jsp"%>

<title>商城 - 商品管理</title>
<style>
<!--
table {
	width: 100%;
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

table tr td ul li a {
	display: block;
	min-height: 90px;
	line-height: 90px;
}

table tr td ul li a:hover {
	color: #FFF;
}

table tr td a:hover {
	color: #FFF;
}
-->
</style>
</head>
<%
	List<Goods> myPublishGoods = (List<Goods>) session.getAttribute("myPublishGoods");
if (myPublishGoods == null) {
	response.sendRedirect("../GoodsServlet?action=listAll");
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
				<div class="ddzxbt">我发布的商品列表</div>
				<table border="1" cellspacing="1" cellpadding="1">
					<tr>
						<th>ID</th>
						<th>商品名称</th>
						<th>介绍</th>
						<th>图片</th>
						<th>价格</th>
						<th>分类</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
					<%
						for (Goods goods : myPublishGoods) {
					%>
					<tr>
						<td><%=goods.getId()%></td>
						<td><%=goods.getName()%></td>
						<td><%=goods.getRepresent()%></td>
						<td>
							<ul>
								<li style="display: none;"><a
									href="add-picture.jsp?id=<%=goods.getId()%>">更新图片</a></li>
								<li style="display: list-item;"><img
									style="width: 80px; min-height: 90px;"
									src="../<%=goods.getImg()%>" /></li>
							</ul>
						</td>
						<td>￥<%=goods.getMoney()%> 元
						</td>
						<%
							for (Category category : categories) {
							if (goods.getCategoryId() == category.getId()) {
						%>
						<td><%=category.getName()%></td>
						<%
							}
						}
						%>
						<%
							if (goods.getStatus() == 1) {
						%>
						<td>上架中</td>
						<%
							} else {
						%>
						<td>已下架</td>
						<%
							}
						%>
						<td><a
							href="../GoodsServlet?action=getById&admin=yes&id=<%=goods.getId()%>">修改</a>
							| <a href="../GoodsServlet?action=delete&id=<%=goods.getId()%>"
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
	<script type="text/javascript">
		$("tr ul").mouseover(function() {
			$(this).children().eq(0).show();
			$(this).children().eq(1).hide();
		}).mouseout(function() {
			$(this).children().eq(0).hide();
			$(this).children().eq(1).show();
		});
	</script>
</body>
</html>