package www.shop.com.dao.po;

public class User {

	private int id;
	private String username;
	private String password;
	private int rule;
	private String address;
	private String phone;

	public User() {
		super();
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, int rule, String address, String phone) {
		super();
		this.username = username;
		this.password = password;
		this.rule = rule;
		this.address = address;
		this.phone = phone;
	}

	public User(int id, String username, String password, int rule, String address, String phone) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.rule = rule;
		this.address = address;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRule() {
		return rule;
	}

	public void setRule(int rule) {
		this.rule = rule;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", rule=" + rule + ", address="
				+ address + ", phone=" + phone + "]";
	}

}
