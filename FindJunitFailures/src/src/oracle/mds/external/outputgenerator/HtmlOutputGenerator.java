package src.oracle.mds.external.outputgenerator;

import src.oracle.mds.external.JunitError;

public class HtmlOutputGenerator implements OutputGeneratorCallback
{
    private StringBuffer content = new StringBuffer();
    private final String NEW_LINE = "\n";
	@Override
	public void start()
	{
		addToBufferWithNewLine(content,"<html>");

		addToBufferWithNewLine(content,"<body>");
		addToBufferWithNewLine(content,"<table border=2>");
		addToBufferWithNewLine(content,"<tr>");
		addToBufferWithNewLine(content,"<th>");
		addToBufferWithNewLine(content,"Category");		
		addToBufferWithNewLine(content,"</th>");
		addToBufferWithNewLine(content,"<th>");
		addToBufferWithNewLine(content,"Class");
		addToBufferWithNewLine(content,"</th>");
		addToBufferWithNewLine(content,"<th>");
		addToBufferWithNewLine(content,"Method");
		addToBufferWithNewLine(content,"</th>");
		addToBufferWithNewLine(content,"<th>");
		addToBufferWithNewLine(content,"Message");
		addToBufferWithNewLine(content,"</th>");
		addToBufferWithNewLine(content,"<th>");
		addToBufferWithNewLine(content,"Link");
		addToBufferWithNewLine(content,"</th>");				
		addToBufferWithNewLine(content,"</tr>");
	}

	@Override
	public void handleNewError(JunitError junitError)
	{
		addToBufferWithNewLine(content, "<tr>");
		addToBufferWithNewLine(content, "<td>");
		addToBufferWithNewLine(content, "Error");
		addToBufferWithNewLine(content, "</td>");
		addToBufferWithNewLine(content, "<td>");
		addToBufferWithNewLine(content, junitError.getClassName());
		addToBufferWithNewLine(content, "</td>");
		addToBufferWithNewLine(content, "<td>");
		addToBufferWithNewLine(content, junitError.getMethodName());
		addToBufferWithNewLine(content, "</td>");
		addToBufferWithNewLine(content, "<td>");
		addToBufferWithNewLine(content, junitError.getMessage());
		addToBufferWithNewLine(content, "</td>");
		addToBufferWithNewLine(content, "<td>");
		addToBufferWithNewLine(content, buildHrefLink(junitError));
		addToBufferWithNewLine(content, "</td>");				
		addToBufferWithNewLine(content, "</tr>");
	}

	@Override
	public void handleNewFailure(JunitError junitError)
	{
		addToBufferWithNewLine(content, "<tr>");
		addToBufferWithNewLine(content, "<td>");
		addToBufferWithNewLine(content, "Failure");
		addToBufferWithNewLine(content, "</td>");
		addToBufferWithNewLine(content, "<td>");
		addToBufferWithNewLine(content, junitError.getClassName());
		addToBufferWithNewLine(content, "</td>");
		addToBufferWithNewLine(content, "<td>");
		addToBufferWithNewLine(content, junitError.getMethodName());
		addToBufferWithNewLine(content, "</td>");
		addToBufferWithNewLine(content, "<td>");
		addToBufferWithNewLine(content, junitError.getMessage());
		addToBufferWithNewLine(content, "</td>");	
		addToBufferWithNewLine(content, "<td>");
		addToBufferWithNewLine(content, buildHrefLink(junitError));
		addToBufferWithNewLine(content, "</td>");						
		addToBufferWithNewLine(content, "</tr>");
	}

	@Override
	public void end()
	{
		addToBufferWithNewLine(content,"</table>");
		addToBufferWithNewLine(content,"</body>");
		addToBufferWithNewLine(content,"</html>");
	}

	@Override
	public String result()
	{
		return content.toString();
	}

	private void addToBufferWithNewLine(StringBuffer sb, String content)
	{
		sb.append(content+NEW_LINE);
	}
	
	private String buildHrefLink(JunitError junitError)
	{
		return "<a href=\""+junitError.getHtmlLink()+"\">"+junitError.getMethodName()+"</a>";
	}
}
