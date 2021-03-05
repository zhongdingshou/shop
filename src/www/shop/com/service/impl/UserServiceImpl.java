package www.shop.com.service.impl;

import java.util.List;

import www.shop.com.dao.IUserDao;
import www.shop.com.dao.impl.UserDaoImpl;
import www.shop.com.dao.po.User;
import www.shop.com.service.IUserService;

public class UserServiceImpl implements IUserService {

	IUserDao userDao = new UserDaoImpl();

	@Override
	public List<User> listAll() {
		// TODO Auto-generated method stub
		return userDao.listAll();
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return userDao.deleteById(id);
	}

	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return userDao.update(user);
	}

	@Override
	public int insert(User user) {
		// TODO Auto-generated method stub
		return userDao.insert(user);
	}

	@Override
	public User getById(int id) {
		// TODO Auto-generated method stub
		return userDao.getById(id);
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return userDao.login(username, password);
	}

	@Override
	public int add(User user) {
		// TODO Auto-generated method stub
		return userDao.add(user);
	}

}
