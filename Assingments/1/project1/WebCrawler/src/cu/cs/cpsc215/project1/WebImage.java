package cu.cs.cpsc215.project1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;


public class WebImage implements WebElement{
    
	String imgurl;
	
    public WebImage(String imgurl) {
    	this.imgurl = imgurl;
    }
	
	public void saver(String savePath) {
		URL url;
		
		try {
			url = new URL(imgurl);
			String imgName = imgurl.substring(imgurl.lastIndexOf("/") + 1);
			savePath += "images/";
            new File(savePath).mkdirs();
            
            File localFile = new File(savePath + imgName);
            
            InputStream reader = url.openStream();
            OutputStream writer = new FileOutputStream(localFile);
            
            byte[] b = new byte[2048];
            int length;
            while ((length = reader.read(b)) != -1) {
            	writer.write(b, 0, length);
            }
            reader.close();
            writer.close();
		} catch (IOException e){
                System.err.println("Could Not Save Image " + imgurl);
        }
	}
}
