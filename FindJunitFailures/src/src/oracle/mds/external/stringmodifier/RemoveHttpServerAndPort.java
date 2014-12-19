package src.oracle.mds.external.stringmodifier;


import org.apache.log4j.Logger;

public class RemoveHttpServerAndPort extends StringModifier
{
	final static Logger logger = Logger.getLogger(RemoveHttpServerAndPort.class);
	@Override
	protected String manipulateString(String input)
	{
		if (input == null)
		{
			return null;
		}
		String retVal = null;
		if (input.startsWith("http://"))
		{
			int indexOfSlash = input.indexOf('/', "http://".length());
			logger.info("Replacing "+input.substring(0, indexOfSlash)+" from "+ input);
			retVal = input.substring(indexOfSlash);
			
		}
		else
		{
			retVal = input;
		}
		return retVal;
	}

}
