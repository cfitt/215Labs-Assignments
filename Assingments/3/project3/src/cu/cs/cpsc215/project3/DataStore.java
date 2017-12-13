package cu.cs.cpsc215.project3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataStore {
	private Configuration<String> configMain;
	private ArrayList<Contact<String>> contactList;
	
	private static DataStore SingleInstance = null; //For Singleton Pattern
	
	private ObjectInputStream configObjInStream;
	private ObjectInputStream contactsObjInStream;
	private ObjectOutputStream configObjOutStream;
	private ObjectOutputStream contactsObjOutStream;
	
	private DataStore(){
		configMain = new Configuration<String>();
		contactList = new ArrayList<Contact<String>>();
	}
	
	public static DataStore instantiate(){
		if(SingleInstance == null){
			SingleInstance = new DataStore();
			return SingleInstance;
		}
		return SingleInstance;	
	}
	
	@SuppressWarnings("unchecked")
	public Configuration<String> loadConfig() throws IOException, ClassNotFoundException{
		FileInputStream configFileInStream = new FileInputStream("config.dat");
		configObjInStream = new ObjectInputStream(configFileInStream);
		configMain = (Configuration<String>)configObjInStream.readObject();
		return configMain;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Contact<String>> loadContacts() throws IOException, ClassNotFoundException {
		FileInputStream contactsFileInStream = new FileInputStream("contacts.dat");
		contactsObjInStream = new ObjectInputStream(contactsFileInStream);
		contactList = (ArrayList<Contact<String>>)contactsObjInStream.readObject();
		return contactList;
	}
	
	public void storeConfig() throws IOException{
		//Stores configuration information to disk
		FileOutputStream configFileOutStream = new FileOutputStream("config.dat");
		configObjOutStream = new ObjectOutputStream(configFileOutStream);
		configObjOutStream.writeObject(configMain);
	}
	
	public void storeContacts() throws IOException{
		//Stores contact information to disk
		FileOutputStream contactsFileOutStream = new FileOutputStream("contacts.dat");
		contactsObjOutStream = new ObjectOutputStream(contactsFileOutStream);
		contactsObjOutStream.writeObject(contactList);
	}
	
	public Configuration<String> getConfig() {
		return configMain;
	}

	public ArrayList<Contact<String>> getContacts() {
		return contactList;
	}
}