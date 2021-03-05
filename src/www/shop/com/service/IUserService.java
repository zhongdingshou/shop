package www.shop.com.service;

import java.util.List;

import www.shop.com.dao.po.User;

public interface IUserService {
	/**
	 * 查询全部用户信息
	 * 
	 * @return 返回全部用户信息
	 */
	public List<User> listAll();

	/**
	 * 根据id删除一个用户信息
	 * 
	 * @param id 主键
	 * @return 删除成功返回受影响的行数，失败返回-1
	 */
	public int deleteById(int id);

	/**
	 * 修改一个用户信息
	 * 
	 * @param user
	 * @return 修改成功返回受影响的行数，失败返回-1
	 */
	public int update(User user);

	/**
	 * 添加一个用户信息
	 * 
	 * @param user
	 * @return 添加成功返回受影响的行数，失败返回-1
	 */
	public int insert(User user);

	/**
	 * 根据id查询一个用户信息
	 * 
	 * @param id 主键
	 * @return 查询成功返回一个用户信息，失败返回null
	 */
	public User getById(int id);

	/**
	 * 登录功能
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @return 登录成功返回一个用户信息，失败返回null
	 */
	public User login(String username, String password);

	public int add(User user);
}
