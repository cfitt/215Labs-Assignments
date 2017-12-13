package cu.cs.cpsc215.project3;

import java.awt.Dialog;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;

public class ContactTableMouseListener implements MouseListener{
	private Dialog d;
	private DataStore dataStoreInst;

	public ContactTableMouseListener(){
		super();
		JFrame dFrame = new JFrame();
		d = new Dialog(dFrame);
		dataStoreInst = DataStore.instantiate();
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			JTable target = (JTable)e.getSource();
	        int row = target.getSelectedRow();
			JDialog emaiID = new EmailTransmissionDlg(
					d, true, dataStoreInst.getContacts().get(row).getEmailAddress()
			);
			emaiID.setVisible(true);
	    }
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}

}
