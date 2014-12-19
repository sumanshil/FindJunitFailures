package src.oracle.mds.external.requestprocessor;

import java.util.Map;

import src.oracle.mds.external.outputgenerator.OutputGeneratorCallback;
import src.oracle.mds.external.resultpersister.PersistErrorAndFailureResult;

public abstract class RequestHandler
{
	public enum Type 
	{
		DEFAULT,
		EXPLICIT
	}
	public static final String LABEL_SERIES     = "LABEL_SERIES";
	public static final String LABEL            = "LABEL";
	public static final String TEST_PLATFORM    = "TEST_PLATFORM";
	public static final String SOURCE_PATH      = "SOURCE";
	public static final String DESTINATION_PATH = "DESTINATION";
	public static final String OUTPUT_PATH      = "OUTPUT";
	public abstract void processNewErrorAndFailure(Map<String, Object> input,
	                    		                OutputGeneratorCallback callback,
	                    		                PersistErrorAndFailureResult persister)
	                        throws Exception;
	
	public static RequestHandler getInstance(Type type) throws Exception
	{
		switch(type)
		{
		    case DEFAULT :
		    	return new DefaultTargetRequestHandler();
		    case EXPLICIT :
		    	return new ExplicitTargetRequestHandler();
		}
		throw new Exception("Unsupported request handler");
	}
}
