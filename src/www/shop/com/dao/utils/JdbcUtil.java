package www.shop.com.dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	public Connection conn = null; // ���ݿ����Ӷ���
	public Statement stmt = null; // statement��������ִ��Sql���
	public ResultSet rs = null; // �������
	// �����������
	private static String dbClassName = "com.mysql.jdbc.Driver";
	private static String dbUrl = "jdbc:mysql://localhost:3306/shop?useUnicode=true&characterEncoding=utf-8";
	private static String dbUser = "root"; // ��¼sql���û���
	private static String dbPwd = ""; // ��¼sql������

	/*
	 * ���������ݿ������
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(dbClassName); // װ�����ݿ�����
			// ��ȡ���ݿ����Ӷ���
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
	 * �������ݹ���
	 */
	public int executeUpdate(String sql) {
		int result = 0; // �������ݵļ�¼����
		try {
			conn = getConnection(); // ��ȡ���ݿ�����
			// ��������ִ��SQL����statement����
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO: handle exception
			result = 0;
			e.printStackTrace(); // ����쳣��Ϣ
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
	 * ����ָ����sql����ѯ����
	 */
	public ResultSet executeQuery(String sql) {
		try {
			conn = getConnection(); // ��ȡ���ݿ�����
			// ��������ִ��SQL����statement����
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql); // ִ��SQL���
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rs;
	}

	/*
	 * �ر����ݿ�
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
