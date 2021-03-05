package www.shop.com.dao.po;

public class Myorder {
	private int id;
	private int goodsId;
	private int userId;
	private int status;

	public Myorder() {
		super();
	}

	public Myorder(int goodsId, int userId) {
		super();
		this.goodsId = goodsId;
		this.userId = userId;
	}

	public Myorder(int id, int goodsId, int userId, int status) {
		super();
		this.id = id;
		this.goodsId = goodsId;
		this.userId = userId;
		this.status = status;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Myorder [id=" + id + ", goodsId=" + goodsId + ", userId=" + userId + ", status=" + status + "]";
	}

}
