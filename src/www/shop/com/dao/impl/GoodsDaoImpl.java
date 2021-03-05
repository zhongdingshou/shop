package www.shop.com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import www.shop.com.dao.IGoodsDao;
import www.shop.com.dao.po.Category;
import www.shop.com.dao.po.Goods;
import www.shop.com.dao.utils.JdbcUtil;

public class GoodsDaoImpl implements IGoodsDao {

	private JdbcUtil conn = new JdbcUtil();

	@Override
	public List<Goods> listAll(int goodsid) {
		// TODO Auto-generated method stub
		List<Goods> allGoods = new ArrayList<Goods>();
		try {
			String allSql = "SELECT id,name,represent,money,img,host_id,category_id,status FROM goods WHERE category_id="
					+ goodsid;
			// ��һ�ű����������
			ResultSet rs = conn.executeQuery(allSql);
			while (rs.next()) {
				// һ������ӳ��һ����¼
				Goods newOne = new Goods();
				newOne.setId(rs.getInt("id"));
				newOne.setName(rs.getString("name"));
				newOne.setRepresent(rs.getString("represent"));
				newOne.setMoney(rs.getDouble("money"));
				newOne.setImg(rs.getString("img"));
				newOne.setHostId(rs.getInt("host_id"));
				newOne.setCategoryId(rs.getInt("category_id"));
				newOne.setStatus(rs.getInt("status"));
				allGoods.add(newOne);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ر���Դ
			conn.close();
		}
		return allGoods;
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		int rows = -1;
		String delSql = "DELETE FROM goods WHERE id=" + id;
		// ��һ�ű����������
		rows = conn.executeUpdate(delSql);
		conn.close();
		return rows;
	}

	@Override
	public int update(Goods goods) {
		// TODO Auto-generated method stub
		int rows = -1;
		String updSql = "UPDATE goods SET name = '" + goods.getName() + "',represent = '" + goods.getRepresent()
				+ "',money = '" + goods.getMoney() + "',category_id = '" + goods.getCategoryId() + "',status = '"
				+ goods.getStatus() + "' WHERE id= '" + goods.getId() + "'";
		rows = conn.executeUpdate(updSql);
		conn.close();
		return rows;
	}

	@Override
	public int insert(Goods goods) {
		// TODO Auto-generated method stub
		int rows = -1;
		// 3.�õ�StateMent�ӿ�ָ��Ķ��󣬲���StateMent�ӿ�ָ��Ķ�����SQL�����ݿ���ִ�У�������ResultSetָ��Ľ��������
		String insertsavSql = "INSERT INTO goods(name,represent,money,host_id,category_id,status) VALUES('"
				+ goods.getName() + "','" + goods.getRepresent() + "','" + goods.getMoney() + "','" + goods.getHostId()
				+ "','" + goods.getCategoryId() + "','" + goods.getStatus() + "')";
		rows = conn.executeUpdate(insertsavSql);
		conn.close();
		return rows;
	}

	@Override
	public Goods getById(int id) {
		// TODO Auto-generated method stub
		Goods goods = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getConnection();
			// 3.�õ�StateMent�ӿ�ָ��Ķ��󣬲���StateMent�ӿ�ָ��Ķ�����SQL�����ݿ���ִ�У�������ResultSetָ��Ľ��������
			String getSql = "SELECT id,name,represent,money,img,host_id,category_id,status FROM goods WHERE id=?";
			ps = con.prepareStatement(getSql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				goods = new Goods();
				goods.setId(rs.getInt("id"));
				goods.setName(rs.getString("name"));
				goods.setRepresent(rs.getString("represent"));
				goods.setMoney(rs.getDouble("money"));
				goods.setImg(rs.getString("img"));
				goods.setHostId(rs.getInt("host_id"));
				goods.setCategoryId(rs.getInt("category_id"));
				goods.setStatus(rs.getInt("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ر���Դ
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return goods;
	}

	@Override
	public List<Goods> getSomeGoods(int which) {
		// TODO Auto-generated method stub
		List<Goods> whichGoods = new ArrayList<Goods>();
		try {
			String whichSql = "SELECT * FROM goods WHERE status=1 ORDER BY id LIMIT 5";
			switch (which) {
			case 1: {
				whichSql = "SELECT id,name,represent,money,img,host_id,category_id,status FROM goods WHERE status=1 ORDER BY id DESC LIMIT 5";
				break;
			}
			case 2: {
				String findSql = "SELECT name,represent FROM category";
				List<Category> allCategory = new ArrayList<Category>();
				// ��һ�ű����������
				ResultSet ret = conn.executeQuery(findSql);
				while (ret.next()) {
					// һ������ӳ��һ����¼
					Category oneCategory = new Category();
					oneCategory.setName(ret.getString("name"));
					oneCategory.setRepresent(ret.getString("represent"));
					allCategory.add(oneCategory);
				}
				whichSql = "SELECT id,name,represent,money,img,host_id,category_id,status FROM goods WHERE status=1 AND (name LIKE '%"
						+ allCategory.get((int) (Math.random() * allCategory.size())).getName() + "%' OR name LIKE '%"
						+ allCategory.get((int) (Math.random() * allCategory.size())).getName()
						+ "%' OR represent LIKE '%"
						+ allCategory.get((int) (Math.random() * allCategory.size())).getRepresent()
						+ "%' OR represent LIKE '%"
						+ allCategory.get((int) (Math.random() * allCategory.size())).getRepresent()
						+ "%') ORDER BY id DESC LIMIT 5";
				break;
			}
			case 3: {
				whichSql = "SELECT id,name,represent,money,img,host_id,category_id,status FROM goods  WHERE status = 1 AND (name LIKE '%�ֻ�%' OR represent LIKE '%�ֻ�%') ORDER BY id DESC LIMIT 5";
				break;
			}
			}
			// ��һ�ű����������
			ResultSet rs = conn.executeQuery(whichSql);
			while (rs.next()) {
				// һ������ӳ��һ����¼
				Goods newOne = new Goods();
				newOne.setId(rs.getInt("id"));
				newOne.setName(rs.getString("name"));
				newOne.setRepresent(rs.getString("represent"));
				newOne.setMoney(rs.getDouble("money"));
				newOne.setImg(rs.getString("img"));
				newOne.setHostId(rs.getInt("host_id"));
				newOne.setCategoryId(rs.getInt("category_id"));
				newOne.setStatus(rs.getInt("status"));
				whichGoods.add(newOne);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ر���Դ
			conn.close();
		}
		if (whichGoods.size() < 5) {
			String getSql = "SELECT * FROM goods WHERE status=1 ORDER BY id LIMIT " + (5 - whichGoods.size());
			// ��һ�ű����������
			ResultSet rs = conn.executeQuery(getSql);
			try {
				while (rs.next()) {
					// һ������ӳ��һ����¼
					Goods newOne = new Goods();
					newOne.setId(rs.getInt("id"));
					newOne.setName(rs.getString("name"));
					newOne.setRepresent(rs.getString("represent"));
					newOne.setMoney(rs.getDouble("money"));
					newOne.setImg(rs.getString("img"));
					newOne.setHostId(rs.getInt("host_id"));
					newOne.setCategoryId(rs.getInt("category_id"));
					newOne.setStatus(rs.getInt("status"));
					whichGoods.add(newOne);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return whichGoods;
	}

	@Override
	public List<Goods> searchGoods(String word) {
		// TODO Auto-generated method stub
		List<Goods> allGoods = new ArrayList<Goods>();
		try {
			String allSql = "SELECT id,name,represent,money,img,host_id,category_id,status FROM goods WHERE name LIKE '%"
					+ word + "%' OR represent LIKE '%" + word + "%'";
			// ��һ�ű����������
			ResultSet rs = conn.executeQuery(allSql);
			while (rs.next()) {
				// һ������ӳ��һ����¼
				Goods newOne = new Goods();
				newOne.setId(rs.getInt("id"));
				newOne.setName(rs.getString("name"));
				newOne.setRepresent(rs.getString("represent"));
				newOne.setMoney(rs.getDouble("money"));
				newOne.setImg(rs.getString("img"));
				newOne.setHostId(rs.getInt("host_id"));
				newOne.setCategoryId(rs.getInt("category_id"));
				newOne.setStatus(rs.getInt("status"));
				allGoods.add(newOne);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ر���Դ
			conn.close();
		}
		return allGoods;
	}

	@Override
	public List<Goods> getAdminGoods(int hostid) {
		// TODO Auto-generated method stub
		List<Goods> allGoods = new ArrayList<Goods>();
		try {
			String allSql = "SELECT id,name,represent,money,img,host_id,category_id,status FROM goods WHERE host_id="
					+ hostid;
			// ��һ�ű����������
			ResultSet rs = conn.executeQuery(allSql);
			while (rs.next()) {
				// һ������ӳ��һ����¼
				Goods newOne = new Goods();
				newOne.setId(rs.getInt("id"));
				newOne.setName(rs.getString("name"));
				newOne.setRepresent(rs.getString("represent"));
				newOne.setMoney(rs.getDouble("money"));
				newOne.setImg(rs.getString("img"));
				newOne.setHostId(rs.getInt("host_id"));
				newOne.setCategoryId(rs.getInt("category_id"));
				newOne.setStatus(rs.getInt("status"));
				allGoods.add(newOne);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ر���Դ
			conn.close();
		}
		return allGoods;
	}

	@Override
	public int updateImg(Goods goods) {
		// TODO Auto-generated method stub
		int rows = -1;
		String updSql = "UPDATE goods SET img = '" + goods.getImg() + "' WHERE id= '" + goods.getId() + "'";
		rows = conn.executeUpdate(updSql);
		conn.close();
		return rows;
	}

}
