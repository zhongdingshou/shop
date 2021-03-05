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
		// ���봦��
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String action = request.getParameter("action");
		if (action == null || action.equals("")) {
			response.getWriter().write("���ύ�ǵķǷ����ݣ�");
			return;
		}
		try {
			// ���䣺������δ����ȷ�������顣 ���������action����ͨ��������Ƶõ�������Ķ�Ӧ��������
			Method method = this.getClass().getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
			// ʹ�÷�������method���ñ�����ķ���
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("���ύ�ǵķǷ����ݣ���");
		}
	}

}
