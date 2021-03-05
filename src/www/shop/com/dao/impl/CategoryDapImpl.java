package www.shop.com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import www.shop.com.dao.ICategoryDao;
import www.shop.com.dao.po.Category;
import www.shop.com.dao.utils.JdbcUtil;

public class CategoryDapImpl implements ICategoryDao {
	private JdbcUtil conn = new JdbcUtil();

	@Override
	public List<Category> listAll() {
		// TODO Auto-generated method stub
		List<Category> categories = new ArrayList<Category>();
		try {
			String allSql = "SELECT id,name,represent FROM category ORDER BY id ASC";
			// 是一张表的所有数据
			ResultSet rs = conn.executeQuery(allSql);
			while (rs.next()) {
				// 一个对象映射一条记录
				Category category = new Category();
				category.setId(rs.getInt(("id")));
				category.setName(rs.getString("name"));
				category.setRepresent(rs.getString("represent"));
				categories.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			conn.close();
		}
		return categories;
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		int rows = -1;
		String delSql = "DELETE FROM goods WHERE category_id=" + id;
		rows = conn.executeUpdate(delSql);

		delSql = "DELETE FROM category WHERE id=" + id;
		// 是一张表的所有数据
		rows = conn.executeUpdate(delSql);
		conn.close();
		return rows;
	}

	@Override
	public int update(Category category) {
		// TODO Auto-generated method stub
		int rows = -1;
		String updSql = "UPDATE category SET name = '" + category.getName() + "',represent = '"
				+ category.getRepresent() + "' WHERE id= '" + category.getId() + "'";
		rows = conn.executeUpdate(updSql);
		conn.close();
		return rows;
	}

	@Override
	public int insert(Category category) {
		// TODO Auto-generated method stub
		int rows = -1;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		con = JdbcUtil.getConnection();
		// 3.得到StateMent接口指向的对象，并且StateMent接口指向的对象发送SQL到数据库中执行，并返回ResultSet指向的结果集对象
		String seleteSql = "SELECT id,name,represent FROM category where name=?";
		try {
			ps = con.prepareStatement(seleteSql);
			ps.setString(1, category.getName());
			rs = ps.executeQuery();
			if (!rs.next()) {
				String insertsavSql = "INSERT INTO category(name,represent) VALUES('" + category.getName() + "','"
						+ category.getRepresent() + "')";
				rows = conn.executeUpdate(insertsavSql);
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public Category getById(int id) {
		// TODO Auto-generated method stub
		Category category = new Category();
		String getSql = "SELECT id,name,represent FROM category WHERE id=" + id;
		ResultSet rs = conn.executeQuery(getSql);
		try {
			if (rs.next()) {
				// 一个对象映射一条记录
				category.setId(rs.getInt(("id")));
				category.setName(rs.getString("name"));
				category.setRepresent(rs.getString("represent"));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return category;
	}

}
