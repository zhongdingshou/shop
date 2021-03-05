<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="www.shop.com.dao.po.Goods"%>
<%@page import="www.shop.com.dao.po.Category"%>
<%@ include file="template/indexHeader.jsp"%>
<title>列表页</title>
</head>
<body>
	<%@ include file="template/nav.jsp"%>

	<%
		List<Goods> goodsCategory = (List<Goods>) session.getAttribute("goodsCategory");
	%>
	<div class="danpin center">
		<%
			for (Category c : categories) {
			if (goodsCategory.get(0).getCategoryId() == c.getId()) {
		%>
		<div class="biaoti center"><%=c.getName()%></div>
		<%
			}
		}
		%>
		<%
			int num = 1;
		for (Goods oneGoods : goodsCategory) {
			if (num == 1 || num % 6 == 0) {
				out.print("<div class=\"main center\" style='margin-bottom:20px;'>");
			}
		%>
		<div class="mingxing fl mb20"
			style="border: 2px solid #fff; width: 230px; cursor: pointer;"
			onmouseout="this.style.border='2px solid #fff'"
			onmousemove="this.style.border='2px solid red'">
			<div class="sub_mingxing">
				<a href="GoodsServlet?action=getById&id=<%=oneGoods.getId()%>"
					target="_blank"><img src="<%=oneGoods.getImg()%>"
					alt="<%=oneGoods.getName()%>"></a>
			</div>
			<div class="pinpai">
				<a href="GoodsServlet?action=getById&id=<%=oneGoods.getId()%>"
					target="_blank"><%=oneGoods.getName()%></a>
			</div>
			<div class="youhui"><%=oneGoods.getRepresent()%></div>
			<div class="jiage">
				￥
				<%=oneGoods.getMoney()%>元
			</div>
		</div>
		<%
			if (num % 5 == 0) {
			out.print("<div class=\"clear\"></div></div>");
		}
		num++;
		}
		%>
		<div class="clear"></div>
	</div>
	</div>
	<%@ include file="template/footer.jsp"%>