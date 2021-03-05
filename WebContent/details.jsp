<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="www.shop.com.dao.po.User"%>
<%@page import="www.shop.com.dao.po.Category"%>
<%@page import="www.shop.com.dao.po.Goods"%>
<%@ include file="template/indexHeader.jsp"%>
<title>详情页-简单商城</title>
</head>
<body>
	<%@ include file="template/nav.jsp"%>

	<%
		Goods goodsDetail = (Goods) session.getAttribute("goodsDetail");
	%>
	<!-- xiangqing -->
	<div class="xiangqing">
		<div class="neirong w">
			<div class="xiaomi6 fl"><%=goodsDetail.getName()%></div>
			<nav class="fr" style="width: 100px;">
				<li><a href="javascript:;">概述如下</a></li>
				<div class="clear"></div>
			</nav>
			<div class="clear"></div>
		</div>
	</div>

	<div class="jieshao mt20 w">
		<div class="left fl">
			<img style="width: 560px;" src="<%=goodsDetail.getImg()%>">
		</div>
		<div class="right fr" style="height: 560px;">
			<div class="h3 ml20 mt20"><%=goodsDetail.getName()%></div>
			<div class="jianjie mr40 ml20 mt10" style="min-height: 200px;"><%=goodsDetail.getRepresent()%></div>
			<div class="jiage ml20 mt10">
				单价：<%=goodsDetail.getMoney()%>元
			</div>
			<form action="TrolleyServlet?action=add" method="post">
				<input type="hidden" name="id" value="<%=goodsDetail.getId()%>">
				<div class="xqxq mt20 ml20">
					<div class="top1 mt10">
						<div class="left1 fl"><%=goodsDetail.getName()%></div>
						<div class="right1 fr">
							<label>数量：<input type="number" id="input1" name="num"
								value="1" maxlength="9"></label>
						</div>
						<div class="clear"></div>
					</div>
					<div class="bot mt20 ft20 ftbc">
						总计：<span id="yuan">0</span> 元
					</div>
				</div>
				<div class="xiadan ml20 mt20">
					<%
						if (operator == null) {
					%>
					<a href="javascript:;"><input class="jrgwc"
						onclick="alert('请登陆')" type="button" name="jrgwc" value="立即选购" /></a>
					<a href="javascript:;"><input class="jrgwc"
						onclick="alert('请登陆')" type="button" name="jrgwc" value="加入购物车" /></a>
					<%
						} else {
					%>
					<a href="MyorderServlet?action=add&id=<%=goodsDetail.getId()%>"
						onclick="return confirm('确定下单吗？')"><input class="jrgwc"
						type="button" name="jrgwc" value="立即选购" /></a> <a href="javascript:;"><input
						class="jrgwc" type="submit" name="jrgwc" value="加入购物车" /></a>
					<%
						}
					%>
				</div>
			</form>
		</div>
		<div class="clear"></div>
	</div>
	<!-- footer -->
	<%@ include file="template/footer.jsp"%>
	<script type="text/javascript">
	window.onload = function(){
		$("#yuan").text(<%=goodsDetail.getMoney()%>);
	} 
	$("#input1").bind("input propertychange",function(event){
	       console.log($(this).val().trim());
	       var num = $(this).val().trim();
	       if(num==null||num==undefined||num==""||num==0){
	    	   $(this).val("1");
	    	   return;
	       }
	       if(!isNaN(num)){
	    	   num = Math.floor(num);
	    	   $("#yuan").text(num*<%=goodsDetail.getMoney()%>);
	       } else {
	    	   $(this).val("1");
	    	   $("#yuan").text(<%=goodsDetail.getMoney()%>);
			}
		});
	</script>
</body>
</html>