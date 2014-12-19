package src.oracle.mds.external.htmlparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;



public class UriLinkParser extends HtmlErrorAndFailureLinkParser
{

	@Override
	protected Elements getElements(String uri,
			                       String  testPlatform)
			                       throws Exception
	{
	    Document doc = Jsoup.connect(uri).get();	    
	    Elements elements = doc.getElementsByAttributeValueContaining(Constants.HTML_HREF_LINK,
	    		                                                      testPlatform);
	    return elements;
	}

}
