<%@page import="www.shop.com.dao.po.Goods"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../template/adminHeader.jsp"%>

<title>商城 - 商品管理</title>
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
				<div class="ddzxbt">商品添加</div>
				<form action="../GoodsServlet?action=add" method="post">
					名称：<input type="text" name="goodsname" value="" /><br /> 介绍：<input
						type="text" name="represent" value="" /><br /> 价格：<input
						type="number" name="money" value="" /><br /> 分类：<select
						name="category">
						<%
							for (Category category : categories) {
						%>
						<option value="<%=category.getId()%>"><%=category.getName()%></option>
						<%
							}
						%>
					</select><br /> 状态：<input type="radio" name="status" value="1" checked />上架
					<input type="radio" name="status" value="2" />下架<br /> <input
						type="submit" value="添加" /> <input type="reset" value="重置" /><br />
					<a href="list-goods.jsp"
						style="margin: 14px auto; display: block; width: 60px; color: #FF6700">返回</a>
				</form>
				<%
					} else {
					Goods goodsDetail = (Goods) session.getAttribute("goodsDetail");
				%>
				<div class="ddzxbt">商品编辑</div>
				<form action="../GoodsServlet?action=update" method="post">
					<input type="hidden" name="id" value="<%=goodsDetail.getId()%>" /><br />
					名称：<input type="text" name="goodsname"
						value="<%=goodsDetail.getName()%>" /><br /> 介绍：<input
						type="text" name="represent"
						value="<%=goodsDetail.getRepresent()%>" /><br /> 价格：<input
						type="number" name="money" value="<%=goodsDetail.getMoney()%>" /><br />
					分类：<select name="category">
						<%
							for (Category category : categories) {
							if (goodsDetail.getCategoryId() == category.getId()) {
						%>
						<option value="<%=category.getId()%>" selected="selected"><%=category.getName()%></option>
						<%
							}
						%>
						<option value="<%=category.getId()%>"><%=category.getName()%></option>
						<%
							}
						%>
					</select><br /> 状态：<input type="radio" name="status" value="1"
						<%=goodsDetail.getStatus() == 1 ? "checked" : ""%> />上架 <input
						type="radio" name="status" value="2"
						<%=goodsDetail.getStatus() == 2 ? "checked" : ""%> />下架<br /> <input
						type="submit" value="修改" /> <input type="reset" value="重置" /><br />
					<a href="list-goods.jsp"
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