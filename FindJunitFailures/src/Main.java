

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import src.oracle.mds.external.outputgenerator.HtmlOutputGenerator;
import src.oracle.mds.external.requestprocessor.RequestHandler;
import src.oracle.mds.external.requestprocessor.RequestHandler.Type;
import src.oracle.mds.external.resultpersister.PersistErrorAndFailureResult;
import src.oracle.mds.external.userinput.UserInputHandler;

public class Main
{
	final static Logger logger = Logger.getLogger(Main.class);
	
	public static void main(String[] args) throws Exception
	{
		logger.info("Hello World");
		try
		{
	        UserInputHandler.MODE_IMPLICIT.processUserInput(args);        
	        if (UserInputHandler.MODE_IMPLICIT.validateUserInput().size() == 0)
			{
	        	Map<String, Object> input = new HashMap<String, Object>();
			    input.put(RequestHandler.LABEL_SERIES, UserInputHandler.
			    		                               MODE_IMPLICIT.
			    		                               LABEL_SERIES.getValue());
			    input.put(RequestHandler.LABEL, UserInputHandler.
	                                            MODE_IMPLICIT.
	                                            LABEL.getValue());
	
			    input.put(RequestHandler.TEST_PLATFORM, UserInputHandler.
	                                            MODE_IMPLICIT.
	                                            TEST_PLATFORM.getValue());
	
			    input.put(RequestHandler.OUTPUT_PATH, UserInputHandler.
	                                                  MODE_IMPLICIT.
	                                                  OUTPUT_DIR.getValue());
	
			    RequestHandler.getInstance(Type.DEFAULT).
			           processNewErrorAndFailure(input,
			        		                     new HtmlOutputGenerator(),
			        		                     PersistErrorAndFailureResult.getInstance());
			}
	        else if (UserInputHandler.MODE_EXPLICIT.validateUserInput().size() == 0)
			{
				Map<String, Object> input = new HashMap<String, Object>();
				input.put(RequestHandler.SOURCE_PATH, UserInputHandler.MODE_EXPLICIT
						                             .TEST_RESULT_SOURCE.getValue());
				input.put(RequestHandler.DESTINATION_PATH,
						                              UserInputHandler.MODE_EXPLICIT
	                                                  .TEST_RESULT_SOURCE.getValue());
			    input.put(RequestHandler.OUTPUT_PATH, UserInputHandler.
									                  MODE_IMPLICIT.
									                  OUTPUT_DIR.getValue());
				
				RequestHandler.getInstance(Type.EXPLICIT).
				    processNewErrorAndFailure(input,
				    		                  new HtmlOutputGenerator(),
				    		                  PersistErrorAndFailureResult.getInstance());
			}
	        else
	        {
	        	System.out.println(UserInputHandler.
	        			           MODE_IMPLICIT.getModeDescription());
	        	System.out.println(UserInputHandler.
	        			           MODE_EXPLICIT.getModeDescription());
	        }
		}
		catch(Exception e)
		{
			logger.error("Error", e);
			System.out.println("Unable to process LRG result diff. Please see log for error");
		}
	}

}
