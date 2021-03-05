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

	// 返回上传来的文件名
	private String getFilename(Part part) {
		String fname = null;
		// 返回上传的文件部分的content-disposition请求头的值
		String header = part.getHeader("content-disposition");
		// 返回不带路径的文件名
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
		// 返回Web应用程序文档根目录
		String path = this.getServletContext().getRealPath("./");
		Part p = request.getPart("fileName");
		if (p.getSize() > 1024 * 1024) { // 上传的文件不能超过1MB大小
			p.delete();
			response.sendRedirect("message.jsp?message=Failed&where=admin/add-picture.jsp");
		} else {
			// 文件存储在文档根目录位置
			String imagePosition = "upload/" + username + "/" + System.currentTimeMillis();
			path = path + "/" + imagePosition;
			File f = new File(path);
			if (!f.exists()) { // 若目录不存在，则创建目录
				f.mkdirs();
			}
			String fname = getFilename(p); // 得到文件名
			p.write(path + "/" + fname); // 将上传的文件写入磁盘
			String imageForSqlString = imagePosition + "/" + fname;
			// 存入数据库
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
