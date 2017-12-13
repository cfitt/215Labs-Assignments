package cu.cs.cpsc215.project1;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * WebPage (imp WebElement):
	Constructor()
		Pass an address and save said address

	Crawl(<Depth>)
		Multiple ways I could do this
		after time thinking about it, I feel the simplest method would be 
			to increment/decrement depth 
		1) Create ULR object and data structures needed
			Data Structure choice is vast for this project
			play around with a few to find the best
				so far: ArrayList, Map, or Tree of some sort
		2) Check if depth is < 0
			if so, you’re done, return page
		3) Save string of ULR passed in, to ULR object
		4) BufferedReader that poop
			find we elements
			Sort them
		5) Check if reading is empty, then end?
		6) decrement depth

		What does this still need to be functional?
			A way to “parse” what the BufferedReader gives?
				Use JSoup?
					JSoup is OK, but keep experimenting…
				Make a Parser?
					More Control of what’s being passed.
					Make a parserBeReadin(), It can’t be that hard
		Getters()
			Still undecided on Data Structure I will use.
			Once decided, just return the Data together. Easy.
		Sorter()
			part of step 4 above
			A way to sort through to see if an type 
					of element as been read
			pages will require you to enter and download within
				same method of reading in, 
				just with <Depth> involved
			basic alg:
			if(WebPage)
				add Web page to it’s own Data for that type
			if(WebImage)
				add Image to it’s own Data
			if(WebFile)
				add File to it’s own Data?
				or file contents?
 * @author cfitt
 *
 */

public class WebPage implements WebElement {

	String url;
	ArrayList<WebPage> pages = new ArrayList<WebPage>();
	ArrayList<WebImage> images = new ArrayList<WebImage>();
	ArrayList<WebFile> files = new ArrayList<WebFile>();
	ArrayList<String> visited = new ArrayList<String>();
	private static Elements media;
	private static Elements links;
	
	
	public WebPage(String url) {
		this.url = url;
	}
	
	public void crawl(int depth){
		if (depth < 0){
			//End Recursion Case
			return;
		}
			System.out.println("Crawling " + url + " with a depth of " + depth);
			try { //Start Parse for page
			    Document doc = Jsoup.connect(url).get();
			    links = doc.select("[href]");//All Links
			    media = doc.select("[src]");//All Image Types
			    
			    String[] webImageExtensions = { "jpeg", "jpg", "bmp", "ppm", "png", "gif", "tiff", "ico" };
			    if(doc != null) { //If not a blank page, or down?
				    for (Element src : media) {
				    	String imgtopass = src.absUrl("src");
				    	for (int i = 0; i < webImageExtensions.length; i++)
			                if (imgtopass.endsWith(webImageExtensions[i]) || src.tagName().equals("img") 
			                		|| !visited.contains(imgtopass)){
		                    WebImage newImage = new WebImage(imgtopass);
				            images.add(newImage);
				            visited.add(imgtopass);
				        	System.out.println(imgtopass + " added Image to images");
			                }
				        }
				    }
			        
				    for (Element link : links) {
				    	//test if the link is another use-able link
				    	String baselinkUrl = link.absUrl("href");
				    	String copyLink = baselinkUrl;
			    	
		               String[] webPageExtensions = {"com", "edu", "gov", "net", "org", "shtml",
		            		   "dhtml", "xhtml", "asp", "aspx", "php", "jsp", "html", "htm"};
		               String[] webFileExtensions = {"doc", "docx", "log", "odt", "pdf", 
		            		   "rft", "txt", "wpd", "wps", "csv", "dat", "pps", "ppt","pptx", 
		            		   "tar", "xls", "xlsx", "gz", "jar", "rar", "zip"};
		               
		               for (int i = 0; i < webPageExtensions.length; i++)
		                   if (copyLink.endsWith(webPageExtensions[i]) || copyLink.endsWith("/")
		                		   && !visited.contains(copyLink)){
		                	   WebPage newPage = new WebPage(copyLink);
		            		   pages.add(newPage);
		            		   visited.add(copyLink);
		            		   System.out.println(copyLink + " Added Page to pages");
		               }
		               
		               for (int j = 0; j < webFileExtensions.length; j++)
		            	   if (copyLink.endsWith(webFileExtensions[j]) && !visited.contains(copyLink)){
		            		   String fileUrl = copyLink;
		            		   WebFile newFile = new WebFile(fileUrl);
		            		   files.add(newFile);
		            		   visited.add(copyLink);
		            		   System.out.println(fileUrl + " Added File to files");
		            	   }	   
		               } 
		            
			} catch (IOException e){
				System.out.println("Page Not Found");
				return;
			}
        DownloadRepository downloadRepository = DownloadRepository.getInstance();
        downloadRepository.addWebElements(this);
        for (WebPage page : pages)
			page.crawl(depth - 1);
	}
	@Override
	public void saver(String savePath) {
       //unused in pages, since they hold elements, and are not elements
	}
	
	public ArrayList<WebImage> getImages(){
		return images;
	}
	
	public ArrayList<WebFile> getFiles(){
		return files;
	}
	
	public ArrayList<WebPage> getWebPages(){
		return pages;
	}	
}