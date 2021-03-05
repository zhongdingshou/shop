package www.shop.com.dao.po;

public class Trolley {

	private int id;
	private int goodsId; 
	private int userId;
	private int buyNum;
	
	
	public Trolley() {
		super();
	}
	
	public Trolley(int id, int buyNum) {
		super();
		this.id = id;
		this.buyNum = buyNum;
	}

	public Trolley(int goodsId, int userId, int buyNum) {
		super();
		this.goodsId = goodsId;
		this.userId = userId;
		this.buyNum = buyNum;
	}

	public Trolley(int id, int goodsId, int userId,int buyNum) {
		super();
		this.id = id;
		this.goodsId = goodsId;
		this.userId = userId;
		this.buyNum = buyNum;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}

	@Override
	public String toString() {
		return "Trolley [id=" + id + ", goodsId=" + goodsId + ", userId=" + userId + ", buyNum=" + buyNum + "]";
	}

}
