package cu.cs.cpsc215.project3;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SystemInformationDlg extends JDialog{
	private static final long serialVersionUID = 3940298768854868761L;

	public SystemInformationDlg(Dialog d, boolean m){
		super(d, "System Info", m);		
		JPanel mainJPanel = new JPanel(new BorderLayout());
		String nl = System.getProperty("line.separator");
		String systemInfo = "Simple Mail: Project 3" + nl + nl + 
				"This is a very simple mail client, that was turned in as a project 12/3/14" + nl 
				+ nl + "If you have any questions, feel free to email me at: cfitt@g.clemson.edu";
		
		JTextArea areaInfo = new JTextArea(systemInfo);
		areaInfo.setBackground(new Color(240, 240 ,255));
		mainJPanel.add(areaInfo);
		this.getContentPane().add(mainJPanel);
		this.setSize(600, 300);
	}
}