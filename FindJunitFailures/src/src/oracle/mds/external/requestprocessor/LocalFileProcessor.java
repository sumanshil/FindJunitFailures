package src.oracle.mds.external.requestprocessor;

import src.oracle.mds.external.htmlparser.HtmlErrorAndFailureParser;
import src.oracle.mds.external.htmlparser.HtmlParser.ParserType;

public class LocalFileProcessor extends ErrorAndFailureProcessor
{

	public LocalFileProcessor(String file)
	{
		super(file);
	}

	@Override
	public HtmlErrorAndFailureParser getParser()
	{		
		return HtmlErrorAndFailureParser.getInstance(ParserType.LOCAL);
	}


}
