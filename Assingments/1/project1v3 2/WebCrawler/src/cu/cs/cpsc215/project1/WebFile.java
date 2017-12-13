package cu.cs.cpsc215.project1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class WebFile implements WebElement{

	String fileurl;
	
	public WebFile(String fileurl){
		this.fileurl = fileurl;
	}
	
	public void saver(String filePath){
		URL url;
		
			try {
				url = new URL(fileurl);
				String nameofFile;
				if(fileurl.substring(fileurl.lastIndexOf("/") + 1) != null)
					nameofFile = fileurl.substring(fileurl.lastIndexOf("/") + 1);
				else
					nameofFile = fileurl.substring(fileurl.lastIndexOf("/"));
				filePath += "files/";
		        new File(filePath).mkdirs();
		        //Makes a new folder within the directory specified
		        
		        File localFile = new File(filePath + nameofFile);
		        try {
		        	InputStream fileReader= url.openStream();
		        	OutputStream fileWriter = new FileOutputStream(localFile);
		        
			        byte[] b = new byte[2048];//This should be big enough
			        int len;
			       
					while ((len = fileReader.read(b)) != -1) {
						fileWriter.write(b, 0, len);
					}
					
			        fileReader.close();
			        fileWriter.close();
			        System.out.println("File: " + fileurl);
		        } catch (IOException e) {
		        	System.err.println("Could not save file " + fileurl + " to " + filePath);
		        	return;
		        }
			} catch (MalformedURLException e1) {
				System.err.println("Could not save file to " + filePath);
				return;
			}
		}    
	}


