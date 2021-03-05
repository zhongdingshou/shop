<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="template/header.jsp"%>

<title>商城 - 用户注册界面</title>
</head>
<body>
	<div class="container clearfix">
		<!-- 登录开始 -->
		<div class="content content-middle">
			<a style="float: right;" href="javascript:history.go(-1);">返回</a>
			<div class="login_name" style="margin: 0;">
				<p>用户注册</p>
			</div>
			<form action="UserServlet?action=register" method="post">
				<input name="username" type="text" required="required"
					placeholder="用户名" /> <input name="password" type="password"
					class="password_one" required="required" placeholder="密码" /> <input
					name="password_r" type="password" class="password_two"
					onChange="chackPassword()" required="required" placeholder="确认密码" />
				<input type="text" style="width: 60%;" name="checkCode"
					placeholder="验证码区分大小写" required="required" onclick="checkTime()" />
				<div
					style="display: inline-block; vertical-align: middle; padding: 0 0 16px 0;">
					<img src="CheckCode" name="img_checkCode" onClick="myReload()"
						id="img_checkCode" />
					<p style="color: blue; font-size: 12px;">看不清？点击图片更换</p>
				</div>
				<input value="提交" style="width: 100%;" type="submit">
			</form>
			<a
				style="display: inline-block; padding-top: 12px; color: blue; text-decoration: none;"
				href="login.jsp">已有账号？去登录</a>
		</div>
		<!-- 登录结束 -->
		<!-- 背景图 -->

		<%@ include file="template/background.jsp"%>

	</div>
	<script src="static/js/utils.js" type="text/javascript"></script>
</body>
</html>