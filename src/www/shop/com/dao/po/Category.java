package www.shop.com.dao.po;

public class Category {
	private int id;
	private String name;
	private String represent;

	public Category() {
		super();
	}

	public Category(String name, String represent) {
		super();
		this.name = name;
		this.represent = represent;
	}

	public Category(int id, String name, String represent) {
		super();
		this.id = id;
		this.name = name;
		this.represent = represent;
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

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", represent=" + represent + "]";
	}

}
