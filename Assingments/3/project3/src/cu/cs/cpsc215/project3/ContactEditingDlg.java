package cu.cs.cpsc215.project3;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ContactEditingDlg extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1709072772869838000L;
	private DataStore dataStoreInst = DataStore.instantiate();
	private ContactTableModel tableModel;
	private int editRow;
	
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField postalAddressField;
	private JTextField phoneNumberField;
	private JTextField emailAddressField;
	
	private JButton saveButton;
	private JButton cancelButton;
	
	public ContactEditingDlg(Dialog d, String title, boolean modal, ContactTableModel contactTableModel){
		super(d, title + " Contact", modal);
		
		//All fields initially blank
		firstNameField = new JTextField();
		lastNameField = new JTextField();
		postalAddressField = new JTextField();
		phoneNumberField = new JTextField();
		emailAddressField = new JTextField();
		editRow = -1;
		tableModel = contactTableModel;
		editBuild();	//Build
	}

	//Constructor for editing a contact
	public ContactEditingDlg(Dialog owner, String title, boolean modal, ContactTableModel contactTableModel, int row){
		//Call the superclass constructor
		super(owner, title + " Contact", modal);
	
		//Initialize all fields to the information already stored in this contact
		firstNameField = new JTextField(dataStoreInst.getContacts().get(row).getFirstName());
		lastNameField = new JTextField(dataStoreInst.getContacts().get(row).getLastName());
		postalAddressField = new JTextField(dataStoreInst.getContacts().get(row).getPostalAddress());
		phoneNumberField = new JTextField(dataStoreInst.getContacts().get(row).getPhoneNumber());
		emailAddressField = new JTextField(dataStoreInst.getContacts().get(row).getEmailAddress());
		editRow = row;
		tableModel = contactTableModel;
		editBuild();
	}
	
	public void editBuild(){
		JPanel mainPanel = new JPanel(new BorderLayout());
		JFrame contactFrame = new JFrame();
		contactFrame.getContentPane().setLayout(
				new BoxLayout(contactFrame.getContentPane(), BoxLayout.Y_AXIS)
		);
		
		JLabel firstNameLabel = new JLabel("First Name: ");
		JLabel lastNameLabel = new JLabel("Last Name: ");
		JLabel postalAddressLabel = new JLabel("Postal Address: ");
		JLabel phoneNumberLabel = new JLabel("Phone Number: ");
		JLabel emailAddressLabel = new JLabel("Email Address: ");
		
		//Panel Positionings
		JPanel firstName = new JPanel(new BorderLayout());
		firstName.add(firstNameLabel, "West");
		firstName.add(firstNameField, "Center");
		JPanel lastName = new JPanel(new BorderLayout());
		lastName.add(lastNameLabel, "West");
		lastName.add(lastNameField, "Center");
		JPanel postalAddress = new JPanel(new BorderLayout());
		postalAddress.add(postalAddressLabel, "West");
		postalAddress.add(postalAddressField, "Center");
		JPanel phoneNumber = new JPanel(new BorderLayout());
		phoneNumber.add(phoneNumberLabel, "West");
		phoneNumber.add(phoneNumberField, "Center");
		JPanel emailAddress = new JPanel(new BorderLayout());
		emailAddress.add(emailAddressLabel, "West");
		emailAddress.add(emailAddressField, "Center");
		contactFrame.getContentPane().add(firstName);
		contactFrame.getContentPane().add(lastName);
		contactFrame.getContentPane().add(postalAddress);
		contactFrame.getContentPane().add(phoneNumber);
		contactFrame.getContentPane().add(emailAddress);
		mainPanel.add(contactFrame.getContentPane());
		
		//Buttons and Action Listener
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		saveButton = new JButton("Save");
		cancelButton = new JButton("Cancel");
		saveButton.addActionListener(this);
		cancelButton.addActionListener(this);
		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);
		mainPanel.add(buttonPanel, "South");
		this.getContentPane().add(mainPanel);
		this.setSize(350, 200);
	}

	public void actionPerformed(ActionEvent e) {
		//Save and make sure fields are entered
		if(e.getSource() == saveButton){
			if(firstNameField.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Please enter a first name.");
				return;
			}
			if(lastNameField.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Please enter a last name.");
				return;
			}
			if(postalAddressField.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Please enter a postal address.");
				return;
			}
			if(!isValidPhoneNumber(phoneNumberField.getText())){
				JOptionPane.showMessageDialog(null, "Invalid phone number. Format: XXX-XXX-XXXX");
				return;
			}
			if(!isValidEmailAddress(emailAddressField.getText())){
				JOptionPane.showMessageDialog(null, "Invalid email address.");
				return;
			}
			//Add a new contact
			if(editRow == -1){
				Contact<String> newContact = new Contact<String>(firstNameField.getText(), lastNameField.getText(), 
						postalAddressField.getText(), phoneNumberField.getText(), emailAddressField.getText());
				dataStoreInst.getContacts().add(newContact);
				
				String[] newRow = {firstNameField.getText(), lastNameField.getText(), 
						postalAddressField.getText(), phoneNumberField.getText(), emailAddressField.getText()};
				tableModel.addRow(newRow);
			}
			//Edit an already existing contact
			else{
				Contact<String> editContact = dataStoreInst.getContacts().get(editRow);
				editContact.setFirstName(firstNameField.getText());
				editContact.setLastName(lastNameField.getText());
				editContact.setPostalAddress(postalAddressField.getText());
				editContact.setPhoneNumber(phoneNumberField.getText());
				editContact.setEmailAddress(emailAddressField.getText());
				
				tableModel.setValueAt(firstNameField.getText(), editRow, 0);
				tableModel.setValueAt(lastNameField.getText(), editRow, 1);
				tableModel.setValueAt(postalAddressField.getText(), editRow, 2);
				tableModel.setValueAt(phoneNumberField.getText(), editRow, 3);
				tableModel.setValueAt(emailAddressField.getText(), editRow, 4);
			}
		}
		this.setVisible(false); //Close Window
	}
	
	//Validate an email address
	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
		   InternetAddress emailAddress = new InternetAddress(email);
		   emailAddress.validate();
		} catch (AddressException ex) {
		   result = false;
		}
		return result;
	}
	
	public static boolean isValidPhoneNumber(String phone) {
	    if(Pattern.matches("\\d\\d\\d\\p{Punct}\\d\\d\\d\\p{Punct}\\d\\d\\d\\d", phone))
	    	return true;
	    return false;
	}
}