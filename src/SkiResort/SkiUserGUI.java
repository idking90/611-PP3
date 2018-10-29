package SkiResort;
//***********************************************************************
//*																		*
//* CIS611					Fall 2018									*
//*																		*
//*						Program Assignment PA06							*
//*																		*
//*						ski resort system, GUI							*
//*																		*
//*						Created 22 Oct 2018								*
//*																		*
//*						Saved in SkiUserGUI.java						*
//*																		*
//***********************************************************************

//add the class template

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

class SkiUserGUI extends JFrame implements ActionListener{

	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblDays;
	private JTextField txtDays;
	private JLabel lblMbrID ;
	private JTextField txtMbrID;
	private JLabel lblNumItems;
	private JTextField txtNumItems;
	private JCheckBox chkFirstTime;
	private JCheckBox chkCoupon;
	private JButton btnCreate ;
	private JButton btnSort;
	private JButton btnSave;
	private JButton btnClose ;
	private JTextArea txtAreaOutput ;
	private JScrollPane scrollPane;
	private JLabel lblNumSkiers;
	private int numSkiers = 0;
	  
	  private SkiResort ski;

// declare UI component objects
	
public SkiUserGUI() {
	int count = 0;
	int maxTries = 3;
	int n;
	// this loop will just retry letting the user create a ski resort up to 3 times before closing the program
	while(true) {
		try {
			n = Integer.parseInt(JOptionPane.showInputDialog("Input the number of Ski customers"));
			break;
		}
		catch(NumberFormatException ex) {
		JOptionPane.showMessageDialog(null, "ERROR: It must be a whole number of skiers to create", "ERROR", JOptionPane.ERROR_MESSAGE);
			if (++count == maxTries) {
			inputException(ex);
			}
		}
	}


	
	ski = new SkiResort(n);
	
	
	
	// call these two methods to create user GUI
	initComponent();
	doTheLayout();
	
	//add event listeners
    btnClose.addActionListener( new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e){
            closeButtonClicked();
            }
    });
    
    btnCreate.addActionListener( new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e){
            createButtonClicked();
            }
    });
    
    btnSave.addActionListener( new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e){
            saveButtonClicked();
            }
    });
    
    btnSort.addActionListener( new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e){
            sortButtonClicked();
            }
    });
    
    
	
}
private void inputException(NumberFormatException ex) {
	JOptionPane.showMessageDialog(null, "Sorry, you've exceeded the max number of attempts. Closing program.", "ERROR", JOptionPane.ERROR_MESSAGE);
	System.exit(-1);
}
private void badGUIInput() {
	JOptionPane.showMessageDialog(null, "Sorry. Incorrect input for that customer! \n"
										+ "-Customer name is required \n"
										+ "-Length of stay must be a whole number greater than 0 \n"
										+ "-Items rented must be a whole number between 0 and 3 \n" 
										+ "-You may only create as many skiers as you entered in the first window", "ERROR", JOptionPane.ERROR_MESSAGE);
	System.exit(-1);
}

private void initComponent(){
	// Initialize the GUI components
	lblNumSkiers = new JLabel("You have created " + numSkiers + "skiers");
	lblName = new JLabel("Customer name: ");
	txtName = new JTextField("Type name here");
	lblDays = new JLabel("Length of stay: ");
	txtDays = new JTextField("Enter number of days of stay in whole numbers");
	lblMbrID = new JLabel("Member ID: ");
	txtMbrID = new JTextField("Type ID here or leave blank for a non-member");
	lblNumItems = new JLabel("Number of items: ");
	txtNumItems = new JTextField("Type how many items rented, from 0 to 3");
	chkFirstTime = new JCheckBox("First Time User? ");
	chkCoupon = new JCheckBox("Coupon? ");
	btnCreate = new JButton("Create");
	btnSort = new JButton("Sort Customers");
	btnSave = new JButton("Save");
	btnClose = new JButton("Close");
	txtAreaOutput = new JTextArea(50, 100);
	scrollPane = new JScrollPane(txtAreaOutput, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	txtAreaOutput.setEditable(false);
	txtAreaOutput.setLineWrap(true);
	txtAreaOutput.setWrapStyleWord(true);
	scrollPane.setAutoscrolls(true);
	
	txtAreaOutput.append("Output text will go here");
	

	
	
}

private void doTheLayout(){
	// Arrange the UI components into GUI window
	JPanel top = new JPanel();
	JPanel center = new JPanel();
    JPanel bottom = new JPanel();

    top.setLayout(new GridLayout(7, 2, 0, 2));
    top.add(lblName);
    top.add(txtName);
    top.add(lblDays);
    top.add(txtDays);
    top.add(lblMbrID);
    top.add(txtMbrID);
    top.add(lblNumItems);
    top.add(txtNumItems);
    top.add(chkFirstTime);
    top.add(chkCoupon);
    top.add(lblNumSkiers);
    
    center.setLayout(new FlowLayout());
    center.add(txtAreaOutput);
    
    bottom.setLayout(new GridLayout(1, 4, 10, 2));
    bottom.add(btnCreate);
    bottom.add(btnSort);
    bottom.add(btnSave);
    bottom.add(btnClose);
    
    this.add(top, "North");
    this.add(center, "Center");
    this.add(bottom, "South");
    
    
    
    
}	
	
 
@Override
public void actionPerformed(ActionEvent e) {
	// calls one of these methods to respond to the user action:
	// createButtonClicked(),
	// sortButtonClicked(),
	// saveButtonClicked(), or 
	// closeButtonClicked()
	
}

private void createButtonClicked() {
	  String name = "";
	  int days = -1;
	  String mbrID = "";
	  boolean isMember = true;
	  int numItems = -1;
	  boolean isFirstTime = false;
	  boolean isCoupon = false;
	  SkiCustomer theCustomer;
	  
	  // validates user inputs first
	  try {
		  name = txtName.getText();
		  days = Integer.parseInt(txtDays.getText());	
		  numItems = Integer.parseInt(txtNumItems.getText());
		  isFirstTime = chkFirstTime.isSelected();
		  isCoupon = chkCoupon.isSelected();
	  }
	  catch (Exception ex){
		  badGUIInput();
	  }
	  //determines whether a member to set that variable
	  if(txtMbrID.getText().isEmpty() || txtMbrID.getText() == null) {
		  isMember = false;
	  }
	  else {
		  mbrID = txtMbrID.getText();
	  }
	  //validation of available items to rent
	  if (numItems < 0 || numItems > 3) {
		  badGUIInput();
	  }
	  //validation that it's not a 0 or negative length of stay
	  if (days < 1) {
		  badGUIInput();
	  }
	  // after validation, uses the ski reference variable to call method addCustomer() with parameters
	  if(isMember) {//call method for adding member
		  try {
			  theCustomer = ski.addCustomer(name, days, numItems, isFirstTime, isCoupon, mbrID);
			  txtAreaOutput.append("\n CUSTOMER CREATED: " + theCustomer.toString());
		  }
		  catch(Exception ex) {
			  badGUIInput();
		  }
	  }
	  else {//not a member, call other method
		  try {
			  theCustomer = ski.addCustomer(name, days, numItems, isFirstTime, isCoupon);
			  txtAreaOutput.append("\n CUSTOMER CREATED: " + theCustomer.toString());
		  }
		  catch(Exception ex) {
			  badGUIInput();
		  }
	  }
	  
	  
	  //clearing inputs for next customer
	  txtName.setText("");
	  txtDays.setText("");
	  txtMbrID.setText("");
	  txtNumItems.setText("");
	  chkFirstTime.setSelected(false);
	  chkCoupon.setSelected(false);
	  numSkiers ++;
	  if (numSkiers == ski.customers.length) {
		  lblNumSkiers.setText("You've made all " + ski.customers.length + " skiers. Don't make any more");
	  }
	  else {
		  lblNumSkiers.setText("You have created " + numSkiers + " skier(s) of " + ski.customers.length);
	  }
	
  }

private void sortButtonClicked(){
	  
	  // uses the ski reference variable to call method sortCustomer()
	txtAreaOutput.append(ski.sortCustomer());
	  // also calls display() to display the entire customers data in the text area 
	txtAreaOutput.append(ski.display());
	
  }


private void saveButtonClicked(){
	
	// uses the ski reference variable to call method writeToFile() to save data to file
	
	txtAreaOutput.append(ski.writeToFile());
  }


private  void closeButtonClicked(){
	  
	// handle close button clicked event, gracefully closes the program
	System.exit(0);
    
  }
  
  /**Main method*/
  public static void main(String[] args) {
	  
	  // create the user GUI
	  SkiUserGUI frame = new SkiUserGUI();
	  frame.setTitle("PA06 Ski Resort GUI");
	  frame.pack();
	  frame.setLocationRelativeTo(null);
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  frame.setVisible(true);
  }
}