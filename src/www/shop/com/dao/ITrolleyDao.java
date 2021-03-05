package www.shop.com.dao;

import java.util.List;

import www.shop.com.dao.po.Trolley;

public interface ITrolleyDao {
	
	List<Trolley> listAll(int userId);

	public int deleteById(int id, int userid);

	public int insert(Trolley trolley);
	
	public int update(Trolley trolley);

}
