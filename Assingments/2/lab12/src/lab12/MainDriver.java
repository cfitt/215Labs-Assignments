package lab12;

import java.awt.Color;
import javax.swing.JFrame;

public class MainDriver {
	public static void main(String[] args) {
		AbstractTableOption model = new AbstractTableOption();
		model.addPerson(new Person ("Strong", "Bad", "strongbad@g.clemson.edu", Color.blue));
		model.addPerson(new Person ("The", "Paper", "dahPaper@gmail.com", Color.yellow));
		model.addPerson(new Person ("Homestar", "Runner", "HomestarRunner@homestar.com", Color.green));
		model.addPerson(new Person ("Sir", "Poopsmith", "PoopinSmithin@poop.net", Color.orange));
		JFrame frame = new ContactTableFrame("Contact List", model);
		frame.setVisible(true);
	}
}
