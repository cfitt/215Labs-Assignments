package cu.cs.cpsc215.project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = -6501223548904024654L;

	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;
	private JMenuItem menuItemExit;
	private JMenuItem menuItemConfigure;
	private JMenuItem menuItemAbout;
	private JDialog addJD;
	private JDialog editJD;
	private JDialog configJD;
	private JDialog aboutJD;
	private Dialog d;

	private final DataStore dataStoreInst;

	private JTable contactTable;
	private ContactTableModel contactTableModel;

	public MainFrame(String caption){
		super(caption);
		dataStoreInst = DataStore.instantiate();
		instantiateFrame();//Build
	}

	private void instantiateFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(750, 500);

		JPanel mainWindow = new JPanel();
		mainWindow.setLayout(new BorderLayout());
		
		ImageIcon image = new ImageIcon("mail.png");
		this.setIconImage(image.getImage());
		
		JMenuBar menuTop = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		JMenu menuConfig = new JMenu("Configuration");
		JMenu menuHelp = new JMenu("Help");
		menuItemExit = new JMenuItem("Exit");
		menuItemConfigure = new JMenuItem("Configure");
		menuItemAbout = new JMenuItem("About");
		menuItemExit.addActionListener(this);
		menuItemConfigure.addActionListener(this);
		menuItemAbout.addActionListener(this);

		//Add menu items
		menuFile.add(menuItemExit);
		menuConfig.add(menuItemConfigure);
		menuHelp.add(menuItemAbout);
		menuTop.add(menuFile);
		menuTop.add(menuConfig);
		menuTop.add(menuHelp);
		mainWindow.add(menuTop, "North");

		String[] colNames = {"First Name", "Last Name", "Postal Address", "Phone Number", "Email Address"};
		Object[][] contactInfo = new Object[dataStoreInst.getContacts().size()][5];
		for(int i = 0; i < dataStoreInst.getContacts().size(); i++){
			for(int j = 0; j <= 4; j++){
				switch(j) {
				case 0: contactInfo[i][0] = dataStoreInst.getContacts().get(i).getFirstName();
				case 1: contactInfo[i][1] = dataStoreInst.getContacts().get(i).getLastName();
				case 2: contactInfo[i][2] = dataStoreInst.getContacts().get(i).getPostalAddress();
				case 3: contactInfo[i][3] = dataStoreInst.getContacts().get(i).getPhoneNumber();
				case 4: contactInfo[i][4] = dataStoreInst.getContacts().get(i).getEmailAddress();
				}
			}
		}
		
		contactTableModel = new ContactTableModel(contactInfo, colNames);
		contactTable = new JTable(contactTableModel);
		ContactTableMouseListener listenToMyMouse = new ContactTableMouseListener();
		contactTable.addMouseListener(listenToMyMouse);
		JScrollPane contactScrollPane = new JScrollPane(contactTable);

		mainWindow.add(contactScrollPane, "Center");

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		addButton = new JButton("Add");
		editButton = new JButton("Edit");
		deleteButton = new JButton("Delete");
		addButton.addActionListener(this);
		editButton.addActionListener(this);
		deleteButton.addActionListener(this);
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(deleteButton);
		mainWindow.add(buttonPanel, "South");

		this.getContentPane().add(mainWindow);
		JFrame dialogFrame = new JFrame();
		d = new Dialog(dialogFrame);
	}

	public void actionPerformed(ActionEvent actor){
		//"Exit" on top menu in File: Shuts down the program
		if(actor.getSource() == menuItemExit){
			System.exit(0);
		}
		//"Configure" on top menu in Configuration: Open a configuration dialog
		else if(actor.getSource() == menuItemConfigure){
			configJD = new ConfigurationDlg(d, true);
			configJD.setVisible(true);
		}
		//About on top menu in Help: Open a system information dialog
		else if(actor.getSource() == menuItemAbout){
			aboutJD = new SystemInformationDlg(d, true);
			aboutJD.setVisible(true);
		}
		//Add on bottom menu: Open add contact dialog
		else if(actor.getSource() == addButton){
			addJD = new ContactEditingDlg(d, "Add", true, contactTableModel);
			addJD.setVisible(true);
			contactTableModel.fireTableDataChanged();
		}
		//Edit on bottom menu: Open an edit contact dialog
		else if(actor.getSource() == editButton){
			if(contactTable.getSelectedRow() != - 1){
				editJD = new ContactEditingDlg(d, "Edit", true, contactTableModel, contactTable.getSelectedRow());
				editJD.setVisible(true);
				contactTableModel.fireTableDataChanged();
			}
		}
		//Delete on bottom menu: selected row is deleted 
		else if(actor.getSource() == deleteButton){
			if(contactTable.getSelectedRow() != - 1){
				JDialog deleteD = new JDialog();
				int deleteOption = JOptionPane.showOptionDialog(deleteD, "Are you sure you want to delete the contact " + 
						dataStoreInst.getContacts().get(contactTable.getSelectedRow()).getFirstName() + " " + 
						dataStoreInst.getContacts().get(contactTable.getSelectedRow()).getLastName() + "?",
						"Delete Contact", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if(deleteOption == 0){
					dataStoreInst.getContacts().remove(contactTable.getSelectedRow());
					contactTableModel.removeRow(contactTable.getSelectedRow());
				}
				deleteD.setVisible(false);
			}
		}
	}
}