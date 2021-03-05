package www.shop.com.servlet;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import www.shop.com.dao.po.Goods;
import www.shop.com.dao.po.User;
import www.shop.com.service.IGoodsService;
import www.shop.com.service.impl.GoodsServiceImpl;

/**
 * Servlet implementation class UploadFile
 */
@WebServlet("/UploadFile")
@MultipartConfig(location = "D:\\", fileSizeThreshold = 1024)
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IGoodsService goodsService = new GoodsServiceImpl();

	/**
	 * Default constructor.
	 */
	public UploadFile() {
		// TODO Auto-generated constructor stub
	}

	// �����ϴ������ļ���
	private String getFilename(Part part) {
		String fname = null;
		// �����ϴ����ļ����ֵ�content-disposition����ͷ��ֵ
		String header = part.getHeader("content-disposition");
		// ���ز���·�����ļ���
		fname = header.substring(header.lastIndexOf("\\") + 1, header.length() - 1);
		return fname;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User operator = (User) session.getAttribute("operator");
		if (operator == null || operator.getRule() != 2) {
			response.sendRedirect("index.jsp");
			return;
		}
		String idString = request.getParameter("id");
		String username = operator.getUsername();
		// ����WebӦ�ó����ĵ���Ŀ¼
		String path = this.getServletContext().getRealPath("./");
		Part p = request.getPart("fileName");
		if (p.getSize() > 1024 * 1024) { // �ϴ����ļ����ܳ���1MB��С
			p.delete();
			response.sendRedirect("message.jsp?message=Failed&where=admin/add-picture.jsp");
		} else {
			// �ļ��洢���ĵ���Ŀ¼λ��
			String imagePosition = "upload/" + username + "/" + System.currentTimeMillis();
			path = path + "/" + imagePosition;
			File f = new File(path);
			if (!f.exists()) { // ��Ŀ¼�����ڣ��򴴽�Ŀ¼
				f.mkdirs();
			}
			String fname = getFilename(p); // �õ��ļ���
			p.write(path + "/" + fname); // ���ϴ����ļ�д�����
			String imageForSqlString = imagePosition + "/" + fname;
			// �������ݿ�
			Goods goods = new Goods(Integer.parseInt(idString), imageForSqlString);
			int ret = goodsService.updateImg(goods);
			if (ret > 0) {
				response.sendRedirect("message.jsp?message=Success&where=GoodsServlet?action=listAll");
			} else {
				response.sendRedirect("message.jsp?message=Failed&where=admin/add-picture.jsp");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
