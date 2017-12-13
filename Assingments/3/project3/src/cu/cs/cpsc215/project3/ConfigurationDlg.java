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

public class ConfigurationDlg extends JDialog implements ActionListener{
	private static final long serialVersionUID = 6836976399369738081L;
	
	private DataStore dataStoreInst;
	
	private JTextField emailField;
	private JTextField ipField;
	private JTextField passField;
	
	private JButton saveButton;
	private JButton cancelButton;
	

	public ConfigurationDlg(Dialog owner, boolean modal){
		super(owner, "Configuration Settings", modal);
		
		dataStoreInst = DataStore.instantiate();
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JFrame configFrame = new JFrame();
		configFrame.getContentPane().setLayout(new BoxLayout(configFrame.getContentPane(), BoxLayout.Y_AXIS));
		
		emailField = new JTextField(dataStoreInst.getConfig().getEmailAddress());
		ipField = new JTextField(dataStoreInst.getConfig().getIPAddress());
		passField = new JTextField(dataStoreInst.getConfig().getPassword());
		
		JLabel emailLabel = new JLabel(" Email Address: ");
		JLabel IPLabel = new JLabel(" SMTP Server: ");
		JLabel passwordLabel = new JLabel(" Password: ");
		

		JPanel email = new JPanel(new BorderLayout());
		email.add(emailLabel, "West");
		email.add(emailField, "Center");
		JPanel IP = new JPanel(new BorderLayout());
		IP.add(IPLabel, "West");
		IP.add(ipField, "Center");
		JPanel password = new JPanel(new BorderLayout());
		password.add(passwordLabel, "West");
		password.add(passField, "Center");
		configFrame.getContentPane().add(email);
		configFrame.getContentPane().add(IP);
		configFrame.getContentPane().add(password);
		mainPanel.add(configFrame.getContentPane());
		
		//Bottom Menu Buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		saveButton = new JButton("Save");
		cancelButton = new JButton("Cancel");
		saveButton.addActionListener(this);
		cancelButton.addActionListener(this);
		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);
		
		mainPanel.add(buttonPanel, "South");
		
		//Add main panel to the configuration dialog
		this.getContentPane().add(mainPanel);
		this.setSize(350, 150);
		
	}

	//Instructions for actions after button clicks
	public void actionPerformed(ActionEvent e) {
		//Save: save the information
		if(e.getSource() == saveButton){
			if(!validateEmail(emailField.getText())){ //Validate
				JOptionPane.showMessageDialog(null, "Invalid email address.");
				return;
			}
			if(!validateSMTPAddress(ipField.getText())){
				JOptionPane.showMessageDialog(null, "Invalid SMTP server.");
				return;
			}
			
			dataStoreInst.getConfig().setEmailAddress(emailField.getText());
			dataStoreInst.getConfig().setIPAddress(ipField.getText());
			dataStoreInst.getConfig().setPassword(passField.getText());
			this.setVisible(false);
		}
		//Cancel: close the window
		else if (e.getSource() == cancelButton)
			this.setVisible(false);
	}
	
	
	//Validation methods. Check the Email Address & SMPT validity.
	public static boolean validateEmail(String email) {
		boolean validate = true;
		try {
		   InternetAddress emailAddress = new InternetAddress(email);
		   emailAddress.validate();
		} catch (AddressException ae) {
		   validate = false;
		}
		return validate;
	}
	
	public static boolean validateSMTPAddress(String smtp) {
	    if(Pattern.matches("\\w+\\p{Punct}\\w+\\p{Punct}\\w+", smtp))
	    	return true;
	    return false;
	}
}