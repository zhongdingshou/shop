package www.shop.com.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import www.shop.com.dao.po.Goods;
import www.shop.com.dao.po.User;
import www.shop.com.service.IGoodsService;
import www.shop.com.service.impl.GoodsServiceImpl;

/**
 * Servlet implementation class GoodsServlet
 */
@WebServlet("/GoodsServlet")
public class GoodsServlet extends BaseServlet implements Servlet {

	private static final long serialVersionUID = 1L;
	IGoodsService goodsService = new GoodsServiceImpl();

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 解决从服务器端转到客户端的乱码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("goodsname");
		String represent = request.getParameter("represent");
		String money = request.getParameter("money");
		String category = request.getParameter("category");
		String status = request.getParameter("status");
		Goods goods = new Goods(Integer.parseInt(id), name, represent, Double.parseDouble(money),
				Integer.parseInt(category), Integer.parseInt(status));
		int ret = goodsService.update(goods);
		if (ret > 0) {
			response.sendRedirect("message.jsp?message=Success&where=GoodsServlet?action=listAll");
		} else {
			response.sendRedirect("message.jsp?message=Failed&where=admin/list-goods.jsp");
		}
	}

	public void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User operator = (User) session.getAttribute("operator");
		if (operator == null || operator.getRule() != 2) {
			response.sendRedirect("index.jsp");
			return;
		}
		List<Goods> myGoods = goodsService.getAdminGoods(operator.getId());
		if (myGoods != null) {
			session.setAttribute("myPublishGoods", myGoods);
		}
		response.sendRedirect("admin/list-goods.jsp");
	}

	public void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Goods> newGoods = goodsService.getSomeGoods(1);
		List<Goods> hotGoods = goodsService.getSomeGoods(2);
		List<Goods> phone = goodsService.getSomeGoods(3);
		HttpSession session = request.getSession();
		session.setAttribute("newGoods", newGoods);
		session.setAttribute("hotGoods", hotGoods);
		session.setAttribute("phone", phone);
		response.sendRedirect("index.jsp");
	}

	public void getOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String idStr = request.getParameter("id");
		List<Goods> goodsCategory = goodsService.listAll(Integer.parseInt(idStr));
		if (!goodsCategory.isEmpty()) {
			HttpSession session = request.getSession();
			session.setAttribute("goodsCategory", goodsCategory);
			response.sendRedirect("list.jsp");
		} else {
			response.sendRedirect("index.jsp");
		}
	}

	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 解决从服务器端转到客户端的乱码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String name = request.getParameter("goodsname");
		String represent = request.getParameter("represent");
		String money = request.getParameter("money");
		String category = request.getParameter("category");
		String status = request.getParameter("status");
		HttpSession session = request.getSession();
		User operator = (User) session.getAttribute("operator");
		if (operator == null || operator.getRule() != 2) {
			response.sendRedirect("index.jsp");
			return;
		}
		Goods goods = new Goods(name, represent, Double.parseDouble(money), operator.getId(),
				Integer.parseInt(category), Integer.parseInt(status));
		int ret = goodsService.insert(goods);
		if (ret > 0) {
			response.sendRedirect("message.jsp?message=Success&where=GoodsServlet?action=listAll");
		} else {
			response.sendRedirect("message.jsp?message=Failed&where=admin/list-goods.jsp");
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String idString = request.getParameter("id");
		int ret = goodsService.deleteById(Integer.parseInt(idString));
		if (ret > 0) {
			response.sendRedirect("message.jsp?message=Success&where=GoodsServlet?action=listAll");
		} else {
			response.sendRedirect("message.jsp?message=Failed&where=admin/list-goods.jsp");
		}
	}

	public void getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 解决从服务器端转到客户端的乱码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String idStr = request.getParameter("id");
		String admin = request.getParameter("admin");
		if (idStr != null) {
			Goods goodsDetail = goodsService.getById(Integer.parseInt(idStr));
			if (goodsDetail != null) {
				HttpSession session = request.getSession();
				session.setAttribute("goodsDetail", goodsDetail);
				if (admin != null) {
					response.sendRedirect("admin/add-goods.jsp?how=edit");
				} else {
					response.sendRedirect("details.jsp");
				}
			} else {
				response.sendRedirect("index.jsp");
			}
		} else {
			response.sendRedirect("index.jsp");
		}
	}

	public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 解决从服务器端转到客户端的乱码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String word = request.getParameter("word");
		List<Goods> searGoods = goodsService.searchGoods(word);
		if (!searGoods.isEmpty()) {
			HttpSession session = request.getSession();
			session.setAttribute("goodsSearch", searGoods);
			response.sendRedirect("search.jsp");
		} else {
			response.sendRedirect(
					"message.jsp?message=No relevant items have been found, please enter other keywords.&where=index.jsp");
		}
	}

}
