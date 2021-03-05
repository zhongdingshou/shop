package www.shop.com.dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	public Connection conn = null; // 数据库连接对象
	public Statement stmt = null; // statement对象，用于执行Sql语句
	public ResultSet rs = null; // 结果对象集
	// 驱动类的类名
	private static String dbClassName = "com.mysql.jdbc.Driver";
	private static String dbUrl = "jdbc:mysql://localhost:3306/shop?useUnicode=true&characterEncoding=utf-8";
	private static String dbUser = "root"; // 登录sql的用户名
	private static String dbPwd = ""; // 登录sql的密码

	/*
	 * 创建与数据库的连接
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(dbClassName); // 装在数据库驱动
			// 获取数据库连接对象
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (conn == null) {
			System.err.println("DbConnectionManger.getConnection():" + dbClassName + "\r\n" + dbUrl + "\r\n" + dbUser
					+ "/" + dbPwd);
		}
		return conn;
	}

	/*
	 * 更新数据功能
	 */
	public int executeUpdate(String sql) {
		int result = 0; // 更新数据的记录条数
		try {
			conn = getConnection(); // 获取数据库连接
			// 创建用于执行SQL语句的statement对象
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO: handle exception
			result = 0;
			e.printStackTrace(); // 输出异常信息
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 根据指定的sql语句查询数据
	 */
	public ResultSet executeQuery(String sql) {
		try {
			conn = getConnection(); // 获取数据库连接
			// 创建用于执行SQL语句的statement对象
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql); // 执行SQL语句
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rs;
	}

	/*
	 * 关闭数据库
	 */
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(System.err);
		}
	}
}
