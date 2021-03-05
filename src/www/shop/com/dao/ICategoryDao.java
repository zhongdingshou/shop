package www.shop.com.dao;

import java.util.List;

import www.shop.com.dao.po.Category;

public interface ICategoryDao {

	public List<Category> listAll();

	public int deleteById(int id);

	public int update(Category category);

	public int insert(Category category);

	public Category getById(int id);

}
