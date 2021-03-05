<%@page import="www.shop.com.dao.po.Myorder"%>
<%@page import="www.shop.com.dao.po.Goods"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="template/indexHeader.jsp"%>

<title>商城 - 个人中心</title>
</head>
<%
	List<Myorder> myorders = (List<Myorder>) session.getAttribute("myorders");
List<Goods> orderGoods = (List<Goods>) session.getAttribute("orderGoods");
if (myorders == null) {
	response.sendRedirect("MyorderServlet?action=get");
	return;
}
%>
<body>
	<!-- start header -->
	<%@ include file="template/nav.jsp"%>
	<!-- end banner_x -->
	<!-- self_info -->
	<div class="grzxbj">
		<div class="selfinfo center">

			<%@ include file="template/left-nav.jsp"%>

			<div class="rtcont fr" style="height: auto;">
				<div class="ddzxbt">交易订单</div>
				<%
					for (Myorder oneMyorder : myorders) {
					for (Goods g : orderGoods) {
						if (oneMyorder.getGoodsId() == g.getId()) {
				%>
				<div class="ddxq">
					<div class="ddspt fl">
						<img style="width: 80px;" src="<%=g.getImg()%>"
							alt="<%=g.getName()%>">
					</div>
					<div class="ddbh fl"
						style="width: 270px; font-size: 13px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;"><%=g.getRepresent()%></div>
					<div class="ztxx fr">
						<ul style="font-size: 13px;">
							<li
								style="width: 130px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;"><%=g.getName()%></li>
							<li style="color: red;">￥<%=g.getMoney()%> 元
							</li>
							<%
								if (oneMyorder.getStatus() == 1) {
							%>
							<li>未支付—><a style="color: blue; font-size: 12px;"
								href="MyorderServlet?action=update&id=<%=oneMyorder.getId()%>&status=2"
								onclick="return confirm('确定支付吗？')">去支付？</a></li>
							<%
								} else if (oneMyorder.getStatus() == 2) {
							%>
							<li>已支付，待发货</li>
							<%
								} else if (oneMyorder.getStatus() == 3) {
							%>
							<li>发货中—><a style="color: blue; font-size: 12px;"
								href="MyorderServlet?action=update&id=<%=oneMyorder.getId()%>&status=4"
								onclick="return confirm('确定收到吗？')">已接收？</a></li>
							<%
								} else {
							%>
							<li>已接收—><a style="color: blue; font-size: 12px;"
								href="MyorderServlet?action=delete&id=<%=oneMyorder.getId()%>"
								onclick="return confirm('确定删除吗？')">要删除？</a></li>
							<%
								}
							%>
							<li><a
								href="GoodsServlet?action=getById&id=<%=oneMyorder.getGoodsId()%>">商品详情</a>
								<%
									if (oneMyorder.getStatus() != 2) {
								%> | <a style="color: blue; font-size: 12px;"
								href="MyorderServlet?action=delete&flag=n&id=<%=oneMyorder.getId()%>"
								onclick="return confirm('确定移除吗？')">移除X</a> <%
 	}
 %></li>
							<div class="clear"></div>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<%
					}
				}
				}
				%>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<!-- self_info -->

	<%@ include file="template/footer.jsp"%>
</body>
</html>