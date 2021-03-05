package www.shop.com.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import www.shop.com.dao.po.User;
import www.shop.com.service.IUserService;
import www.shop.com.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IUserService userService = new UserServiceImpl();

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 解决从服务器端转到客户端的乱码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String id = request.getParameter("userid");
		String rule = request.getParameter("rule");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		HttpSession session = request.getSession();
		User user = new User(Integer.parseInt(id), username, password, Integer.parseInt(rule), address, phone);
		int ret = userService.update(user);
		if (ret > 0) {
			User op = (User) session.getAttribute("operator");
			if (Integer.parseInt(id) == op.getId()) {
				session.setAttribute("operator", user);
				response.sendRedirect("message.jsp?message=Success&where=personal.jsp");
			} else {
				response.sendRedirect("message.jsp?message=Success&where=UserServlet?action=listAll");
			}
		} else {
			response.sendRedirect("message.jsp?message=Failed&where=personal.jsp");
		}
	}

	public void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 解决从服务器端转到客户端的乱码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		User operator = (User) session.getAttribute("operator");
		if (operator == null || operator.getRule() != 2) {
			response.sendRedirect("index.jsp");
			return;
		}
		List<User> allUsers = userService.listAll();
		if (!allUsers.isEmpty()) {
			session.setAttribute("allUsers", allUsers);
		}
		response.sendRedirect("admin/list-user.jsp");
	}

	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 解决从服务器端转到客户端的乱码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		User operator = (User) session.getAttribute("operator");
		if (operator == null || operator.getRule() != 2) {
			response.sendRedirect("index.jsp");
			return;
		}
		String rule = request.getParameter("rule");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		User user = new User(username, password, Integer.parseInt(rule), address, phone);
		int ret = userService.add(user);
		if (ret > 0) {
			response.sendRedirect("message.jsp?message=Success&where=UserServlet?action=listAll");
		} else {
			response.sendRedirect("message.jsp?message=Failed&where=admin/list-user.jsp");
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String idString = request.getParameter("id");
		HttpSession session = request.getSession();
		User operator = (User) session.getAttribute("operator");
		if (operator == null || operator.getRule() != 2) {
			response.sendRedirect("index.jsp");
			return;
		}
		int ret = userService.deleteById(Integer.parseInt(idString));
		if (ret > 0) {
			response.sendRedirect("UserServlet?action=listAll");
		} else {
			response.sendRedirect("message.jsp?message=Failed&where=list-user.jsp");
		}
	}

	public void getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String idString = request.getParameter("id");
		HttpSession session = request.getSession();
		User operator = (User) session.getAttribute("operator");
		if (operator == null || operator.getRule() != 2) {
			response.sendRedirect("index.jsp");
			return;
		}
		User editUser = userService.getById(Integer.parseInt(idString));
		if (editUser != null) {
			session.setAttribute("editUser", editUser);
			response.sendRedirect("admin/add-user.jsp?how=edit");
		} else {
			response.sendRedirect("admin/list-user.jsp");
		}
	}

	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String checkCode = request.getParameter("checkCode");
		HttpSession session = request.getSession();
		String randCheckCode = (String) session.getAttribute("randCheckCode");

		if (checkCode.equals(randCheckCode)) {
			User user = userService.login(username, password);
			if (user != null) {
				session.setAttribute("operator", user);
				response.sendRedirect("message.jsp?message=Success&where=index.jsp");
			} else {
				response.sendRedirect(
						"message.jsp?message=Failed login, account number or password error.&where=login.jsp");
			}
		} else {
			response.sendRedirect("message.jsp?message=The verification code is wrong.&where=login.jsp");
		}
	}

	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("operator");
		session.removeAttribute("message");
		session.removeAttribute("myorders");
		session.removeAttribute("orderGoods");
		session.removeAttribute("adminMyorders");
		session.removeAttribute("myPublishGoods");
		session.removeAttribute("editUser");
		session.removeAttribute("allUsers");
		response.sendRedirect("index.jsp");
	}

	public void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String checkCode = request.getParameter("checkCode");
		HttpSession session = request.getSession();
		String randCheckCode = (String) session.getAttribute("randCheckCode");
		if (checkCode.equals(randCheckCode)) {
			User user = new User(username, password);
			int result = userService.insert(user);
			if (result > 0) {
				user = userService.login(username, password);
				session.setAttribute("operator", user);
				response.sendRedirect("message.jsp?message=Success&where=index.jsp");
			} else {
				response.sendRedirect(
						"message.jsp?message=Registration failed and the user already exists.&where=register.jsp");
			}
		} else {
			response.sendRedirect("message.jsp?message=The verification code is wrong.&where=register.jsp");
		}
	}
}
