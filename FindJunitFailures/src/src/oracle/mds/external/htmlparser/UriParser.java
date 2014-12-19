package src.oracle.mds.external.htmlparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class UriParser extends HtmlErrorAndFailureParser
{
	@Override
	protected Elements getElements(String path) throws Exception
	{
	    Document doc = Jsoup.connect(path).get();	    
	    Elements elements = doc.getElementsByAttribute(Constants.HTML_HREF_LINK);
		return elements;
	}

}
