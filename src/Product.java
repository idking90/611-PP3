package TechStore;

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
		return "Product [ID = " + id + ", Name = " + name + ", Description = "
				+ description + ", Price = $" + price + "]";
	}

	public int getID() {
		return id;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	
	public void setID(int newID) {
		this.id=newID;
		
	}
	
	public void setPrice(double newPrice) {
		this.price=newPrice;
	}
	
	public void setName(String newName) {
		this.name=newName;
	}
	
	public void setDescription(String newDesc) {
		this.description=newDesc;
		
	}

}