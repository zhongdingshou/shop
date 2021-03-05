package www.shop.com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import www.shop.com.dao.utils.JdbcUtil;
import www.shop.com.dao.IUserDao;
import www.shop.com.dao.po.User;

public class UserDaoImpl implements IUserDao {
	private JdbcUtil conn = new JdbcUtil();

	@Override
	public List<User> listAll() {
		List<User> users = new ArrayList<User>();
		try {
			String allSql = "SELECT id,username,password,rule,address,phone FROM user";
			// 是一张表的所有数据
			ResultSet rs = conn.executeQuery(allSql);
			while (rs.next()) {
				// 一个对象映射一条记录
				User user = new User();
				user.setId(rs.getInt(("id")));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRule(rs.getInt("rule"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getString("phone"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			conn.close();
		}
		return users;
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		int rows = -1;
		String delSql = "DELETE FROM user WHERE id=" + id;
		// 是一张表的所有数据
		rows = conn.executeUpdate(delSql);
		conn.close();
		return rows;
	}

	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		int rows = -1;
		String updSql = "UPDATE user SET username = '" + user.getUsername() + "',password = '" + user.getPassword()
				+ "',rule = '" + user.getRule() + "',address = '" + user.getAddress() + "',phone = '" + user.getPhone()
				+ "' WHERE id= '" + user.getId() + "'";
		rows = conn.executeUpdate(updSql);
		conn.close();
		return rows;
	}

	@Override
	public int insert(User user) {
		// TODO Auto-generated method stub
		int rows = -1;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		con = JdbcUtil.getConnection();
		// 3.得到StateMent接口指向的对象，并且StateMent接口指向的对象发送SQL到数据库中执行，并返回ResultSet指向的结果集对象
		String seleteSql = "SELECT id,username,password,rule,address,phone FROM user where username=?";
		try {
			ps = con.prepareStatement(seleteSql);
			ps.setString(1, user.getUsername());
			rs = ps.executeQuery();
			if (!rs.next()) {
				String insertsavSql = "INSERT INTO user(username,password) VALUES('" + user.getUsername() + "','"
						+ user.getPassword() + "')";
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
	public User getById(int id) {
		// TODO Auto-generated method stub
		User user = new User();
		String getSql = "SELECT id,username,password,rule,address,phone FROM user WHERE id=" + id;
		ResultSet rs = conn.executeQuery(getSql);
		try {
			if (rs.next()) {
				// 一个对象映射一条记录
				user.setId(rs.getInt(("id")));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRule(rs.getInt("rule"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getString("phone"));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User login(String username, String password) {
		User user = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getConnection();
			// 3.得到StateMent接口指向的对象，并且StateMent接口指向的对象发送SQL到数据库中执行，并返回ResultSet指向的结果集对象
			String seleteSql = "SELECT id,username,password,rule,address,phone FROM user where username=? and password=?";
			ps = con.prepareStatement(seleteSql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt(("id")));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRule(rs.getInt("rule"));
				user.setAddress(rs.getString("address"));
				user.setPhone(rs.getString("phone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public int add(User user) {
		// TODO Auto-generated method stub
		int rows = -1;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		con = JdbcUtil.getConnection();
		// 3.得到StateMent接口指向的对象，并且StateMent接口指向的对象发送SQL到数据库中执行，并返回ResultSet指向的结果集对象
		String seleteSql = "SELECT id,username,password,rule,address,phone FROM user where username=?";
		try {
			ps = con.prepareStatement(seleteSql);
			ps.setString(1, user.getUsername());
			rs = ps.executeQuery();
			if (!rs.next()) {
				String insertsavSql = "INSERT INTO user(username,password,rule,phone,address) VALUES('"
						+ user.getUsername() + "','" + user.getPassword() + "','" + user.getRule() + "','"
						+ user.getPhone() + "','" + user.getAddress() + "')";
				rows = conn.executeUpdate(insertsavSql);
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;
	}

}
