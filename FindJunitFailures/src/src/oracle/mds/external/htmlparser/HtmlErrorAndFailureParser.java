package src.oracle.mds.external.htmlparser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import src.oracle.mds.external.JunitError;
import src.oracle.mds.external.JunitErrorInfo;

public abstract class HtmlErrorAndFailureParser extends HtmlParser
{
	final static Logger logger = Logger.getLogger(HtmlErrorAndFailureParser.class);
	public static HtmlErrorAndFailureParser getInstance(ParserType type)
	{
		HtmlErrorAndFailureParser instance = null;
		switch(type)
		{
		    case URI :
		    	instance = new UriParser();
		    	break;
		    case LOCAL :
		    	instance = new LocalFileParser();
		    	break;
		}
		return instance;
	}
	
	protected abstract Elements getElements(String path) throws Exception;
	public List<JunitError> getErrorsAndFailures(String path) throws Exception
	{
		Elements elements = getElements(path);
		String rootPath   = getRootPath(path);
		List<JunitError> retVal = extractData(rootPath, elements);
		return retVal;
	}
	
	private String getRootPath(String path)
	{
		int lastSlashindex = path.lastIndexOf('/');
		return path.substring(0, lastSlashindex);
	}

	private List<JunitError> extractData(String root,
			                             Elements elements)
			                             throws Exception
	{
	    Iterator<Element> iter = elements.iterator();
	    List<JunitError> retVal = new ArrayList<JunitError>();
	    while(iter.hasNext())
	    {
	    	Element element = iter.next();
	    	//System.out.println(element.hasAttr("href"));
	    	if (element.hasAttr(Constants.HTML_HREF_LINK))
	    	{
	    		
	    		String value =  element.attr(Constants.HTML_HREF_LINK);
	    		if (value.contains(Constants.HTML_HASH)
	    			&& value.contains(Constants.HTML_UNDERSCORE))
	    		{
	    			int index1 = value.indexOf(Constants.HTML_UNDERSCORE);
	    			int index2 = value.indexOf(Constants.HTML_DOT);
	    			int index3 = value.indexOf(Constants.HTML_HASH);
	    			String className = value.substring(index1+1, index2);
	    			String methodName = value.substring(index3+1);
	    			
	    			//Elements elementsList = element.siblingElements();
	    			Element textElement = element.parent().nextElementSibling().nextElementSibling();
	    			Iterator<Node> iterator = textElement.childNodes().iterator();	    			
	    			String message = null;
	    			while(iterator.hasNext())
	    			{
	    				Node node = iterator.next();
	    				if (node.siblingIndex() == 0)
	    				{
	    					message = node.toString();	
	    					break;
	    				}
	    			}
	    			
	    			Element testCategoryElem = ((Element)element.parent().parent().childNode(1));
	    			String testType = null;
	    			if (testCategoryElem.tagName().equals(Constants.HTML_TD_TAG))
	    			{
	    				testType = testCategoryElem.text();	
	    			}
	    			else
	    			{
	    				throw new Exception("Test category tag not found");
	    			}
	    			JunitError newError = new JunitErrorInfo(className,
	    					                                 methodName,
	    					                                 message,
	    					                                 testType,
	    					                                 "./junit-report/"+value);
	    			logger.info("New Error found "+newError);
	    			retVal.add(newError);
	    		}
	    		//System.out.println(value);
	    		
	    	}	    		    	
	   	}		
	    return retVal;
	}

}
