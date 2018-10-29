
import java.util.Arrays;

public class Customer {

	private String fName, lName;
	private int id;
	private Purchase [] purchases;
	static int numberOfCustomers = 0;

	public Customer(String fName, String lName, int id, int n) {
		
		this.fName = fName;
		this.lName = lName;
		this.id = id;
		numberOfCustomers++;
		purchases = new Purchase[n];
	}

	public Purchase addPurchase(String categoryName, Product prod, int amont, String date) {
		// makes a purchase for a product, it creates a new purchase object that has the purchase information.
		// the new purchase object information must be written to the data text file using the returned Purchase object of this method.
		// the new purchase object must be added to the purchases array

		return null;
	}

	public double totalPurchases(){
		// returns the total value of the all purchases of the current customer, this information must be displayed in the GUI JTestArea after a customer purchase is completed
		return 0;
	}

	@Override
	public String toString() {
		// it must return string of customer object info and all purchases' objects info
		return "Customer [fName=" + fName + ", lName=" + lName + ", id=" + id
				+ ", purchases=" + Arrays.toString(purchases) + "]";
	}
	
	public Purchase getPurchase(int ProductID) {
		// searches and returns a Purchase object in the purchases array using a product id 
		return null;
	}

	public int getID() {
		return id;
	}
	
	public String getfName() {
		return fName;
	}
	public String getLName() {
		return lName;
	}
	
	public void setID(int newID) {
		this.id=newID;
		
	}
	public void setFName(String newFName) {
		this.fName = newFName;
	}
	public void setLName(String newLName) {
		this.lName = newLName;
		
	}
   // add missing setters and getters methods for the private data fields.


}
