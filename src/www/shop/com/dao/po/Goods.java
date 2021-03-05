package www.shop.com.dao.po;

public class Goods {
	private int id;
	private String name;
	private String represent;
	private double money;
	private String img;
	private int hostId;
	private int categoryId;
	private int status;

	public Goods() {
		super();
	}

	public Goods(int id, String img) {
		super();
		this.id = id;
		this.img = img;
	}

	public Goods(String name, String represent, double money, int hostId, int categoryId, int status) {
		super();
		this.name = name;
		this.represent = represent;
		this.money = money;
		this.hostId = hostId;
		this.categoryId = categoryId;
		this.status = status;
	}

	public Goods(int id, String name, String represent, double money, int categoryId, int status) {
		super();
		this.id = id;
		this.name = name;
		this.represent = represent;
		this.money = money;
		this.categoryId = categoryId;
		this.status = status;
	}

	public Goods(int id, String name, String represent, double money, String img, int hostId, int categoryId,
			int status) {
		super();
		this.id = id;
		this.name = name;
		this.represent = represent;
		this.money = money;
		this.img = img;
		this.hostId = hostId;
		this.categoryId = categoryId;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRepresent() {
		return represent;
	}

	public void setRepresent(String represent) {
		this.represent = represent;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getHostId() {
		return hostId;
	}

	public void setHostId(int hostId) {
		this.hostId = hostId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", represent=" + represent + ", money=" + money + ", img=" + img
				+ ", hostId=" + hostId + ", categoryId=" + categoryId + ", status=" + status + "]";
	}

}
