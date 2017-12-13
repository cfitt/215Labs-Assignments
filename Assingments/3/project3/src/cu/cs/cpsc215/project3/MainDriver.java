package cu.cs.cpsc215.project3;

import java.io.IOException;

public class MainDriver {
	public static void main(String[] args) {
		final DataStore dataStoreInst = DataStore.instantiate();
		try{
			dataStoreInst.loadConfig();
		} catch(IOException e) {
			System.out.println("Could not read/find config.dat");
		} catch(ClassNotFoundException e) {
			System.out.println("Configuration invalid");
		}
		
		try{
			dataStoreInst.loadContacts();
		} catch(IOException e){
			System.out.println("contacts.dat not found");
		} catch(ClassNotFoundException e) {
			System.out.println("Contact class not found");
		}
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	        	try{
	        		dataStoreInst.storeConfig();
	        	} catch(IOException e) {
	        		System.out.println("Failed to save configuration");
	        	}
	        	try{
	        		dataStoreInst.storeContacts();
	        	} catch(IOException e) {
	        		System.out.println("Failed to save contacts");
	        	}
	        }
	    }, "Shuting Down"));
		
		MainFrame mainFrame = new MainFrame("Simple Mail");
		mainFrame.setVisible(true);
		
	}

}