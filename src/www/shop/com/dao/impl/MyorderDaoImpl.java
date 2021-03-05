package www.shop.com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import www.shop.com.dao.IMyorderDao;
import www.shop.com.dao.po.Myorder;
import www.shop.com.dao.utils.JdbcUtil;

public class MyorderDaoImpl implements IMyorderDao {
	private JdbcUtil conn = new JdbcUtil();

	@Override
	public List<Myorder> listAll(int userId) {
		// TODO Auto-generated method stub
		List<Myorder> myorders = new ArrayList<Myorder>();
		try {
			String allSql = "SELECT id,goods_id,user_id,status FROM myorder WHERE user_id =" + userId;
			// ��һ�ű����������
			ResultSet rs = conn.executeQuery(allSql);
			while (rs.next()) {
				// һ������ӳ��һ����¼
				Myorder myorder = new Myorder();
				myorder.setId(rs.getInt("id"));
				myorder.setGoodsId(rs.getInt("goods_id"));
				myorder.setUserId(rs.getInt("user_id"));
				myorder.setStatus(rs.getInt("status"));
				myorders.add(myorder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ر���Դ
			conn.close();
		}
		return myorders;
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		int rows = -1;
		String delSql = "DELETE FROM myorder WHERE id=" + id;
		// ��һ�ű����������
		rows = conn.executeUpdate(delSql);
		conn.close();
		return rows;
	}

	@Override
	public int insert(Myorder myorder) {
		// TODO Auto-generated method stub
		int rows = -1;
		String allSql = "SELECT id,goods_id,user_id,status FROM myorder WHERE user_id = " + myorder.getUserId()
				+ " AND goods_id = " + myorder.getGoodsId();
		// ��һ�ű����������
		ResultSet rs = conn.executeQuery(allSql);
		try {
			if (!rs.next()) {
				String insertSql = "INSERT INTO myorder(goods_id,user_id) VALUES('" + myorder.getGoodsId() + "','"
						+ myorder.getUserId() + "')";
				rows = conn.executeUpdate(insertSql);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn.close();
		return rows;
	}

	@Override
	public int changeStatus(int id, int status) {
		// TODO Auto-generated method stub
		int rows = -1;
		String updSql = "UPDATE myorder SET status = '" + status + "' WHERE id= '" + id + "'";
		rows = conn.executeUpdate(updSql);
		conn.close();
		return rows;
	}

	@Override
	public List<Myorder> getById(int id) {
		// TODO Auto-generated method stub
		List<Myorder> myorders = new ArrayList<Myorder>();
		try {
			String allSql = "SELECT id,goods_id,user_id,status FROM myorder WHERE goods_id =" + id;
			// ��һ�ű����������
			ResultSet rs = conn.executeQuery(allSql);
			while (rs.next()) {
				// һ������ӳ��һ����¼
				Myorder myorder = new Myorder();
				myorder.setId(rs.getInt("id"));
				myorder.setGoodsId(rs.getInt("goods_id"));
				myorder.setUserId(rs.getInt("user_id"));
				myorder.setStatus(rs.getInt("status"));
				myorders.add(myorder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ر���Դ
			conn.close();
		}
		return myorders;
	}

}
