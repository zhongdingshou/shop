<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="www.shop.com.dao.po.Trolley"%>
<%@ page import="www.shop.com.dao.po.Goods"%>

<%@ include file="template/indexHeader.jsp"%>
<title>我的购物车-简单商城</title>
<style>
.goodstitle {
	width: 130px;
	margin-left: 20px;
}

.goodsdesc {
	width: 270px;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
	margin-left: 20px;
}
</style>
</head>
<%
	List<Goods> myTrolley = (List<Goods>) session.getAttribute("myTrolley");
List<Trolley> trolleysGoods = (List<Trolley>) session.getAttribute("trolleysGoods");
if (myTrolley == null) {
	response.sendRedirect("GoTrolleyServlet");
	return;
}
%>
<body>
	<!-- start header -->
	<!--end header -->
	<!-- start banner_x -->
	<div class="banner_x center">
		<a href="javascript:;" target="_blank">
			<div class="logo fl">
				<h4 style="vertical-align: middle; line-height: 100px;">LOGO</h4>
			</div>
		</a>

		<div class="wdgwc fl ml40">我的购物车</div>
		<div class="wxts fl ml20">温馨提示：产品是否购买成功，以最终下单为准哦，请尽快结算</div>
		<div class="dlzc fr">
			<ul>
				<li><a href="javascript:history.go(-1);">返回</a></li>

			</ul>

		</div>
		<div class="clear"></div>
	</div>
	<div class="xiantiao"></div>
	<div class="gwcxqbj" style="height: auto;">
		<div class="gwcxd center">
			<div class="top2 center">
				<div class="sub_top fl" style="margin-left: 70px;"></div>
				<div class="goodstitle fl">商品名称</div>
				<div class="goodsdesc fl">介绍</div>
				<div class="goodstitle fl">价格</div>
				<div class="goodstitle fl">数量</div>
				<div class="goodstitle fl">状态</div>
				<div class="goodstitle fr">操作</div>
				<div class="clear"></div>
			</div>
			<%
				for (Goods goods : myTrolley) {
			%>
			<div class="clear"></div>
			<div class="content2 center">
				<div class="goodstitle fl">
					<img style="width: 120px;" src="<%=goods.getImg()%>">
				</div>
				<div class="goodstitle fl"><%=goods.getName()%></div>
				<div class="goodsdesc fl"><%=goods.getRepresent()%></div>
				<div class="goodstitle fl">
					￥<%=goods.getMoney()%>元
				</div>
				<div class="goodstitle fl">
					<%
						for (Trolley t : trolleysGoods) {
						if (goods.getId() == t.getGoodsId()) {
					%>
					<div style="display: block"><%=t.getBuyNum()%>
						件 <input type="button" class="shownum" value="修改">
					</div>
					<div style="display: none">
						<form action="TrolleyServlet?action=update" method="post">
							<input type="hidden" name="id" value="<%=t.getId()%>"> <label>数量：<input
								type="number" name="num" style="width: 50px;"
								value="<%=t.getBuyNum()%>" maxlength="9"></label> <input
								type="submit" value="修改"><input type="reset"
								class="changenum" value="取消">
						</form>
					</div>
					<%
						}
					}
					%>
				</div>
				<div class="goodstitle fl"><%=goods.getStatus() == 1 ? "上架" : "下架"%></div>
				<div class="goodstitle fl">
					<a href="MyorderServlet?action=add&id=<%=goods.getId()%>"
						onclick="return confirm('确定下单吗？')">下单</a> | <a
						href="TrolleyServlet?action=delete&id=<%=goods.getId()%>">移除</a>
				</div>
				<div class="clear"></div>
			</div>
			<%
				}
			%>
			<div class="jiesuandan mt20 center">
				<div class="tishi fl ml20">
					<ul>
						<li><a href="index.jsp">继续购物</a></li>
						<div class="clear"></div>
					</ul>
				</div>
				<div class="jiesuan fr">
					<div class="jsanniu fr">
						<a href="order.jsp"><input class="jsan" type="button"
							name="jiesuan" value="我的订单" /></a>
					</div>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>

	<!-- footer -->
	<%@ include file="template/footer.jsp"%>
	<script type="text/javascript">
		$("div.goodstitle div input.shownum").click(function() {
			$(this).parent().hide();
			$(this).parent().next().show();
		});
		$("div.goodstitle div input.changenum").click(function() {
			$(this).parent().parent().hide();
			$(this).parent().parent().prev().show();
		});
	</script>
</body>
</html>
