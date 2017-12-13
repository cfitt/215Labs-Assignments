package lab12;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

@SuppressWarnings("serial")
public class ColorTableCellRenderer extends DefaultTableCellRenderer {
	private AbstractTableOption basemodel;
	public ColorTableCellRenderer(AbstractTableOption model) {
		basemodel = model;
	}
	public Component getTableCellRendererComponent (JTable table, Object value, 
			boolean isSelected, boolean hasFocus, int row, int column) {
		Component component = super.getTableCellRendererComponent (table, value, 
				isSelected, hasFocus, row, column);
		component.setForeground(basemodel.getPerson(row).getColor());
		component.setBackground(basemodel.getPerson(row).getColor());
		return (component);
	}	
}

