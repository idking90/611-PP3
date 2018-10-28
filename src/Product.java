
public class Product {
	
	private int id;
	private String name;
	private String description;
	private double price;
	static int numberOfProducts = 0;

	public Product(int id, String name, String description, double price) {

		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		numberOfProducts++;
	}

	// add missing setters and getters methods for the private data fields.
	
	public String toString() {
		// it should returns all the product object info
		return "Product [id=" + id + ", name=" + name + ", description="
				+ description + ", price=" + price + "]";
	}

	


}
