package www.shop.com.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BaseServlet
 */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public BaseServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 乱码处理
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String action = request.getParameter("action");
		if (action == null || action.equals("")) {
			response.getWriter().write("您提交是的非法数据！");
			return;
		}
		try {
			// 反射：可以做未来不确定的事情。 根据输入的action动作通过反射机制得到本对象的对应方法对象
			Method method = this.getClass().getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
			// 使用方法对象method调用本对象的方法
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("您提交是的非法数据！！");
		}
	}

}
