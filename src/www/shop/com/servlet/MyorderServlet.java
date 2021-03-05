package www.shop.com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import www.shop.com.dao.po.Goods;
import www.shop.com.dao.po.Myorder;
import www.shop.com.dao.po.User;
import www.shop.com.service.IGoodsService;
import www.shop.com.service.IMyorderService;
import www.shop.com.service.impl.GoodsServiceImpl;
import www.shop.com.service.impl.MyorderServiceImpl;

/**
 * Servlet implementation class MyorderServlet
 */
@WebServlet("/MyorderServlet")
public class MyorderServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	IMyorderService myorderService = new MyorderServiceImpl();
	IGoodsService goodsService = new GoodsServiceImpl();

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String idStr = request.getParameter("id");
		String status = request.getParameter("status");
		int ret = myorderService.changeStatus(Integer.parseInt(idStr), Integer.parseInt(status));
		if (ret > 0) {
			if (Integer.parseInt(status) == 3) {
				response.sendRedirect("MyorderServlet?action=listAll");
			} else {
				response.sendRedirect("MyorderServlet?action=get");
			}
		} else {
			response.sendRedirect("message.jsp?message=error&where=order.jsp");
		}
	}

	public void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		User operator = (User) session.getAttribute("operator");
		if (operator == null || operator.getRule() != 2) {
			response.sendRedirect("index.jsp");
			return;
		}
		List<Goods> myGoods = goodsService.getAdminGoods(operator.getId());
		if (myGoods != null) {
			session.setAttribute("myPublishGoods", myGoods);
			List<Myorder> adminMyorders = new ArrayList<Myorder>();
			for (Goods goods : myGoods) {
				List<Myorder> goodsMyorders = myorderService.getById(goods.getId());
				adminMyorders.addAll(goodsMyorders);
			}
			session.setAttribute("adminMyorders", adminMyorders);
			if (adminMyorders.size() != 0) {
				response.sendRedirect("admin/list-order.jsp");
			} else {
				response.sendRedirect("message.jsp?message=Failed,nobody want your goods&where=admin/list-order.jsp");
			}
		} else {
			response.sendRedirect("message.jsp?message=Failed,your goods is null&where=admin/list-goods.jsp");
		}
	}

	public void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 解决从服务器端转到客户端的乱码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		User operator = (User) session.getAttribute("operator");
		if (operator == null) {
			response.sendRedirect("index.jsp");
			return;
		}
		List<Myorder> myorders = myorderService.listAll(operator.getId());
		if (myorders != null) {
			session.setAttribute("myorders", myorders);
			List<Goods> orderGoods = new ArrayList<Goods>();
			for (Myorder myorder : myorders) {
				Goods goodsDetail = goodsService.getById(myorder.getGoodsId());
				if (goodsDetail != null) {
					orderGoods.add(goodsDetail);
				}
			}
			if (orderGoods != null) {
				session.setAttribute("orderGoods", orderGoods);
			}
			response.sendRedirect("order.jsp");
		} else {
			response.sendRedirect("message.jsp?message=your order is null&where=personal.jsp");
		}
	}

	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 解决从服务器端转到客户端的乱码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String goodsIdStr = request.getParameter("id");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("operator");
		if (user == null) {
			response.sendRedirect("index.jsp");
			return;
		}
		Myorder order = new Myorder(Integer.parseInt(goodsIdStr), user.getId());
		int ret = myorderService.insert(order);
		if (ret > 0) {
			response.sendRedirect("MyorderServlet?action=get");
		} else {
			response.sendRedirect("message.jsp?message=Failed,already exists&where=order.jsp");
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 解决从服务器端转到客户端的乱码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String idStr = request.getParameter("id");
		String flag = request.getParameter("flag");
		HttpSession session = request.getSession();
		User operator = (User) session.getAttribute("operator");
		if (operator == null) {
			response.sendRedirect("index.jsp");
			return;
		}
		int ret = myorderService.deleteById(Integer.parseInt(idStr));
		if (ret > 0) {
			if (flag.equals("y")) {
				response.sendRedirect("MyorderServlet?action=listAll");
			} else {
				response.sendRedirect("MyorderServlet?action=get");
			}
		} else {
			response.sendRedirect("message.jsp?message=Failed&where=MyorderServlet?action=get");
		}
	}

}
