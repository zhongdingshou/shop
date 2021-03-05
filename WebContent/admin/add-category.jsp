<%@page import="www.shop.com.dao.po.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../template/adminHeader.jsp"%>

<title>商城 - 商品分类管理</title>
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
				<div class="ddzxbt">商品分类添加</div>
				<form action="../CategoryServlet?action=add" method="post">
					商品类别名称：<input type="text" name="categoryname" maxlength="9"
						required value="" /><br /> 简单描述：<input type="text"
						name="represent" required value="" /><br /> <input type="submit"
						value="添加" /> <input type="reset" value="重置" /><br /> <a
						href="list-category.jsp"
						style="margin: 14px auto; display: block; width: 60px; color: #FF6700">返回</a>
				</form>
				<%
					} else {
					Category editCategory = (Category) session.getAttribute("editCategory");
				%>
				<div class="ddzxbt">商品类别编辑</div>
				<form action="../CategoryServlet?action=update" method="post">
					<input type="hidden" name="id" value="<%=editCategory.getId()%>" /><br />
					商品类别名称：<input type="text" name="categoryname" maxlength="9"
						value="<%=editCategory.getName()%>" /><br /> 简单描述：<input
						type="text" name="represent"
						value="<%=editCategory.getRepresent()%>" /><br /> <input
						type="submit" value="修改" /> <input type="reset" value="重置" /><br />
					<a href="list-category.jsp"
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