package SkiResort;
//***********************************************************************
//*																		*
//* CIS611					Fall 2018									*
//*																		*
//*						Program Assignment PA06							*
//*																		*
//*						ski resort system, customer class				*
//*																		*
//*						Created 22 Oct 2018								*
//*																		*
//*						Saved in SkiCustomer.java						*
//*																		*
//***********************************************************************

public class SkiCustomer {
	
	private String customerName;
	private int daysOfStay;
	private int numberOfRentalItems; //can be only 0,1,2,3
	private String memberID; // this could be null for non-members 
	private	double memberDiscount; // in % from specification
	private	double firstTimeUserDiscount; // in % from specification 
	private double couponDiscountAmount; // from the specifications
	private	double salesTaxAmount; // calculated value 
	private	double totalDiscountAmount; //Calculated value
	private	double totalCharges; // calculated value 

	private final double SALES_TAX_PERCENTAGE = 0.075;
	private static int noSkiCustomers = 0;
	private final double ZERO_ITEM_RENTAL = 60.0;
	private final double ONE_ITEM_RENTAL = 110.0;
	private final double TWO_ITEM_RENTAL = 155.0;
	private final double THREE_ITEM_RENTAL = 190.0;
	private final double MBR_DISC_0_ITEM = .05;
	private final double MBR_DISC_WITH_ITEMS = .08;
	private final double FIRST_USE_0_ITEM = .10;
	private final double FIRST_USE_WITH_ITEMS = .12;
	private final double COUPON_0_ITEM = 5.0;
	private final double COUPON_WITH_ITEMS = 10.0;
	
	
	// provide the constructor of this class and you must increment noSkiCustomers data field by 1 in the constructor
	
	//non-member constructor
	public SkiCustomer(String custName, int daysOfStay, int noOfRentalItems, boolean isFirstTimeUser,
			boolean isCoupon){
		this.customerName = custName;
		this.daysOfStay = daysOfStay;
		this.numberOfRentalItems = noOfRentalItems;
		
		if(isFirstTimeUser) {
			this.firstTimeUserDiscount = calculateFirstTimeUserDiscount(noOfRentalItems);
		}
		else {
			this.firstTimeUserDiscount = 0;
		}
		
		if(isCoupon) {
			this.couponDiscountAmount = calculateCouponDiscount(noOfRentalItems);
		}
		else {
			this.couponDiscountAmount = 0;
		}
		
		this.memberDiscount = 0;
		this.memberID = null;
		this.totalDiscountAmount = calculateDiscount(isCoupon, false, isFirstTimeUser, noOfRentalItems, daysOfStay);
		this.salesTaxAmount = calculateSalesTax(noOfRentalItems, daysOfStay, isCoupon, false, isFirstTimeUser);
		this.totalCharges = calculateTotalCharges(noOfRentalItems, daysOfStay, isCoupon, false, isFirstTimeUser);
		
		noSkiCustomers ++;
		
		
	}
	//member constructor
	public SkiCustomer(String custName, int daysOfStay, int noOfRentalItems, boolean isFirstTimeUser,
			boolean isCoupon,String memberID){
		this.customerName = custName;
		this.daysOfStay = daysOfStay;
		this.numberOfRentalItems = noOfRentalItems;
		
		if(isFirstTimeUser) {
			this.firstTimeUserDiscount = calculateFirstTimeUserDiscount(noOfRentalItems);
		}
		else {
			this.firstTimeUserDiscount = 0;
		}
		
		if(isCoupon) {
			this.couponDiscountAmount = calculateCouponDiscount(noOfRentalItems);
		}
		else {
			this.couponDiscountAmount = 0;
		}
		
		this.memberDiscount = calculateMembershipDiscount(noOfRentalItems);
		this.memberID = memberID;
		this.totalDiscountAmount = calculateDiscount(isCoupon, true, isFirstTimeUser, noOfRentalItems, daysOfStay);
		this.salesTaxAmount = calculateSalesTax(noOfRentalItems, daysOfStay, isCoupon, true, isFirstTimeUser);
		this.totalCharges = calculateTotalCharges(noOfRentalItems, daysOfStay, isCoupon, true, isFirstTimeUser);
		
		noSkiCustomers ++;
		
	}
	
	// Add the setter and getter methods
	public String getCustomerName() {
		return customerName;
	}
	
	public int getDaysOfStay() {
		return daysOfStay;
	}
	
	public int getNumOfRentalItems() {
		return numberOfRentalItems;
	}
	
	public String getMemberID() {
		return memberID;
	}
	
	public double getMemberDiscount() {
		return memberDiscount;
	}
	
	public double getFirstTimeUserDiscount() {
		return firstTimeUserDiscount;
	}
	
	public double getCouponDiscount() {
		return couponDiscountAmount;
	}
	
	public double getSalesTaxAmount() {
		return salesTaxAmount;
	}
	
	public double getTotalDiscountAmount() {
		return totalDiscountAmount;
	}
	
	public double getTotalCharges() {
		return totalCharges;
	}
	
	public void setCustomerName(String newCustomerName) {
		this.customerName = newCustomerName;
	}
	
	public void setDaysOfStay(int newDaysOfStay) {
		this.daysOfStay = newDaysOfStay;
	}
	
	public void setNumOfRentalItems(int newNumRental) {
		this.numberOfRentalItems = newNumRental;
	}
	
	public void setMemberID(String newID) {
		this.memberID = newID;
	}
	
	//these set methods from here and below should not be called at any point, as they are specified/calculated values, but written anyway
	public void setMemberDiscount(double newDiscount) {
		this.memberDiscount = newDiscount;
	}
	
	public void setFirstTimeDiscount(double newDiscount) {
		this.firstTimeUserDiscount = newDiscount;
	}
	
	public void setCouponDiscount(double newDiscount) {
		this.couponDiscountAmount = newDiscount;
	}
	
	public void setSalesTaxAmount(double newTaxAmount) {
		this.salesTaxAmount = newTaxAmount;
	}
	
	public void setTotalDiscount(double newDiscount) {
		this.totalDiscountAmount = newDiscount;
	}
	
	public void setTotalCharges(double newTotal) {
		this.totalCharges = newTotal;
	}
	
	
	// implement the following methods, you can slightly change the return type of the methods if you like to do that
	private double calculateMembershipDiscount(int numItems) {
		double theDiscount = 0;
		if(numItems == 0) {
			theDiscount = MBR_DISC_0_ITEM;
		}
		else if (numItems >0 && numItems <4) {
			theDiscount = MBR_DISC_WITH_ITEMS;
		}
		else {//error, no discount
			theDiscount = 0;
			System.out.println("error in membership discount calculation");
		}
		return theDiscount;
		
	}
	private double calculateCouponDiscount(int numItems) {
		double theDiscount = 0;
		if(numItems == 0) {
			theDiscount = COUPON_0_ITEM;
		}
		else if (numItems >0 && numItems <4) {
			theDiscount = COUPON_WITH_ITEMS;
		}
		else {//error, no discount
			theDiscount = 0;
			System.out.println("error in coupon discount calculation");
		}
		return theDiscount;
	}
	private double calculateFirstTimeUserDiscount(int numItems) {
		double theDiscount = 0;
		if(numItems == 0) {
			theDiscount = FIRST_USE_0_ITEM;
		}
		else if (numItems >0 && numItems <4) {
			theDiscount = FIRST_USE_WITH_ITEMS;
		}
		else {//error, no discount
			theDiscount = 0;
			System.out.println("error in first time discount calculation");
		}
		return theDiscount;
	}
	private double calculateDiscount(boolean isCoupon, boolean isMember, boolean isFirstTime, int numItems, int numDays) {
		double theTotalDiscount = 0;
		double theBasePriceNoDiscount = calculateBasePrice(numItems, numDays);
		if (isCoupon) {//straight math, no percentages for a coupon
			theTotalDiscount += calculateCouponDiscount(numItems);
		}
		//below is if else about percentage discounts
		if (isMember  && isFirstTime) {
			//pure addition of percentages
			double thePercentageToUse = calculateFirstTimeUserDiscount(numItems) + calculateMembershipDiscount(numItems);
			theTotalDiscount += thePercentageToUse * theBasePriceNoDiscount;
		}
		else if (isMember && !isFirstTime) {
			//only member disc
			theTotalDiscount += calculateMembershipDiscount(numItems) * theBasePriceNoDiscount;
		}
		else if (!isMember && isFirstTime) {
			//first time only
			theTotalDiscount += calculateFirstTimeUserDiscount(numItems) * theBasePriceNoDiscount;
		}
		else { //no percentage discount, don't need to do anything
			
		}			
		
		return theTotalDiscount;
	}
	private double calculateSalesTax(int numItems, int numDays, boolean isCoupon, boolean isMember, boolean isFirstTime) {
		double theTaxAmount = 0;
		theTaxAmount = SALES_TAX_PERCENTAGE * calculatePretaxSubtotal(numItems, numDays, isCoupon, isMember, isFirstTime);
		
		return theTaxAmount;
	}
	private double calculateTotalCharges(int numItems, int numDays, boolean isCoupon, boolean isMember, boolean isFirstTime) {
		double theFinalTotal = 0;
		double theSubtotal = calculatePretaxSubtotal(numItems, numDays, isCoupon, isMember, isFirstTime);
		
		theFinalTotal = theSubtotal + calculateSalesTax(numItems, numDays, isCoupon, isMember, isFirstTime);
				
		return theFinalTotal;
	} // This method will call 4 and 5 methods to calculate the total charges and set the corresponding instance variables.
		
	public double calculateBasePrice(int numItems, int numDays) {
		double theBasePrice = 0;
		switch (numItems) {
		case 0:
			theBasePrice = ZERO_ITEM_RENTAL * numDays;
			break;
		case 1:
			theBasePrice = ONE_ITEM_RENTAL * numDays;
			break;
		case 2:
			theBasePrice = TWO_ITEM_RENTAL * numDays;
			break;
		case 3:
			theBasePrice = THREE_ITEM_RENTAL * numDays;
			break;
		default:
			theBasePrice = -99;
			System.out.println("Error in base price calculation");
			break;
		}//end switch
		
		return theBasePrice;
	}
	
	private double calculatePretaxSubtotal(int numItems, int numDays, boolean isCoupon, boolean isMember, boolean isFirstTime) {
		double theSubtotal = 0;
		theSubtotal = calculateBasePrice(numItems, numDays) - calculateDiscount(isCoupon, isMember, isFirstTime, numItems, numDays);
		return theSubtotal;
	}
	
	public String toString(){ 
	String tmpStr = "";
	String name = getCustomerName();
	int days = getDaysOfStay();
	int numItems = getNumOfRentalItems();
	double baseCharge = calculateBasePrice(numItems, days);
	double discount = getTotalDiscountAmount();
	double tax = getSalesTaxAmount();
	double finalTotal = getTotalCharges();
	
	tmpStr = "Customer name = " + name +
			" | Length of stay = " + days +
			" | Items rented = " + numItems+
			" | Base charge = $" + baseCharge+
			" | Discount total = $" + discount+
			" | Sales tax = $" + tax +
			" | Total Charges = $" + finalTotal;
		
	
	return tmpStr;
	} //This method must display the output of the SkiCustomer object

	public int compareCustomers(SkiCustomer custToCompare) {
		int result;
		if (this.getTotalCharges() == custToCompare.getTotalCharges()) {
			result = 0;
		}
		else if (this.getTotalCharges() < custToCompare.getTotalCharges()) {
			result = -1;
		}
		else if(this.getTotalCharges() > custToCompare.getTotalCharges()) {
			result = 1;
		}
		else { //error
			result = -99;
		}
		
		return result;
	}	
	

	

}
