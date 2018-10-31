

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
		if(getProduct(id) == null && getProduct(name) == null) {//product doesn't exist, we can add it
			int openSpot = -1;
			for(int i=0; i<products.length; i++) {
				if(products[i]==null) {
					openSpot = i;
					break;
				}
			}
			products[openSpot] = new Product(id, name, description, price);
			return true;
		}
		
		
		return false;
	}
	
	public Product[] getProducts(){
		//returns all the products objects with the current category object
		return products;
	}

	public Product getProduct(int productID){
		// searches for a product in the product array by product id
		Product theProduct = null;
		for (int i=0; i<products.length; i++) {
			if(products[i]==null) {
				break;
			}
			if(products[i].getID()==productID) {
				theProduct = products[i];
				break;
			}
		}
		if (theProduct ==null) {
			return null;
		}
	return theProduct;
    }
	
	public Product getProduct(String productName){
		// searches for a product in the product array by product name
		Product theProduct = null;
		for (int i=0; i<products.length; i++) {
			if(products[i]==null) {
				break;
			}
			if(products[i].getName().equals(productName)) {
				theProduct = products[i];
				break;
			}
		}
	return theProduct;
    }
	

	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		this.name=newName;
	}
	public String toString() {
		//using this method really just for testing purposes to double check the Category objects are actually created
		
		String tmpStr = "CatName = |" + getName() + "| NumProducts = " + getProducts().length;
		return tmpStr;
	}
}
