

public class Purchase {

	private String categoryName; 
	private Product product;
	private int amount;
	private String purchaseDate;
	static int numberOfPurchase = 0;


	// constructor  
	public Purchase(String categoryName, Product product, int amount, String purchaseDate) {

		this.product = product;
		this.amount = amount;
		this.purchaseDate = purchaseDate;
		numberOfPurchase++;
	}

	// add missing setters and getters methods for the private data fields.
}
