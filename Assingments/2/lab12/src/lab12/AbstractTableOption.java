package lab12;

import javax.swing.table.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class AbstractTableOption extends AbstractTableModel {

			private ArrayList<Person> list = new ArrayList<Person>();
			private int columnCount = 4;

			public Object getValueAt(int row, int column) {
				if (column == 0)
					return list.get(row).getFirst();
				if (column == 1)
					return list.get(row).getLast();
				if (column == 2)
					return list.get(row).getEmail();
				else
					return list.get(row).getColor();
			}
			
			public void addPerson (Person dis_person) { list.add(dis_person); }
			
			public Person getPerson(int row) { return list.get(row); }
			
			public int getColumnCount() { return columnCount; }

			public int getRowCount() { return list.size(); } 
}
