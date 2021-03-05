package www.shop.com.service;

import java.util.List;

import www.shop.com.dao.po.Goods;

public interface IGoodsService {
	public List<Goods> listAll(int goodsid);

	public List<Goods> searchGoods(String word);

	public int deleteById(int id);

	public int update(Goods goods);

	public int updateImg(Goods goods);

	public int insert(Goods goods);

	public Goods getById(int id);

	public List<Goods> getSomeGoods(int which);

	public List<Goods> getAdminGoods(int hostid);
}
