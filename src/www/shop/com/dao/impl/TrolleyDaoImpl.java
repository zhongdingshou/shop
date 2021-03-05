package www.shop.com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import www.shop.com.dao.ITrolleyDao;
import www.shop.com.dao.po.Trolley;
import www.shop.com.dao.utils.JdbcUtil;

public class TrolleyDaoImpl implements ITrolleyDao {
	private JdbcUtil conn = new JdbcUtil();

	@Override
	public List<Trolley> listAll(int userId) {
		// TODO Auto-generated method stub
		List<Trolley> listTrolleys = new ArrayList<Trolley>();
		try {
			String allSql = "SELECT id,goods_id,user_id,buy_num FROM trolley WHERE user_id = " + userId;
			// 是一张表的所有数据
			ResultSet rs = conn.executeQuery(allSql);
			while (rs.next()) {
				// 一个对象映射一条记录
				Trolley trolley = new Trolley();
				trolley.setId(rs.getInt(("id")));
				trolley.setGoodsId(rs.getInt("goods_id"));
				trolley.setUserId(rs.getInt("user_id"));
				trolley.setBuyNum(rs.getInt("buy_num"));
				listTrolleys.add(trolley);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			conn.close();
		}
		return listTrolleys;
	}

	@Override
	public int deleteById(int id, int userid) {
		// TODO Auto-generated method stub
		int rows = -1;
		String delSql = "DELETE FROM trolley WHERE user_id=" + userid + " AND goods_id=" + id;
		// 是一张表的所有数据
		rows = conn.executeUpdate(delSql);
		conn.close();
		return rows;
	}

	@Override
	public int insert(Trolley trolley) {
		// TODO Auto-generated method stub
		int rows = -1;
		List<Trolley> listTrolleys = new ArrayList<Trolley>();
		String allSql = "SELECT id,goods_id,user_id,buy_num FROM trolley WHERE user_id = " + trolley.getUserId()
				+ " AND goods_id = " + trolley.getGoodsId();
		// 是一张表的所有数据
		ResultSet rs = conn.executeQuery(allSql);
		try {
			while (rs.next()) {
				// 一个对象映射一条记录
				Trolley geTrolley = new Trolley();
				geTrolley.setId(rs.getInt(("id")));
				geTrolley.setGoodsId(rs.getInt("goods_id"));
				geTrolley.setUserId(rs.getInt("user_id"));
				geTrolley.setBuyNum(rs.getInt("buy_num"));
				listTrolleys.add(geTrolley);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (listTrolleys.size() == 0) {
			String insertSql = "INSERT INTO trolley(goods_id,user_id,buy_num) VALUES('" + trolley.getGoodsId() + "','"
					+ trolley.getUserId() + "','" + trolley.getBuyNum() + "')";
			rows = conn.executeUpdate(insertSql);
		}
		conn.close();
		return rows;
	}

	@Override
	public int update(Trolley trolley) {
		// TODO Auto-generated method stub
		int rows = -1;
		String updSql = "UPDATE trolley SET buy_num = '" + trolley.getBuyNum() + "' WHERE id= '" + trolley.getId()
				+ "'";
		rows = conn.executeUpdate(updSql);
		conn.close();
		return rows;
	}

}
