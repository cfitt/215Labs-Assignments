package cu.cs.cpsc215.project1;

import java.io.IOException;

public interface Repository {
	public void addWebElements(WebPage webPage);
    public void download(String savePath) throws IOException;
}
