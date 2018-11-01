package TechStore;

//***********************************************************************
//*																		*
//* CIS611					Fall 2018									*
//*																		*
//*						Program Assignment PP03							*
//*																		*
//*						TechStore system, TechStore class				*
//*																		*
//*						Created 31 Oct 2018								*
//*																		*
//*						Saved in TechStore.java						*
//*																		*
//***********************************************************************

import java.util.Scanner;
import java.io.*;

public class TechStore {

	private Customer[] customers;
	private Category[] categories;
	private String fileName;

	// Constructor
	public TechStore(String fileName) throws FileNotFoundException {
		// instantiate the TechStore object
		this.fileName = fileName;
		this.customers = new Customer[3];
		this.categories = new Category[3];
		this.readFile(fileName);
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
		int openSpot = -1;
		for(int i = 0; i < categories.length; i++) {
			if(categories[i] == null) {
				openSpot = i;
				break;
			}
		}
		categories[openSpot] = new Category(name);
	}

	public void addPurchase(Customer customer, String categoryName, Product prod, int amount, String date) {
		// calls customer object to purchase a product
		customer.addPurchase(categoryName, prod, amount, date);
		String lineInFile = "purchase; " + customer.getID() + "; " + categoryName + "; "+ prod.getID() + " ;" + amount + "; " + date;		
		
	}
	
	public void addProduct(String categoryName, int id, String name, String description, double price) {
		// Adds a product object to Category object that matches the categoryName parameter
		Category theCategory = getCategory(categoryName);
		theCategory.addProduct(id, name, description, price); //causes "Exception in thread "main" java.lang.NullPointerException"
	}
	
	public Customer getCustomer(String name){

		for(int i=0; i<customers.length; i++) {
			if (customers[i]==null) {
				break;
			}
			String fullName = customers[i].getfName().trim() + " " + customers[i].getLName().trim();
			if (fullName.equals(name)) {
				return customers[i];
			}
		}
		return null;
	}

	public Customer[] getAllCustomers() {
		return customers;
	}
	
	public String[] getCustFullNames() {
		String[] custNames = new String[customers.length + 1];
		custNames[0]=" ";
		for(int i=0; i<customers.length;i++) {
			if(customers[i]==null) {
				return null;
			}
			custNames[i+1]=customers[i].getfName().trim() + " " + customers[i].getLName().trim();
		}
		return custNames;
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
		Category theCategory=null;
		for(int i=0; i<categories.length; i++) {
			
			if (categoryName.equals(categories[i].getName())) {
				theCategory = categories[i];
				
				break;
			}
		}
		
		
		return theCategory;
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
			if (theProductsInSameCategory[i].getName().matches(productName)) {
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
	
	public Category[] getCategories() {
		return categories;
	}
	public String[] getCatNames() {
		String[] catNames = new String[categories.length + 1];
		catNames[0]=" ";
		for(int i=0; i<categories.length;i++) {
			if(categories[i]==null) {
				return null;
			}
			catNames[i+1]=categories[i].getName();
		}
		return catNames;
	}
	public void readFile(String fileName) throws FileNotFoundException {
		// read data from the data.txt file and instantiate all project objects (arrays).
		// some object arrays are in other objects such as customer and category classes
		// it creates object for each string data in the input file, line by line
      
      java.io.File file = new java.io.File(fileName);
	   Scanner input = new Scanner(file);
      
      while (input.hasNext()) {
         String line = input.nextLine();
         String[] type = line.split(";");

         //separates/declares every object in each line
            if (type[0].equalsIgnoreCase("customer")) {
               String fName = type[1];
               String lName = type[2];
               int id = Integer.parseInt(type[3].trim());
               
               addCustomer(fName, lName, id);
               
               
            } else if (type[0].equalsIgnoreCase("category")) {
               String category = type[1].trim(); 
               
               addCategory(category);
               // System.out.println(categories[0].toString()); //**testing to make sure object is created
               
            } else if (type[0].equalsIgnoreCase("product")) {
               String categoryName = type[1].trim(); 
               int productId = Integer.parseInt(type[2].trim());
               String name = type[3].trim();
               String description = type[4].trim();
               double price = Double.parseDouble(type[5].trim());
               
               addProduct(categoryName, productId, name, description, price);
               // System.out.println(categories[0].getProducts()[0].toString()); //shows first product of first category, just to test method calls
               
            } else if (type[0].equalsIgnoreCase("purchase")) {
               int custId = Integer.parseInt(type[1].trim());
               String categoryName = type[2].trim();
               int productId = Integer.parseInt(type[3].trim());
               int amount = Integer.parseInt(type[4].trim());
               String purchaseDate = type[5].trim();
               
               Customer cust = getCustomer(custId);
               Product prod = getProduct(categoryName, productId);
               addPurchase(cust, categoryName, prod, amount, purchaseDate);
               // System.out.println("thisCustPurch0 " + cust.getPurchases()[0].toString());//for now, shows first purchase of first customer just to prove it successfully creates
                
            } else {
               System.out.println("error!");
            } 
              
         }

	    input.close();

    
	}

    public void writeFile(String trans) {
		// write/append any new transaction (purchase) data to the data.txt file
    	
    	try { 
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(trans);
            bw.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
	}

}