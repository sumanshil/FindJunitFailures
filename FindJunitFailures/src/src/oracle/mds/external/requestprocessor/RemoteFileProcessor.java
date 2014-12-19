package src.oracle.mds.external.requestprocessor;

import src.oracle.mds.external.htmlparser.HtmlErrorAndFailureParser;
import src.oracle.mds.external.htmlparser.HtmlParser.ParserType;

public class RemoteFileProcessor extends ErrorAndFailureProcessor
{

	public RemoteFileProcessor(String file)
	{
		super(file);
	}

	@Override
	public HtmlErrorAndFailureParser getParser()
	{		
		return HtmlErrorAndFailureParser.getInstance(ParserType.URI);
	}


}
