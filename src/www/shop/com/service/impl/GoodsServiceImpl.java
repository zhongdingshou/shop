package www.shop.com.service.impl;

import java.util.List;

import www.shop.com.dao.IGoodsDao;
import www.shop.com.dao.impl.GoodsDaoImpl;
import www.shop.com.dao.po.Goods;
import www.shop.com.service.IGoodsService;

public class GoodsServiceImpl implements IGoodsService {
	IGoodsDao goodsDao = new GoodsDaoImpl();

	@Override
	public List<Goods> listAll(int goodsid) {
		// TODO Auto-generated method stub
		return goodsDao.listAll(goodsid);
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return goodsDao.deleteById(id);
	}

	@Override
	public int update(Goods goods) {
		// TODO Auto-generated method stub
		return goodsDao.update(goods);
	}

	@Override
	public int insert(Goods goods) {
		// TODO Auto-generated method stub
		return goodsDao.insert(goods);
	}

	@Override
	public Goods getById(int id) {
		// TODO Auto-generated method stub
		return goodsDao.getById(id);
	}

	@Override
	public List<Goods> getSomeGoods(int which) {
		// TODO Auto-generated method stub
		return goodsDao.getSomeGoods(which);
	}

	@Override
	public List<Goods> searchGoods(String word) {
		// TODO Auto-generated method stub
		return goodsDao.searchGoods(word);
	}

	@Override
	public List<Goods> getAdminGoods(int hostid) {
		// TODO Auto-generated method stub
		return goodsDao.getAdminGoods(hostid);
	}

	@Override
	public int updateImg(Goods goods) {
		// TODO Auto-generated method stub
		return goodsDao.updateImg(goods);
	}

}
