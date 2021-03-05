package www.shop.com.service;

import java.util.List;

import www.shop.com.dao.po.Myorder;

public interface IMyorderService {

	List<Myorder> listAll(int userId);

	public int deleteById(int id);

	public int changeStatus(int id, int status);

	public int insert(Myorder myorder);

	public List<Myorder> getById(int id);
}
