package www.shop.com.service;

import java.util.List;

import www.shop.com.dao.po.Trolley;

public interface ITrolleyService {

	List<Trolley> listAll(int userId);

	public int deleteById(int id, int userid);

	public int insert(Trolley trolley);
	
	public int update(Trolley trolley);

}
