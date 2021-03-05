<%@page import="www.shop.com.dao.po.Myorder"%>
<%@page import="www.shop.com.dao.po.Goods"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../template/adminHeader.jsp"%>

<title>商城 - 后台管理中心</title>
<style>
<!--
.show {
	display: block;
}

.hid {
	display: none;
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
			<div class="rtcont fr show" id="look">
				<div class="grzlbt ml40">我的资料</div>
				<div class="subgrzl ml40">
					<span>用户名</span><span><%=operator.getUsername()%></span>
				</div>
				<div class="subgrzl ml40">
					<span>密码</span><span><%=operator.getPassword()%></span>
				</div>
				<div class="subgrzl ml40">
					<span>手机</span><span><%=operator.getPhone()%></span>
				</div>
				<div class="subgrzl ml40">
					<span>地址</span><span><%=operator.getAddress() != "" ? operator.getAddress() : "未设置"%></span>
				</div>
				<div class="subgrzl ml40">
					<span><a href="javascript:;" onclick="showToHid(0)"><button>编辑</button></a></span>
				</div>
			</div>
			<div class="rtcont fr hid" id="edit">
				<div class="grzlbt ml40">我的资料</div>
				<form action="../UserServlet?action=update" method="post">
					<input type="hidden" name="userid" value="<%=operator.getId()%>">
					<input type="hidden" name="rule" value="<%=operator.getRule()%>">
					<div class="subgrzl ml40">
						<span>用户名</span><span><input type="text" name="username"
							value="<%=operator.getUsername()%>"></span>
					</div>
					<div class="subgrzl ml40">
						<span>密码</span><span><input type="text" name="password"
							value="<%=operator.getPassword()%>"></span>
					</div>
					<div class="subgrzl ml40">
						<span>手机</span><span><input type="text" name="phone"
							value="<%=operator.getPhone()%>" maxlength="11"></span>
					</div>
					<div class="subgrzl ml40">
						<span>地址</span><span><input type="text" name="address"
							value="<%=operator.getAddress() != "" ? operator.getAddress() : ""%>"></span>
					</div>
					<div class="subgrzl ml40">
						<span><a href="javascript:;"><input type="submit"
								value="确定" /></a></span><span><a href="javascript:;"><input
								type="button" value="取消" onclick="showToHid(1)"></a></span>
					</div>
				</form>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<!-- self_info -->

	<%@ include file="../template/footer.jsp"%>

	<script>
		function showToHid(num) {
			if (num == 0) {
				$("#look").addClass("hid");
				$("#look").removeClass("show");
				$("#edit").addClass("show");
				$("#edit").removeClass("hid");
			} else {
				$("#look").addClass("show");
				$("#look").removeClass("hid");
				$("#edit").addClass("hid");
				$("#edit").removeClass("show");
			}

		}
	</script>
</body>
</html>