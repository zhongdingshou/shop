<%@page import="www.shop.com.dao.po.Myorder"%>
<%@page import="www.shop.com.dao.po.Goods"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../template/adminHeader.jsp"%>

<title>商城 - 上传商品图片</title>
<style>
<!--
.rtcont form table {
	width: auto;
	text-align: center;
	margin: 40px auto;
}

.rtcont input {
	margin-top: 10px;
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
			<div class="rtcont fr" style="height: auto;">
				<form action="../UploadFile" enctype="multipart/form-data"
					method="post">
					<table>
						<tr>
							<input type="hidden" name="id"
								value="<%=request.getParameter("id")%>" />
							<br />
							<td colspan="2" align="center">图片上传</td>
						</tr>
						<tr>
							<td>文件名：</td>
							<td><input type="file" name="fileName" accept="image/*"
								required /></td>
						</tr>
						<tr>
							<td align="right"><input type="submit" value="提交" /></td>
							<td align="left"><input type="reset" value="重置" /></td>
							<td align="left"><a href="list-goods.jsp">返回</a></td>
						</tr>
					</table>
				</form>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<!-- self_info -->

	<%@ include file="../template/footer.jsp"%>
</body>
</html>