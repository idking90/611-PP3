package TechStore;

import java.util.Scanner;
import java.io.*;

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
	
		
	public void addCustomer(String fName, String lName, int id) {
			// Creates and adds a new Customer object to the customers array
		int openSpot = -1;
		for(int i = 0; i < customers.length; i++) {
			if(customers[i] == null) {
				openSpot = i;
				break;
			}
		}
		
		customers[openSpot] = new Customer(fName, lName, id);
	}

	public void addCategory (String name) {
			// Creates and adds a new Category object to the categories array
		
		new Category(name);
	}

	public void addPurchase(Customer customer, String categoryName, Product prod, int amount, String date) {
		// calls customer object to purchase a product
		customer.addPurchase(categoryName, prod, amount, date);
		String lineInFile = "purchase; " + customer.getID() + "; " + categoryName + "; "+ prod.getID() + " ;" + amount + "; " + date;		
		writeFile(lineInFile);
	}
	
	public void addProduct(String categoryName, int id, String name, String description, double price) {
		// Adds a product object to Category object that matches the categoryName parameter
		Category theCategory = getCategory(categoryName);
		theCategory.addProduct(id, name, description, price); //causes "Exception in thread "main" java.lang.NullPointerException"
	}
	
	public Customer getCustomer(String name){

		for(int i=0; i<customers.length; i++) {
			if (customers[i]==null) {
				return null;
			}
			String fullName = customers[i].getfName() + " " +customers[i].getLName();
			if (fullName.equals(name)) {
				return customers[i];
			}
		}
		return null;
	}

	public Customer getCustomer(int customerID){
			// search for a Customer object by customer id in the customers array 
		for(int i=0; i<customers.length; i++) {
			if (customers[i]==null) {
				return null;
			}
			
			if (customerID == customers[i].getID()) {
				return customers[i];
			}
		}	
		
		
		return null;
	}

	public Category getCategory(String categoryName){
		// search for a Category object by category name in the purchases array
		for(int i=0; i<categories.length; i++) {
			if (categories[i]==null) {
				return null;
			}
			
			if (categoryName == categories[i].getName()) {
				return categories[i];
			}
		}
		
		
		return null;
	}

	public Product[] getProducts(String categoryName) {
		// returns an array of Product objects for a Category object that matches the 'categoryName' parameter 
		//find the category first
		Category theCategory = getCategory(categoryName);
		Product[] theProducts = theCategory.getProducts();	
		
		return theProducts;
     }

	public Product getProduct(String categoryName, String productName) {
		// returns a Product object with name matches the productName parameter in a Category object that matches the 'categoryName' parameter 
		//get all the products of that category first, then search through that
		Product[] theProductsInSameCategory = getProducts(categoryName);
		for(int i= 0; i<theProductsInSameCategory.length; i++) {
			if (theProductsInSameCategory[i].getName().equals(productName)) {
				return theProductsInSameCategory[i];
			}
		}
		return null;
    }
	
	public Product getProduct(String categoryName, int productID) {
		// returns a Product object with id matches the productID parameter in a Category object that matches the 'categoryName' parameter 
		Product[] theProductsInSameCategory = getProducts(categoryName);
		for(int i= 0; i<theProductsInSameCategory.length; i++) {
			if (theProductsInSameCategory[i].getID() == productID) {
				return theProductsInSameCategory[i];
			}
		}
		
		
		return null;
    }
	
	
	public void readFile(String fileName) throws FileNotFoundException {
		// read data from the data.txt file and instantiate all project objects (arrays).
		// some object arrays are in other objects such as customer and category classes
		// it creates object for each string data in the input file, line by line
      
      java.io.File file = new java.io.File(fileName);
	   Scanner input = new Scanner(file);
      
      while (input.hasNext()) {
         String line = input.nextLine();
         System.out.println(line);
         String[] type = line.split(";");
         
         
         //separates/declares every object in each line, not sure if this is the approach I should be taking
            if (type[0].matches("customer")) {
               String fName = type[1];
               String lName = type[2];
               int id = Integer.parseInt(type[3].trim());
                              
               addCustomer(fName, lName, id);
               
            } else if (type[0].matches("category")) {
               String category = type[1]; 
               
               addCategory(category);             
               
            } else if (type[0].matches("product")) {
               String categoryName = type[1]; 
               int productId = Integer.parseInt(type[2].trim());
               String name = type[3];
               String description = type[4];
               double price = Double.parseDouble(type[5].trim());
               
               addProduct(categoryName, productId, name, description, price);
               
            } else if (type[0].matches("purchase")) {
               int custId = Integer.parseInt(type[1].trim());
               String categoryName = type[2];
               int productId = Integer.parseInt(type[3].trim());
               int amount = Integer.parseInt(type[4].trim());
               String purchaseDate = type[5];
               
               //not sure what to do here yet
               //addPurchase(custId, categoryName, productId, amount, purchaseDate);
                
            } else {
               System.out.println("error!");
            } 
              
         }

	    input.close();

    
	}

    public void writeFile(String trans) {
		// write/append any new transaction (purchase) data to the data.txt file
	}

}