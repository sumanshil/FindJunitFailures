package src.oracle.mds.external.htmlparser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import src.oracle.mds.external.conf.Configuration;
import src.oracle.mds.external.values.JunitReportCategory;


public abstract class HtmlErrorAndFailureLinkParser extends HtmlParser
{
	final static Logger logger = Logger.getLogger(HtmlErrorAndFailureLinkParser.class);
	public static HtmlErrorAndFailureLinkParser getInstance(ParserType type)
			                                    throws Exception
	{
		HtmlErrorAndFailureLinkParser instance = null;
		switch(type)
		{
		    case URI :
		    	instance = new UriLinkParser();
		    	break;
		    case LOCAL :
		    	throw new Exception("Parsing link for errors and failures for local files is not supported");
		}
		return instance;
	}
	
	protected abstract Elements getElements(String uri,
                                            String testPlatform)
                                            throws Exception;
	
    public Map<JunitReportCategory,String> getErrorAndFailureURI(String uri,
    		                                                     String testPlatform)
                                             throws Exception
    {
	    Iterator<Element> iter = getElements(uri, testPlatform).iterator();
	    boolean errorFound = false;
	    boolean failureFound = false;
	    Map<JunitReportCategory,String> retVal = new HashMap<JunitReportCategory,
	    		                                                       String>();
	    while(iter.hasNext())
	    {
	    	Element element = iter.next();
	    	//System.out.println(element.hasAttr("href"));
	    	if (element.hasAttr(Constants.HTML_HREF_LINK))
	    	{
	    		
	    		if (errorFound && failureFound)
	    		{
	    			break;
	    		}
	    		String value =  element.attr(Constants.HTML_HREF_LINK);
	    		if (value.contains(Configuration.FAILURE_HTML_FILE))
	    		{
	    			logger.info("Failure link found "+value);
	    			failureFound = true;
	    			retVal.put(JunitReportCategory.FAILURE, value);
	    		}
	    		else if (value.contains(Configuration.ERROR_HTML_FILE))
	    		{
	    			logger.info("Error link found "+value);
	    			errorFound = true;
	    			retVal.put(JunitReportCategory.ERROR, value);
	    		}
	    	}
	   	}
	    return retVal;  
    }

}
