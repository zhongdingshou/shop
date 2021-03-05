package www.shop.com.service.impl;

import java.util.List;

import www.shop.com.dao.IMyorderDao;
import www.shop.com.dao.impl.MyorderDaoImpl;
import www.shop.com.dao.po.Myorder;
import www.shop.com.service.IMyorderService;

public class MyorderServiceImpl implements IMyorderService {
	IMyorderDao myorderDao = new MyorderDaoImpl();

	@Override
	public List<Myorder> listAll(int userId) {
		// TODO Auto-generated method stub
		return myorderDao.listAll(userId);
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return myorderDao.deleteById(id);
	}

	@Override
	public int insert(Myorder myorder) {
		// TODO Auto-generated method stub
		return myorderDao.insert(myorder);
	}

	@Override
	public List<Myorder> getById(int id) {
		// TODO Auto-generated method stub
		return myorderDao.getById(id);
	}

	@Override
	public int changeStatus(int id, int status) {
		// TODO Auto-generated method stub
		return myorderDao.changeStatus(id, status);
	}

}
