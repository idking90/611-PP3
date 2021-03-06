package TechStore;

//***********************************************************************
//*																		*
//* CIS611					Fall 2018									*
//*																		*
//*						Program Assignment PP03							*
//*																		*
//*						TechStore system, UserGUI class				*
//*																		*
//*						Created 31 Oct 2018								*
//*																		*
//*						Saved in UserGUI.java						*
//*																		*
//***********************************************************************

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.swing.*;

public class UserGUI extends JPanel implements ActionListener {
	
	 private JLabel lblCategory;
	 private JLabel lblProduct;
	 private JLabel lblInfo;
	 private JLabel lblCustomer;
	 private JLabel lblAmount;
	 private JLabel lblDate;
	 private JLabel lblTrans;
	 private JComboBox cboCategory;
	 private JComboBox cboProduct;
	 private JComboBox cboCustomer;
	 private JTextField txtAmount;
	 private JTextField txtDate;
	 private JScrollPane scrollProduct;
	 private JScrollPane scrollTrans;
	 private JTextArea txtArProduct;
	 private JTextArea txtArTrans;
	 private JButton btnBuy;
	 private JButton btnExit;
	 private static int numPurch = 0;
	 private static int maxPurch;

	 private static TechStore techStore;
	 private static String fileName = "data.txt";


	  public UserGUI() throws FileNotFoundException  {
      // techStore.readFile(fileName);

	    initGUI();
	    doTheLayout();
	    
	    //event listeners
	    btnExit.addActionListener( new java.awt.event.ActionListener() {
	        public void actionPerformed(ActionEvent e){
	            close();
	            }
	    });
	    
	    btnBuy.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				buyButton_ActionPerformed(e);
				
			}
		});
	    
	    cboCustomer.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		cboCustomer_ActionPerformed(e);
	    		
	    	}
	    });
	    
	    cboCategory.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		cboCategory_ActionPerformed(e);
	    	}
	    });
	    
	    cboProduct.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		cboProduct_ActionPerformed(e);
	    		
	    	}
	    });
	    
	  

	  } // end of constructor

	  private void initGUI(){
	      
		 lblCategory = new JLabel("Category: ");
		 lblCustomer = new JLabel("Customer: ");
		 lblProduct = new JLabel("Product: ");
		 lblInfo = new JLabel("Info: ");
		 lblAmount = new JLabel("Amount: ");
		 lblDate = new JLabel("Date: ");
		 lblTrans = new JLabel("Transactions: ");
		
		 String[] productTypes;
		 productTypes = techStore.getCatNames();
		 cboCategory = new JComboBox(productTypes);
		 String[] custNames;
		 custNames = techStore.getCustFullNames();
		 cboCustomer = new JComboBox(custNames);
		 cboProduct = new JComboBox(); 
		 
		 txtAmount = new JTextField("", 15);
		 txtAmount.setToolTipText("Enter whole number of items purchased");
		 txtDate = new JTextField("mm/dd/yyyy", 15);
		 txtDate.setToolTipText("Enter date of purchase as MM/DD/YYYY");
		 
		 txtArProduct = new JTextArea(5, 300);
		 txtArProduct.setEditable(false);
		 txtArProduct.setLineWrap(true);
		 txtArProduct.setWrapStyleWord(true);
		 txtArProduct.append("Product Info...");
		 scrollProduct = new JScrollPane(txtArProduct, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		 txtArTrans = new JTextArea(10, 300);
		 txtArTrans.setEditable(false);
		 txtArTrans.setLineWrap(true);
		 txtArTrans.setWrapStyleWord(true);
		 txtArTrans.append("Customer Info...");
		 scrollTrans = new JScrollPane(txtArTrans, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		 btnBuy = new JButton("Buy");
		 btnExit = new JButton("Exit");
		 

	  }// end of creating objects method

	  private void doTheLayout(){

	      JPanel top = new JPanel();
	      JPanel prodTA = new JPanel();
	      JPanel buy = new JPanel();
	      JPanel buyBtnPan = new JPanel();
	      JPanel bottom = new JPanel();
	      JPanel txtArPan = new JPanel();
	      JPanel exitPanel = new JPanel();
	      

	      
	      top.setLayout( new GridLayout(3, 2));
	      top.add(lblCategory);
	      top.add(cboCategory);
	      top.add(lblProduct);
	      top.add(cboProduct);
	      top.add(lblInfo);
	      top.add(new JLabel(""));
	      
	      prodTA.setLayout( new BorderLayout());
	      prodTA.add(scrollProduct);
	      
	      buy.setLayout(new GridLayout(4,2));
	      buy.add(lblCustomer);
	      buy.add(cboCustomer);
	      buy.add(lblAmount);
	      buy.add(txtAmount);
	      buy.add(lblDate);
	      buy.add(txtDate);
	      
	      buyBtnPan.setLayout(new FlowLayout(FlowLayout.CENTER));
	      buyBtnPan.add(btnBuy);

	      bottom.setLayout( new GridLayout());
		   bottom.setPreferredSize(new Dimension(300, 500));		      
		   bottom.setPreferredSize(new Dimension(300, 500));
	      bottom.add(lblTrans);
	       //  bottom.add(txtArTrans);
	         bottom.add(scrollTrans);
	      
	      
	      exitPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
	      exitPanel.add(btnExit);
	      
	      

	      setLayout( new GridLayout(6, 1));
	      add(top);
	      add(prodTA);
	      add(buy);
	      add(buyBtnPan);
	      add(bottom);
	      add(exitPanel);
	      
	      

	  }// end of Layout method

	 
	 /* not sure how/where we need this method
	  * void transfer(){
	        String mytext = field1.getText();
	        field2.setText(mytext);
	  }// end of transfer action event method*/
	  
	  void updateTextarea(){
		  
	  }

	  void close(){
	      System.exit(0);
	  }


	public static void main(String[] args) throws FileNotFoundException {


	      boolean isNum = false;
	         while (!isNum) {
	         try {   
               String input = JOptionPane.showInputDialog(null, "Input the number of purchases.");
	            
               if (input != null) { 
                  maxPurch = Integer.parseInt(input);
                  isNum = true;
                  } else if (input == null) { //program will exit if user selects cancel or exit
                     System.exit(0);
                  }
                     
	            if (maxPurch < 1) { //this will revert to continue the loop if 0 or negative is entered
                  JOptionPane.showMessageDialog(null, "An incorrectly formatted value was entered. Make sure number is an integer.", 
	               "Incorrect Entry!", JOptionPane.INFORMATION_MESSAGE);
	            	isNum=false;
                 }
	         } catch (NumberFormatException nfe) {
	            JOptionPane.showMessageDialog(null, "An incorrectly formatted value was entered. Make sure number is an integer.", 
	            "Incorrect Entry!", JOptionPane.INFORMATION_MESSAGE);
	           }
	         }
	     techStore = new TechStore(fileName);
		
		JFrame f = new JFrame("Tech Store");
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Container contentPane = f.getContentPane();
      contentPane.add(new UserGUI());
      f.pack();
      f.setSize(500, 500);
      f.setVisible(true);
  
	}

	public void buyButton_ActionPerformed(ActionEvent e) {
		
		Customer theCustomer;
		int numItems = 0;
		String catName = "";
		Product theProduct;
		Date theDate;
		String dateAsString="";
		Date now = new Date();
		
		//assigning variables first validating for type/format validity
		try {
		theCustomer = techStore.getCustomer(cboCustomer.getSelectedItem().toString());
		numItems = Integer.parseInt(txtAmount.getText());
		catName = cboCategory.getSelectedItem().toString();
		theProduct = techStore.getProduct(catName, cboProduct.getSelectedItem().toString());
		dateAsString = txtDate.getText();
		theDate = new SimpleDateFormat("MM/dd/yyyy").parse(dateAsString);
		
		}
		catch(Exception ex) {
			badPurchaseTrx();
			return;
		}
		
		//checking logical validity of these two variables
		if(numItems < 1 || theDate.after(now)) {
			badPurchaseTrx();
			return;
		}
		
		
		try {
			techStore.addPurchase(theCustomer, catName, theProduct, numItems, dateAsString);
		}
		catch(Exception ex) {
			badPurchaseTrx();
			return;
		}
		
		//will only get to this point if the purchase occurs successfully
		String s = " | ";
		double purchaseTotal = numItems * theProduct.getPrice() * 1.0;
		String purchaseAddedToOutputArea = "\n PURCHASE INFO: Name-" + theCustomer.getfName() + " " + theCustomer.getLName() + s +
										"Category-" + catName + s + "Product-" + theProduct.getName() + s +
										"Amount-" + numItems + s + "Date-" + dateAsString + s + "Total-$" + purchaseTotal;
		
		String lineToWriteToFile = "purchase; " +
									theCustomer.getID() + "; " +
									catName + "; " +
									theProduct.getID() + "; " +
									numItems + "; " +
									dateAsString;
									
		techStore.writeFile(lineToWriteToFile);
		txtArTrans.append(purchaseAddedToOutputArea);
		
		
		//clearing inputs for next purchase
		cboCategory.setSelectedIndex(0);
		cboProduct.setSelectedIndex(0);
		cboCustomer.setSelectedIndex(0);
		txtAmount.setText("");
		txtDate.setText("MM/dd/yyyy");
		
		numPurch ++;
		if(numPurch >= maxPurch) {
			JOptionPane.showMessageDialog(null, "You have reached your max of " + maxPurch + " purchases. Closing program");
			close();
		}
		
	}//end buyButton_action
	
	public void cboCustomer_ActionPerformed(ActionEvent e) {
		//change txtArTrans
		if(cboCustomer.getSelectedItem()==null || cboCustomer.getSelectedItem().toString().trim().equals("")) {//nothing selected
			return;
		}
		
		Customer cust=techStore.getCustomer(cboCustomer.getSelectedItem().toString());
		
		Purchase[] purchHist = cust.getPurchases();
		
		
		String purchHistStr="\n";
		for(int i=0; i<purchHist.length; i++) {
			if(purchHist[i]==null) {//found end of useful part of array
				break;
			}
			purchHistStr += purchHist[i].toString() + "\n";
		}
		txtArTrans.setText("CUSTOMER'S PURCHASE HISTORY:" + purchHistStr);
	}
	
	public void cboCategory_ActionPerformed(ActionEvent e) {
		//update cboProduct
		if(cboCategory.getSelectedItem() ==null || cboCategory.getSelectedItem().toString().trim().equals("")) {
			return;
		}
		Category theCat = techStore.getCategory(cboCategory.getSelectedItem().toString());
		Product[] products = theCat.getProducts();
		cboProduct.removeAllItems();
		cboProduct.addItem(" ");
		for(int i=0; i<products.length; i++) {
			if(products[i]==null) {
				break;
			}
			else {
				cboProduct.addItem(products[i].getName());
			}
		}
		
		
	}
	
	public void cboProduct_ActionPerformed(ActionEvent e) {
		//update txtArProduct
		if(cboProduct.getSelectedItem() == null || cboProduct.getSelectedItem().toString().trim().equals("")) {
			return;
		}
		
		else {
			Product theProduct = techStore.getProduct(cboCategory.getSelectedItem().toString(),
					cboProduct.getSelectedItem().toString());
			txtArProduct.setText("PRODUCT INFO: "+theProduct.toString());
		}
		
	}
	
	public void badPurchaseTrx() {
		JOptionPane.showMessageDialog(null, "ERROR inputting that transaction. Try again", "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// placeholder, not actually using this method
	}
	
	

}