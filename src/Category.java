

public class Category {

	private String name;
	private Product[] products;
	static int numberOfCategories  = 0;


	public Category(String name) {

		this.name = name;
		this.products = new Product[20];
		numberOfCategories++;
	}

	// add missing setters and getters methods for the private data fields.
	
	public boolean addProduct(int id, String name, String description, double price) {
			// adds a new product to the products array, and it returns true if the product is added (the product does not exist before), false otherwise
			// it must call getProduct() to check if the product exist in the array
	return true;
	}
	
	public Product[] getProducts(){
		//returns all the products objects with the current category object
		return null;
	}

	public Product getProduct(int productID){
		// searches for a product in the product array by product id
	return null;
    }
	
	public Product getProduct(String productName){
		// searches for a product in the product array by product name
	return null;
    }
}
