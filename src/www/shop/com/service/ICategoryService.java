package www.shop.com.service;

import java.util.List;

import www.shop.com.dao.po.Category;

public interface ICategoryService {
	public List<Category> listAll();

	public int deleteById(int id);

	public int update(Category category);

	public int insert(Category category);

	public Category getById(int id);
}
