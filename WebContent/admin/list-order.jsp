<%@page import="www.shop.com.dao.po.Myorder"%>
<%@page import="www.shop.com.dao.po.Goods"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../template/adminHeader.jsp"%>

<title>商城 - 订单管理</title>
</head>
<%
	List<Myorder> adminMyorders = (List<Myorder>) session.getAttribute("adminMyorders");
List<Goods> myPublishGoods = (List<Goods>) session.getAttribute("myPublishGoods");
if (adminMyorders == null) {
	response.sendRedirect("../MyorderServlet?action=listAll");
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

			<div class="rtcont fr" style="height: auto;">
				<div class="ddzxbt">交易订单</div>
				<%
					for (Myorder oneMyorder : adminMyorders) {
					for (Goods g : myPublishGoods) {
						if (oneMyorder.getGoodsId() == g.getId()) {
				%>
				<div class="ddxq">
					<div class="ddspt fl">
						<img style="width: 80px;" src="../<%=g.getImg()%>"
							alt="<%=g.getName()%>">
					</div>
					<div class="ddbh fl"
						style="font-size: 13px; width: 160px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;"><%=g.getRepresent()%></div>
					<div class="ztxx fr">
						<ul style="font-size: 13px;">
							<li
								style="font-size: 13px; width: 130px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;"><%=g.getName()%></li>
							<li>用户id：<%=oneMyorder.getUserId()%></li>
							<li style="color: red;">￥<%=g.getMoney()%> 元
							</li>
							<%
								if (oneMyorder.getStatus() == 1) {
							%>
							<li>未支付</li>
							<%
								} else if (oneMyorder.getStatus() == 2) {
							%>
							<li>已支付—><a style="color: blue; font-size: 12px;"
								href="../MyorderServlet?action=update&id=<%=oneMyorder.getId()%>&status=3"
								onclick="return confirm('确定发货吗？')">发货？</a></li>
							<%
								} else if (oneMyorder.getStatus() == 3) {
							%>
							<li>发货中</li>
							<%
								} else {
							%>
							<li>已接收</li>
							<%
								}
							%>
							<li><a
								href="../GoodsServlet?action=getById&id=<%=oneMyorder.getGoodsId()%>">商品详情</a>
								<%
									if (oneMyorder.getStatus() != 2) {
								%> | <a style="color: blue; font-size: 12px;"
								href="../MyorderServlet?action=delete&flag=y&id=<%=oneMyorder.getId()%>"
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

	<%@ include file="../template/footer.jsp"%>
</body>
</html>