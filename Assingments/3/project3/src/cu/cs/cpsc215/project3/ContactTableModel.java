package cu.cs.cpsc215.project3;

import javax.swing.table.DefaultTableModel;

public class ContactTableModel extends DefaultTableModel{
	private static final long serialVersionUID = 5987349725487244708L;
	public ContactTableModel(Object[][] table, String[] columnNames){
		super(table, columnNames);
	}
	
	public boolean isCellEditable(int row, int column) {
	    return false;
	}
}