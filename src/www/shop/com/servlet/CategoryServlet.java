package www.shop.com.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import www.shop.com.dao.po.Category;
import www.shop.com.dao.po.User;
import www.shop.com.service.ICategoryService;
import www.shop.com.service.impl.CategoryServiceImpl;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet implements Servlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ICategoryService categoryService = new CategoryServiceImpl();

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 解决从服务器端转到客户端的乱码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("categoryname");
		String represent = request.getParameter("represent");
		Category category = new Category(Integer.parseInt(id), name, represent);
		int ret = categoryService.update(category);
		if (ret > 0) {
			response.sendRedirect("message.jsp?message=Success&where=CategoryServlet?action=listAll");
		} else {
			response.sendRedirect("message.jsp?message=Failed&where=admin/list-category.jsp");
		}
	}

	public void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> categories = categoryService.listAll();
		HttpSession session = request.getSession();
		session.setAttribute("categories", categories);
		request.setCharacterEncoding("utf-8");
		String whereString = request.getParameter("where");
		if (whereString != null) {
			response.sendRedirect(whereString);
		} else {
			response.sendRedirect("index.jsp");
		}
	}

	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String name = request.getParameter("categoryname");
		String represent = request.getParameter("represent");
		Category category = new Category(name, represent);
		int ret = categoryService.insert(category);
		if (ret > 0) {
			response.sendRedirect("message.jsp?message=Success&where=CategoryServlet?action=listAll");
		} else {
			response.sendRedirect("message.jsp?message=Failed&where=admin/list-category.jsp");
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String idString = request.getParameter("id");
		int ret = categoryService.deleteById(Integer.parseInt(idString));
		if (ret > 0) {
			List<Category> categories = categoryService.listAll();
			HttpSession session = request.getSession();
			session.setAttribute("categories", categories);
			response.sendRedirect("message.jsp?message=Success&where=admin/list-category.jsp");
		} else {
			response.sendRedirect("message.jsp?message=Failed&where=admin/list-category.jsp");
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
		Category editCategory = categoryService.getById(Integer.parseInt(idString));
		if (editCategory != null) {
			session.setAttribute("editCategory", editCategory);
			response.sendRedirect("admin/add-category.jsp?how=edit");
		} else {
			response.sendRedirect("admin/list-category.jsp");
		}
	}

}
