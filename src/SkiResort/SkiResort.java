package SkiResort;
import java.util.Arrays;

import javax.swing.JOptionPane;

//***********************************************************************
//*																		*
//* CIS611					Fall 2018									*
//*																		*
//*						Program Assignment PA06							*
//*																		*
//*						ski resort system, "resort" class				*
//*																		*
//*						Created 22 Oct 2018								*
//*																		*
//*						Saved in SkiResort.java						*
//*																		*
//***********************************************************************

import java.io.*;

public class SkiResort {
	
	SkiCustomer[] customers;
	String fileName;
	
	SkiResort(int n){
		customers = new SkiCustomer[n]; //Initiates the customers array
		fileName = "SkiCustomer.txt";
	}
	
	// implement the following methods as specified in the assignment
	
	//member customer
	SkiCustomer addCustomer(String custName, int daysOfStay, int numItems, boolean isFirstTime, boolean isCoupon, String memberID){
		int openSpot = -1;
		for(int i = 0; i < customers.length; i++) {
			if(customers[i] == null) {
				openSpot = i;
				
				break;
			}
		}
		

		customers[openSpot]= new SkiCustomer(custName, daysOfStay, numItems, isFirstTime, isCoupon, memberID);
		return customers[openSpot];
	}
	
	//non member customer
	SkiCustomer addCustomer(String custName, int daysOfStay, int numItems, boolean isFirstTime, boolean isCoupon){
		int openSpot = -1;
		for(int i = 0; i < customers.length; i++) {
			if(customers[i] == null) {
				openSpot = i;
				break;
			}
		}
		
		customers[openSpot]= new SkiCustomer(custName, daysOfStay, numItems, isFirstTime, isCoupon);
		return customers[openSpot];
	}
	
	String sortCustomer() {
		String messageToSend = "\n ***ERROR attempting to sort file***\n";
		
		for(int i = 0; i < customers.length; i++) {
			if(customers[i]==null) {
				//null customer causes problems and can't sort
				return messageToSend;
			}
		}
		
		int max;
		SkiCustomer temp;
		
		for (int index = 0 ; index < customers.length-1; index++) {
			//having this reset the variable ensures that a success message is only sent on a clean exit of the loop
			messageToSend = "\n ***ERROR attempting to sort file***\n"; 
			max = index;
			
			for(int scan = index+1; scan < customers.length; scan++) {
				if(customers[scan].getTotalCharges() > customers[max].getTotalCharges() ) {
					max = scan;
				}
			}
			temp = customers[max];
			customers[max] = customers[index];
			customers[index] = temp;
			
			messageToSend = "\n Array SORTED \n"; //will only hit this part of code if successful replace and sort, and 
		}
		


		return messageToSend;
	}
    String writeToFile() {
    	String messageToSend = "\n ***ERROR attempting to write to file***\n";
    	File file = new File (fileName);
    	String[] output = new String[customers.length];
    	String s = " , ";
    	

    	for(int i = 0; i < customers.length; i++) {
    		if(customers[i] == null) {
    			System.out.println("Error writing becauase null for cust" + i);
    			return messageToSend;
    		}
    		else {
    			output[i] = customers[i].getCustomerName() + s
    					+ customers[i].getDaysOfStay() + s
    					+ customers[i].getNumOfRentalItems() + s
    					+ customers[i].calculateBasePrice(customers[i].getNumOfRentalItems(), customers[i].getDaysOfStay()) + s
    					+ customers[i].getTotalDiscountAmount() + s
    					+ customers[i].getSalesTaxAmount() + s
    					+ customers[i].getTotalCharges();
    			}
    	}
    	try{
    		PrintWriter write = new PrintWriter(fileName);
    		for (int i = 0; i < customers.length; i++) {
    			write.println(output[i]);
    		} 		
    		write.close();
    		messageToSend = "\n FILE Written to " + fileName;
    		}
    	catch (Exception ex) {
    		return messageToSend;
    	}

    	
    	
    	//if all code executes correctly, it will change to output a normal message
    	return messageToSend;
    }
    
    String display(){
    	String theStringToSend ="";
    	
    	for(int i = 0; i<customers.length; i++) {
    		//System.out.println(i + " is i");
    		if(customers[i] == null ) {
    			//null customer will just skip it
    			System.out.println("displaying null for cust" + i);
    			theStringToSend = "\n ***ERROR Must create all customers before sorting and displaying***\n";
    			break;
    			
    		}
    		else {
    			//System.out.println("cust"+i+"*******" + customers[i].toString());
    			theStringToSend += "** " + customers[i].toString() + " **" + "\n";
    		}
    		
    	
    	}
    	
    	return theStringToSend;
    }


}
