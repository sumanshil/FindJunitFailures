package src.oracle.mds.external.requestprocessor;



import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import src.oracle.mds.external.conf.Configuration;
import src.oracle.mds.external.conf.SystemConstants;
import src.oracle.mds.external.htmlparser.HtmlErrorAndFailureLinkParser;
import src.oracle.mds.external.htmlparser.HtmlParser;
import src.oracle.mds.external.outputgenerator.HtmlOutputGenerator;
import src.oracle.mds.external.outputgenerator.OutputGeneratorCallback;
import src.oracle.mds.external.resultpersister.PersistErrorAndFailureResult;
import src.oracle.mds.external.util.LinuxCommandUtil;
import src.oracle.mds.external.values.JunitReportCategory;

public class DefaultTargetRequestHandler extends RequestHandler
{
	final static Logger logger = Logger.getLogger(DefaultTargetRequestHandler.class);
    private String getRootPath()
    {
	    StringBuilder sb = new StringBuilder(
	    		  Configuration.REMOTE_RESULT_SERVER_URL.length()+
				  Configuration.REMOTE_RESULT_PATH.length());
  	    sb.append(Configuration.REMOTE_RESULT_SERVER_URL);
  	    sb.append(Configuration.REMOTE_RESULT_PATH);
  	    sb.append("/");
  	    return sb.toString();
    }
    
    public String getRemoteLrgFailuresAndErrorsPath(String labelSeries,
                                                String label,
                                                String testPlatform)
                                                throws Exception
	{
		logger.info("LRG root path "+getRootPath());
		StringBuilder sb = new StringBuilder();
		sb.append(getRootPath());
		sb.append(labelSeries);
		
		HtmlErrorAndFailureLinkParser parser = HtmlErrorAndFailureLinkParser.
			                getInstance(HtmlParser.ParserType.URI);
		
		if (label == null)
		{
			Map<JunitReportCategory,String> uris = parser.getErrorAndFailureURI(sb.toString(), 
                    testPlatform );
			//141213.1231.S/wls_oracle_PDB/-junit-report/alltests-fails.html
			String errorUri = uris.get(JunitReportCategory.ERROR);
			if (errorUri == null)
			{
				throw new Exception("Not able to find location for label series "+labelSeries
						            +" platform "+testPlatform);
			}
			String subString = errorUri.substring(0, errorUri.lastIndexOf("/"));
			sb.append("/"+subString);
		}
		else
		{
			//141213.1231.S/wls_oracle_PDB/-junit-report
			sb.append("/");
			sb.append(label);
			sb.append("/");
			sb.append(testPlatform);
			sb.append("/");
			sb.append("-junit-report");
		}
		logger.info("Found remote source location"+sb.toString());
		return sb.toString();	
	}

	public String getLocalErrorsAndFailuresPath()
	     		 throws Exception	
	{
		String viewRoot = LinuxCommandUtil.getCurrentViewRootPath();
		logger.info("View root"+SystemConstants.VIEW_ROOT);		
		
		if (viewRoot == null)
		{
			throw new Exception("$ADE_VIEW_ROOT not found");
		}
	    
		if (viewRoot == null
			  || viewRoot.length() == 0)
		{
		throw new Exception("View root directory not found");
		}
		StringBuffer sb = new StringBuffer();
		sb.append(viewRoot);
		sb.append("/oracle/built");
		sb.append("/-junit-report");
		sb.append("/");
		return sb.toString();
	}

	@Override
	public void processNewErrorAndFailure(
			                 Map<String, Object> input,
			                 OutputGeneratorCallback callback,
			                 PersistErrorAndFailureResult persister)
			                 throws Exception
	{
	    String labelSeries = (String)input.get(LABEL_SERIES);
	    String label  = (String)input.get(LABEL);
	    String testPlatform = (String)input.get(TEST_PLATFORM);
	    String outputPath = (String)input.get(OUTPUT_PATH);
	    
	    String source = getRemoteLrgFailuresAndErrorsPath(labelSeries,
	    		                                      label,
	    		                                      testPlatform);
	    String destination = getLocalErrorsAndFailuresPath();
		Map<String, Object> newInput = new HashMap<String, Object>();
		newInput.put(RequestHandler.SOURCE_PATH, source);
		newInput.put(RequestHandler.DESTINATION_PATH, destination);
		newInput.put(RequestHandler.OUTPUT_PATH, outputPath);
		
		RequestHandler.getInstance(Type.EXPLICIT).
		    processNewErrorAndFailure(newInput,
		    		                  new HtmlOutputGenerator(),
		    		                  PersistErrorAndFailureResult.getInstance());
	}

}
