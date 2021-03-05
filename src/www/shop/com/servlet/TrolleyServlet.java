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
import www.shop.com.dao.po.Trolley;
import www.shop.com.dao.po.User;
import www.shop.com.service.IGoodsService;
import www.shop.com.service.ITrolleyService;
import www.shop.com.service.impl.GoodsServiceImpl;
import www.shop.com.service.impl.TrolleyServiceImpl;

/**
 * Servlet implementation class TrolleyServlet
 */
@WebServlet("/TrolleyServlet")
public class TrolleyServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	ITrolleyService trolleyService = new TrolleyServiceImpl();
	IGoodsService goodsService = new GoodsServiceImpl();

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String IdStr = request.getParameter("id");
		String numStr = request.getParameter("num");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("operator");
		if (user == null) {
			response.sendRedirect("index.jsp");
			return;
		}
		Trolley trolley = new Trolley(Integer.parseInt(IdStr), Integer.parseInt(numStr));
		System.out.println(IdStr);
		System.out.println(numStr);
		int ret = trolleyService.update(trolley);
		if (ret > 0) {
			response.sendRedirect("message.jsp?message=Success&where=TrolleyServlet?action=listAll");
		} else {
			response.sendRedirect("message.jsp?message=Failed&where=trolley.jsp");
		}
	}

	public void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		List<Trolley> trolleysGoods = trolleyService.listAll(operator.getId());
		if (trolleysGoods != null) {
			session.setAttribute("trolleysGoods", trolleysGoods);
			List<Goods> myTrolley = new ArrayList<Goods>();
			for (Trolley trolley : trolleysGoods) {
				Goods goodsDetail = goodsService.getById(trolley.getGoodsId());
				if (goodsDetail != null) {
					myTrolley.add(goodsDetail);
				}
			}
			if (myTrolley != null) {
				session.setAttribute("myTrolley", myTrolley);
			}
			response.sendRedirect("trolley.jsp");
		} else {
			response.sendRedirect("message.jsp?message=your trolley is null&where=index.jsp");
		}
	}

	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String goodsIdStr = request.getParameter("id");
		String numStr = request.getParameter("num");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("operator");
		if (user == null) {
			response.sendRedirect("index.jsp");
			return;
		}
		Trolley trolley = new Trolley(Integer.parseInt(goodsIdStr), user.getId(), Integer.parseInt(numStr));
		int ret = trolleyService.insert(trolley);
		if (ret > 0) {
			response.sendRedirect("message.jsp?message=Success&where=details.jsp");
		} else {
			response.sendRedirect("message.jsp?message=Failed,already exists&where=details.jsp");
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 解决从服务器端转到客户端的乱码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String idStr = request.getParameter("id");
		HttpSession session = request.getSession();
		User operator = (User) session.getAttribute("operator");
		if (operator == null) {
			response.sendRedirect("index.jsp");
			return;
		}
		int ret = trolleyService.deleteById(Integer.parseInt(idStr), operator.getId());
		if (ret > 0) {
			response.sendRedirect("TrolleyServlet?action=listAll");
		} else {
			response.sendRedirect("message.jsp?message=Failed&where=trolley.jsp");
		}
	}

}
