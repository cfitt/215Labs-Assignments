package lab12;
import javax.swing.*;

@SuppressWarnings("serial")
public class ContactTableFrame extends JFrame {

	public ContactTableFrame(String caption, AbstractTableOption contacts) { 
		// invoke superclass constructor to set window's caption
		super(caption);
		// set the size of the window
		this.setSize(750,100);
		// construct a new JTable instance
		JTable table = new JTable(contacts);
		// JTable instance to the JFrame's content pane
		this.getContentPane().add(table);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		table.getColumnModel().getColumn(3).setCellRenderer (new ColorTableCellRenderer(contacts));
	}
}
