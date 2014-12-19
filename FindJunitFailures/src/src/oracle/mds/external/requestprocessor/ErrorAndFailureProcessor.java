package src.oracle.mds.external.requestprocessor;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import src.oracle.mds.external.JunitError;
import src.oracle.mds.external.conf.Configuration;
import src.oracle.mds.external.htmlparser.HtmlErrorAndFailureParser;
import src.oracle.mds.external.values.JunitReportCategory;

public abstract class ErrorAndFailureProcessor
{
	final static Logger logger = Logger.getLogger(ErrorAndFailureProcessor.class);
	private String file = null;
	public ErrorAndFailureProcessor(String file)
	{
		this.file = file;
	}
	
	protected abstract HtmlErrorAndFailureParser getParser();
	
    public final Map<JunitReportCategory, List<JunitError>> process()
                                   throws Exception
    {
    	HtmlErrorAndFailureParser parser = getParser();
		Map<JunitReportCategory, List<JunitError>> retVal 
               = new HashMap<JunitReportCategory, List<JunitError>>();
        String fullFilePath = this.file +"/"+ Configuration.FAILURE_HTML_FILE;
        logger.info("Failure HTML location "+ fullFilePath);
        List<JunitError> failures = parser.getErrorsAndFailures(fullFilePath);
        fullFilePath = this.file +"/"+ Configuration.ERROR_HTML_FILE;
        logger.info("Error HTML location "+ fullFilePath);
        List<JunitError> errors = parser.getErrorsAndFailures(fullFilePath);
	    retVal.put(JunitReportCategory.ERROR, errors);
	    retVal.put(JunitReportCategory.FAILURE, failures);
	    return retVal;    	
    }
     
    
    public static ErrorAndFailureProcessor getInstance(String file) throws Exception
    {
    	if (file.startsWith("http"))
    	{
    		return new RemoteFileProcessor(file);
    	}
    	else if (file.startsWith("/")
    			|| file.startsWith("D:/"))// for debug in windows
    	{
    		return new LocalFileProcessor(file);
    	}
    	else
    	{
    	    throw new Exception("Unsupported file type");
    	}
    }
    
}
