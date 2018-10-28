

import java.awt.*;
import java.awt.event.*;


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
	 
	 
	  private TechStore techStore;
	  private String fileName = "data.txt";
	  
	  public UserGUI() {
		  
		 techStore = new TechStore(fileName);

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
		 
		 String[] productTypes = {"", "Laptops", "Tablets", "Cell Phones"};
		 cboCategory = new JComboBox(productTypes); //might want to populate blank and let read-in of file actually initiate the list
		 cboCustomer = new JComboBox();
		 cboProduct = new JComboBox(); 
		 
		 txtAmount = new JTextField("", 15);
		 txtAmount.setToolTipText("Enter whole number of items purchased");
		 txtDate = new JTextField("mm/dd/yyyy", 15);
		 txtDate.setToolTipText("Enter date of purchase as MM/DD/YYYY");
		 
		 txtArProduct = new JTextArea(5, 15);
		 txtArProduct.setEditable(false);
		 txtArProduct.setLineWrap(true);
		 txtArProduct.setWrapStyleWord(true);
		 txtArProduct.append("Product Info...");
		 scrollProduct = new JScrollPane(txtArProduct);
		 txtArTrans = new JTextArea(10, 50);
		 txtArTrans.setEditable(false);
		 txtArTrans.setLineWrap(true);
		 txtArTrans.setWrapStyleWord(true);
		 txtArTrans.append("Customer Info...");
		 scrollTrans = new JScrollPane(txtArTrans);

		 btnBuy = new JButton("Buy");
		 btnExit = new JButton("Exit");
		 

	  }// end of creating objects method

	  private void doTheLayout(){

	      JPanel top = new JPanel();
	      JPanel prodTA = new JPanel();
	      JPanel buy = new JPanel();
	      JPanel buyBtnPan = new JPanel();
	      JPanel bottom = new JPanel();
	      JPanel exitPanel = new JPanel();
	      

	      
	      top.setLayout( new GridLayout(3, 2));
	      top.add(lblCategory);
	      top.add(cboCategory);
	      top.add(lblProduct);
	      top.add(cboProduct);
	      top.add(lblInfo);
	      top.add(new JLabel(""));
	      
	      prodTA.setLayout( new BorderLayout());
	      prodTA.add(txtArProduct, "North");
	      
	      buy.setLayout(new GridLayout(4,2));
	      buy.add(lblCustomer);
	      buy.add(cboCustomer);
	      buy.add(lblAmount);
	      buy.add(txtAmount);
	      buy.add(lblDate);
	      buy.add(txtDate);
	      
	      buyBtnPan.setLayout(new FlowLayout(FlowLayout.CENTER));
	      buyBtnPan.add(btnBuy);

	      bottom.setLayout( new GridLayout(2, 1));
	      bottom.add(lblTrans);
	      bottom.add(txtArTrans);
	      
	      
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
	  }// end of transfer action event method


	public static void main(String[] args) {
	   JFrame f = new JFrame("Tech Store");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = f.getContentPane();
        contentPane.add( new UserGUI());
        f.pack();
        f.setSize(500, 500);

        f.setVisible(true);

	}

	public void buyButton_ActionPerformed(ActionEvent e) {
		//buyProduct validation, action, etc....
		txtArTrans.append("purchase made"); //**for testing********
	}
	
	public void cboCustomer_ActionPerformed(ActionEvent e) {
		//change txtArTrans
		txtArTrans.append("cboCustomer changed"); //testing********
	}
	
	public void cboCategory_ActionPerformed(ActionEvent e) {
		//update cboProduct
		txtArProduct.append("cboCat changed"); //this is for testing**********
	}
	
	public void cboProduct_ActionPerformed(ActionEvent e) {
		//update txtArProduct
		txtArProduct.append("cboProduct changed"); //this is for testing***********
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// placeholder, not actually using this method
	}
	
	

}
