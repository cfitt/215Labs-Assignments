package cu.cs.cpsc215.project1;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Downloads and passes WebElements
 * key piece in saving, which is the last step
 * Previous implementations of this class were intended to directly save each element
 * 		this was a bad idea, plus it was slow
 * Singleton class, hopefully I set it up correctly
 * 
 * @author cfitt
 *
 */

public class DownloadRepository implements Repository{
    private static DownloadRepository instance;
    private ArrayList<WebFile>  files;
    private ArrayList<WebImage> images;
    //Combine above to have a list of all places visited
    private ArrayList<String> names;
    
    private DownloadRepository()
    {
        files  = new ArrayList<WebFile>();
        images = new ArrayList<WebImage>();
    }
    
    public static DownloadRepository getInstance()
    {
        if (instance == null)
            instance = new DownloadRepository();
        return instance;
    }

    public void addName(String urlName){
    	names.add(urlName);
    }
    
    public boolean visted(String urlCheck){
    	if(!names.contains(urlCheck))
    		return true;
    	return false;
    }
    
    public void download(String savePath) throws IOException
    {
	    for (WebFile webFile : files)
	        webFile.saver(savePath);
	    for (WebImage webImage : images)
	        webImage.saver(savePath);
    }
	
	public void addWebElements(WebPage webPage) {
		for (WebFile webFile : webPage.getFiles())
			files.add(webFile);
		for (WebImage webImage : webPage.getImages())
        	images.add(webImage);
	}
}
