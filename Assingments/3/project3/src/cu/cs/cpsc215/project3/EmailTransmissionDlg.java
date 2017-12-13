package cu.cs.cpsc215.project3;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EmailTransmissionDlg extends JDialog implements ActionListener{
	private static final long serialVersionUID = 4104111623221324151L;
	
	private DataStore dataStoreInst;
	
	private JTextField senderField;
	private JTextField outField;
	private JTextField subjectField;
	private JTextArea bodyField;
	private JButton sendButton;
	private JButton cancelButton;

	public EmailTransmissionDlg(Dialog d, boolean modal, String out){
		super(d, "Send Email", modal);
		dataStoreInst = DataStore.instantiate();
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JFrame emailFrame = new JFrame();
		emailFrame.getContentPane().setLayout(
				new BoxLayout(emailFrame.getContentPane(), BoxLayout.Y_AXIS)
		);
		
		senderField = new JTextField(dataStoreInst.getConfig().getEmailAddress());
		senderField.setEditable(false);
		outField = new JTextField(out);
		subjectField = new JTextField();
		bodyField = new JTextArea(25, 50);
		bodyField.setLineWrap(true);
		JScrollPane bodyPane = new JScrollPane(
				bodyField, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
		);
		
		JLabel senderLabel = new JLabel("Sender: ");
		JLabel destinationLabel = new JLabel("Recepients: ");
		JLabel subjectLabel = new JLabel("Subject: ");
		JLabel bodyLabel = new JLabel("Body: ");
		
		//Panel positioning
		JPanel sender = new JPanel(new BorderLayout());
		sender.add(senderLabel, "West");
		sender.add(senderField, "Center");
		JPanel destination = new JPanel(new BorderLayout());
		destination.add(destinationLabel, "West");
		destination.add(outField, "Center");
		JPanel subject = new JPanel(new BorderLayout());
		subject.add(subjectLabel, "West");
		subject.add(subjectField, "Center");
		JPanel body = new JPanel(new BorderLayout());
		body.add(bodyLabel, "West");
		body.add(bodyPane, "Center");
		emailFrame.getContentPane().add(sender);
		emailFrame.getContentPane().add(destination);
		emailFrame.getContentPane().add(subject);
		emailFrame.getContentPane().add(body);
		mainPanel.add(emailFrame.getContentPane());
		
		//Buttons!
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		sendButton = new JButton("Send");
		cancelButton = new JButton("Cancel");
		sendButton.addActionListener(this);
		cancelButton.addActionListener(this);
		buttonPanel.add(sendButton);
		buttonPanel.add(cancelButton);
		mainPanel.add(buttonPanel, "South");
		this.getContentPane().add(mainPanel);
		this.setSize(700, 440);
	}

	public void actionPerformed(ActionEvent e) {
		//Send: send an email
		if(e.getSource() == sendButton){
			if(outField.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Please enter a recepient of email.");
				return;
			}
			if(subjectField.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Please enter an email subject... It can even be subject");
				return;
			}
			if(bodyField.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Please enter an email text or info");
				return;
			}
			
			try{
				int SMTP_PORT = 465; //Correct finally
				Properties props = new Properties();
				props.put("mail.transport.protocol", "smtp");
		        props.put("mail.smtp.host", dataStoreInst.getConfig().getIPAddress());
		        props.put("mail.smtp.port", SMTP_PORT);
		        props.put("mail.smtp.socketFactory.port", SMTP_PORT);
		        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		        props.put("mail.smtp.auth", "true");
		        
		        Authenticator authy = new Authenticator () {
					public PasswordAuthentication getPasswordAuthentication(){
						return new PasswordAuthentication(senderField.getText(), dataStoreInst.getConfig().getPassword());
					}
				};
			
				Session session = Session.getDefaultInstance(props, authy);
				
				ArrayList<String> outList = new ArrayList<String>();
				String outString = outField.getText();
				
				//Extract each email address from the destination field
				int i = 0;
				while(outString.contains(",")){
					outList.add(outString.substring(0, outString.indexOf(",")));
					outString = outString.substring(outString.indexOf(",")+2,outString.length());
					i++;
				}
				outList.add(outString);
				
				for(int j = 0; j <= i; j++){
					if(isValidEmailAddress(outList.get(j))){
						Message msg = new MimeMessage(session);
						msg.setFrom(new InternetAddress(senderField.getText()));
						msg.setRecipient(RecipientType.TO, new InternetAddress(outList.get(j)));
						msg.setSubject(subjectField.getText());
						msg.setText(bodyField.getText());
						Transport.send(msg);
					}
					else
						JOptionPane.showMessageDialog(null, 
								"Email could not send to " + outList.get(i) 
								+ ". Email address might be invalid.");
				}
				this.setVisible(false);
			}
			catch(MessagingException i){
				System.out.println("Failed to send.");
			}
		}
		//Cancel: close the window
		else if(e.getSource() == cancelButton)
			this.setVisible(false);
	}
	//Validate an email address
	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
		   InternetAddress emailAddress = new InternetAddress(email);
		   emailAddress.validate();
		} catch (AddressException ae) {
		   result = false;
		}
		return result;
	}
}