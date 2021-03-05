<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="www.shop.com.dao.po.User"%>
<%@page import="www.shop.com.dao.po.Category"%>
<%
	User operator = (User) session.getAttribute("operator");
if (operator == null || operator.getRule() == 1) {
	response.sendRedirect("../index.jsp");
	return;
}
%>
<header>
	<div class="top center">
		<div class="left fl">
			<ul>
				<li><a href="index.jsp"
					style="color: #ffffff; font-size: 16px;">简单小商城</a></li>
				<li>|</li>
				<div class="clear"></div>
			</ul>
		</div>
		<div class="right fr">
			<div class="gouwuche fr">
				<a href="../index.jsp" style="display: block;">返回首页</a>
			</div>
			<div class="fr">
				<ul>
					<%
						if (operator == null) {
					%>
					<li><a href="../login.jsp">登录</a></li>
					<li>|</li>
					<li><a href="../register.jsp">注册</a></li>
					<%
						} else {
					%>
					<li style="color: #ffffff; font-size: 14px; margin-right: 10px;">欢迎您，<%=operator.getUsername()%></li>
					<li></li>
					<li>|</li>
					<li></li>
					<li><a href="../personal.jsp">个人中心</a></li>
					<li>|</li>
					<li></li>
					<li><a href="../UserServlet?action=logout">退出</a></li>
					<%
						}
					%>
				</ul>
			</div>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
</header>
<!--end header -->

<!-- start banner_x -->
<div class="banner_x center">
	<a href="javascript:;" target="_blank">
		<div class="logo fl">
			<h4 style="vertical-align: middle; line-height: 100px;">LOGO</h4>
		</div>
	</a> <a href="javascript:;"><div class="ad_top fl">
			<img alt="logo" src="../static/images/yyymix.gif"
				style="height: 80px; clear: both; display: block; margin: 10px auto;">
		</div></a>
	<div class="nav fl">
		<ul style="margin-left: 40px;">
			<%
				List<Category> categories = (List<Category>) session.getAttribute("categories");
			if (categories == null) {
				response.sendRedirect("../CategoryServlet?action=listAll");
				return;
			}
			int i = 0;
			for (Category category : categories) {
				if (i < 9) {
			%>
			<li><a
				href="../GoodsServlet?action=getOne&id=<%=category.getId()%>"><%=category.getName()%></a></li>
			<%
				}
			i++;
			}
			%>
		</ul>
	</div>
	<div class="search fr">
		<form action="../GoodsServlet?action=search" method="post">
			<div class="text fl">
				<input type="text" class="shuru" name="word" required="required"
					placeholder="请输入关键词...">
			</div>
			<div class="submit fl">
				<input type="submit" class="sousuo" value="搜索" />
			</div>
			<div class="clear"></div>
		</form>
		<div class="clear"></div>
	</div>
</div>