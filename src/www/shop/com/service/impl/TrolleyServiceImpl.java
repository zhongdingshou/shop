package www.shop.com.service.impl;

import java.util.List;

import www.shop.com.dao.ITrolleyDao;
import www.shop.com.dao.impl.TrolleyDaoImpl;
import www.shop.com.dao.po.Trolley;
import www.shop.com.service.ITrolleyService;

public class TrolleyServiceImpl implements ITrolleyService {
	ITrolleyDao trolleyDao = new TrolleyDaoImpl();

	@Override
	public List<Trolley> listAll(int userId) {
		// TODO Auto-generated method stub
		return trolleyDao.listAll(userId);
	}

	@Override
	public int deleteById(int id, int userid) {
		// TODO Auto-generated method stub
		return trolleyDao.deleteById(id, userid);
	}

	@Override
	public int insert(Trolley trolley) {
		// TODO Auto-generated method stub
		return trolleyDao.insert(trolley);
	}

	@Override
	public int update(Trolley trolley) {
		// TODO Auto-generated method stub
		return trolleyDao.update(trolley);
	}

}
