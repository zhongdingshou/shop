<%@page import="java.util.List"%>
<%@page import="www.shop.com.dao.po.User"%>
<%@page import="www.shop.com.dao.po.Category"%>
<%@page import="www.shop.com.dao.po.Goods"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="template/indexHeader.jsp"%>
<title>简单小商城</title>
<style type="text/css">
/* css 重置 */
* {
	margin: 0;
	padding: 0;
	list-style: none;
}

body {
	background: #fff;
	font: normal 12px/22px 宋体;
}

img {
	border: 0;
}

a {
	text-decoration: none;
	color: #333;
}

/* 本例子css */
.slideBox {
	width: 1226px;
	height: 460px;
	overflow: hidden;
	position: relative;
	border: 1px solid #ddd;
}

.slideBox .hd {
	height: 15px;
	overflow: hidden;
	position: absolute;
	right: 5px;
	bottom: 5px;
	z-index: 1;
}

.slideBox .hd ul {
	overflow: hidden;
	zoom: 1;
	float: left;
}

.slideBox .hd ul li {
	float: left;
	margin-right: 2px;
	width: 15px;
	height: 15px;
	line-height: 14px;
	text-align: center;
	background: #fff;
	cursor: pointer;
}

.slideBox .hd ul li.on {
	background: #FF6700;
	color: #fff;
}

.slideBox .bd {
	position: relative;
	height: 100%;
	z-index: 0;
}

.slideBox .bd li {
	zoom: 1;
	vertical-align: middle;
}

.slideBox .bd img {
	width: 1226px;
	height: 460px;
	display: block;
}

/* 下面是前/后按钮代码，如果不需要删除即可 */
.slideBox .prev, .slideBox .next {
	position: absolute;
	left: 3%;
	top: 50%;
	margin-top: -25px;
	display: block;
	width: 32px;
	height: 40px;
	background: url(static/images/slider-arrow.png) -110px 5px no-repeat;
	filter: alpha(opacity = 50);
	opacity: 0.5;
}

.slideBox .next {
	left: auto;
	right: 3%;
	background-position: 8px 5px;
}

.slideBox .prev:hover, .slideBox .next:hover {
	filter: alpha(opacity = 100);
	opacity: 1;
}

.slideBox .prevStop {
	display: none;
}

.slideBox .nextStop {
	display: none;
}
</style>
</head>
<body>
	<%@ include file="template/nav.jsp"%>

	<!-- start banner_y -->
	<div class="banner_y center">
		<div id="slideBox" class="slideBox">
			<div class="hd">
				<ul>
					<li>1</li>
					<li>2</li>
					<li>3</li>
					<li>4</li>
				</ul>
			</div>
			<div class="bd">
				<ul>
					<li><a href="javascript:;" target="_blank"><img src="static/images/bg01.jpg"/></a></li>
					<li><a href="javascript:;" target="_blank"><img src="static/images/bg02.jpg"/></a></li>
					<li><a href="javascript:;" target="_blank"><img src="static/images/bg03.jpg"/></a></li>
					<li><a href="javascript:;" target="_blank"><img src="static/images/bg04.jpg"/></a></li>
				</ul>
			</div>

			<!-- 下面是前/后按钮代码，如果不需要删除即可 -->
			<a class="prev" href="javascript:void(0)"></a> <a class="next" href="javascript:void(0)"></a>

		</div>
	</div>

	<!-- start danpin -->
	<div class="danpin center">
		<%
			List<Goods> newGoods = (List<Goods>) session.getAttribute("newGoods");
		List<Goods> hotGoods = (List<Goods>) session.getAttribute("hotGoods");
		List<Goods> phone = (List<Goods>) session.getAttribute("phone");
		if (newGoods == null) {
			response.sendRedirect("GoodsServlet?action=get");
			return;
		}
		%>
		<div class="biaoti center">最新商品</div>

		<div class="main center">
			<%
				for (Goods nG : newGoods) {
			%>
			<div class="mingxing fl">
				<div class="sub_mingxing">
					<a href="GoodsServlet?action=getById&id=<%=nG.getId()%>"><img
						src="<%=nG.getImg()%>" alt=""></a>
				</div>
				<div class="pinpai">
					<a href="javascript:;"><%=nG.getName()%></a>
				</div>
				<div class="youhui"><%=nG.getRepresent()%></div>
				<div class="jiage">
					￥
					<%=nG.getMoney()%>
					元
				</div>
			</div>
			<%
				}
			%>
			<div class="clear"></div>
		</div>
		<div class="biaoti center">推荐</div>

		<div class="main center">
			<%
				for (Goods hG : hotGoods) {
			%>
			<div class="mingxing fl">
				<div class="sub_mingxing">
					<a href="GoodsServlet?action=getById&id=<%=hG.getId()%>"><img
						src="<%=hG.getImg()%>" alt=""></a>
				</div>
				<div class="pinpai">
					<a href="javascript:;"><%=hG.getName()%></a>
				</div>
				<div class="youhui"><%=hG.getRepresent()%></div>
				<div class="jiage">
					￥
					<%=hG.getMoney()%>
					元
				</div>
			</div>
			<%
				}
			%>
			<div class="clear"></div>
		</div>
		<div class="biaoti center">手机</div>

		<div class="main center">
			<%
				for (Goods pG : phone) {
			%>
			<div class="mingxing fl">
				<div class="sub_mingxing">
					<a href="GoodsServlet?action=getById&id=<%=pG.getId()%>"><img
						src="<%=pG.getImg()%>" alt=""></a>
				</div>
				<div class="pinpai">
					<a href="javascript:;"><%=pG.getName()%></a>
				</div>
				<div class="youhui"><%=pG.getRepresent()%></div>
				<div class="jiage">
					￥
					<%=pG.getMoney()%>
					元
				</div>
			</div>
			<%
				}
			%>
			<div class="clear"></div>
		</div>
	</div>
	<%@ include file="template/footer.jsp"%>
	<script type="text/javascript">
		jQuery(".slideBox").slide({
			mainCell : ".bd ul",
			effect : "leftLoop",
			autoPlay : true,
			delayTime : 700
		});
	</script>
</body>
</html>