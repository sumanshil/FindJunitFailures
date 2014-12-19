package src.oracle.mds.external.htmlparser;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class LocalFileParser extends HtmlErrorAndFailureParser
{

	@Override
	protected Elements getElements(String path) throws IOException
	{
	    Document doc = Jsoup.parse(new File(path), "ISO-8859-1");	    
	    Elements elements = doc.getElementsByAttribute("href");
		return elements;
	}

}
