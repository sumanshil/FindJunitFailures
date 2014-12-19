package src.oracle.mds.external.stringmodifier;

import org.apache.log4j.Logger;

public class RemoveViewRootDirectory extends StringModifier
{
	final static Logger logger = Logger.getLogger(RemoveViewRootDirectory.class);
	@Override
	protected String manipulateString(String input)
	{
		while(input.contains("/ade/"))
		{
			int index1 = input.indexOf("/ade/");
			int index2 = input.indexOf('/', index1+"/ade/".length());
			String stringToBeReplaced = input.substring(index1, index2);
			logger.info("Replacing "+stringToBeReplaced+" from "+input+" as it may be a view root directory");
			input = input.replaceAll(stringToBeReplaced, "*");
		}
		return input;
	}

}
