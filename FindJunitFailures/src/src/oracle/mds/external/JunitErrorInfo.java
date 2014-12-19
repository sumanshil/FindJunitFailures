package src.oracle.mds.external;

import org.apache.log4j.Logger;

import src.oracle.mds.external.stringmodifier.RemoveHttpServerAndPort;
import src.oracle.mds.external.stringmodifier.RemoveViewRootDirectory;
import src.oracle.mds.external.stringmodifier.StringModifier;


public class JunitErrorInfo implements JunitError
{
	final static Logger logger = Logger.getLogger(JunitErrorInfo.class);
	private String className;
	private String methodName;
	private String message;
	private String testCategory;
	private String htmlLink;
	public JunitErrorInfo(String className,
			              String methodName,
			              String message,
			              String testCategory,
			              String htmlLink)
	{
		this.className = className;
		this.methodName = methodName;
		this.message = message;
		this.testCategory = testCategory;
		this.htmlLink = htmlLink;
	}
	
	public void setHtmlLink(String htmlLink)
	{
		this.htmlLink = htmlLink;
	}

	@Override
	public String getClassName()
	{		
		return this.className;
	}

	@Override
	public String getMethodName()
	{
		return this.methodName;
	}

	@Override
	public String getMessage()
	{
		return this.message;
	}
	
    @Override
    public boolean equals(Object obj)
    {
    	logger.info("Comparing "+this);
    	logger.info("And");
    	logger.info(obj);
    	JunitError error = (JunitError)obj;
    	StringModifier modifier = new RemoveHttpServerAndPort();
    	modifier.setNext(new RemoveViewRootDirectory());
    	String msgObj  = modifier.process(error.getMessage());
    	String msgThis = modifier.process(this.getMessage());
    	return error.getClassName().intern().equals(this.className.intern())
    		  && error.getMethodName().intern().equals(this.methodName.intern())
    		  && msgObj.intern().equals(msgThis.intern());
    	        //&& error.getTestCategory().intern().equals(this.testCategory.intern());
    }
    
    public String toString()
    {
    	StringBuffer sb = new StringBuffer();
    	sb.append("Test Category: "+ this.testCategory +"\n");
    	sb.append("Class Name: "+this.className+"\n");
    	sb.append("Method Name: "+this.methodName+"\n");
    	sb.append("Message: "+this.message+"\n");
    	
    	return sb.toString();
    }

	@Override
	public String getTestCategory()
	{
		return this.testCategory;
	}

	@Override
	public String getHtmlLink()
	{
		return htmlLink;
	}
	
}
