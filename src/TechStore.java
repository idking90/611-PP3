

public class TechStore {

	private Customer[] customers;
	private Category[] categories;
	private String fileName;

	// Constructor
	public TechStore(String fileName) {
		// instantiate the TechStore object
		this.fileName = fileName;
		this.customers = new Customer[3];
		this.categories = new Category[3];
	}

	// add missing setters and getters methods for the private data fields.
	
		
	public void addCustomer(String fName, String lName, int id) {
			// Creates and adds a new Customer object to the customers array
	}

	public void addCategory (String name) {
			// Creates and adds a new Category object to the categories array
	}

	public void addPurchase(Customer customer, String categoryName, Product prod, int amont, String date) {
		// calls customer object to purchase a product
	}
	
	public void addProduct(String categoryName, int id, String name, String description, double price) {
		// Adds a product object to Category object that matches the categoryName parameter
	}
	
	public Customer getCustomre(String name){
		// search for a Customer object by customer name in the customers array
		return null;
	}

	public Customer getCustomre(int customerID){
			// search for a Customer object by customer id in the customers array 
			return null;
	}

	public Category getCategory(String categoryName){
		// search for a Category object by category name in the purchases array
		return null;
	}

	public Product[] getProducts(String categoryName) {
		// returns an array of Product objects for a Category object that matches the 'categoryName' parameter 
		return null;
     }

	public Product getProduct(String categoryName, String productName) {
		// returns a Product object with name matches the productName parameter in a Category object that matches the 'categoryName' parameter 
		return null;
    }
	
	public Product getProduct(String categoryName, int productID) {
		// returns a Product object with id matches the productID parameter in a Category object that matches the 'categoryName' parameter 
		return null;
    }
	
	
	public void readFile() {
		// read data from the data.txt file and instantiate all project objects (arrays).
		// some object arrays are in other objects such as customer and category classes
		// it creates object for each string data in the input file, line by line
	}

    public void writeFile(String trans) {
		// write/append any new transaction (purchase) data to the data.txt file
	}

}
