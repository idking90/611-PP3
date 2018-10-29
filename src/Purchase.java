

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
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public Product getProduct() {
		return product;
		
	}
	
	public int getAmount() {
		return amount;
	}
	
	public String getPurchaseDate() {
		return purchaseDate;
	}
	
	public void setCategoryName(String newName) {
		this.categoryName=newName;
	}
	
	public void setProduct(Product newProd) {
		this.product=newProd;
	}
	public void setAmount(int newAmt) {
		this.amount = newAmt;
	}
	public void setPurchaseDate(String newDate) {
		this.purchaseDate=newDate;
	}






}
