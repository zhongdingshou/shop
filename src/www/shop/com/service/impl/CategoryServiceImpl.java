package www.shop.com.service.impl;

import java.util.List;

import www.shop.com.dao.ICategoryDao;
import www.shop.com.dao.impl.CategoryDapImpl;
import www.shop.com.dao.po.Category;
import www.shop.com.service.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {
	ICategoryDao categoryDao = new CategoryDapImpl();

	@Override
	public List<Category> listAll() {
		// TODO Auto-generated method stub
		return categoryDao.listAll();
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return categoryDao.deleteById(id);
	}

	@Override
	public int update(Category category) {
		// TODO Auto-generated method stub
		return categoryDao.update(category);
	}

	@Override
	public int insert(Category category) {
		// TODO Auto-generated method stub
		return categoryDao.insert(category);
	}

	@Override
	public Category getById(int id) {
		// TODO Auto-generated method stub
		return categoryDao.getById(id);
	}

}
