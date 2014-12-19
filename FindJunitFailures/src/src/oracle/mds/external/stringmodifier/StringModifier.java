package src.oracle.mds.external.stringmodifier;

public abstract class StringModifier
{
    private  StringModifier next;
    
    public void setNext(StringModifier next)
    {
    	this.next = next;
    }
    
    protected StringModifier getNext()
    {
    	return this.next;
    }
    
    protected abstract String manipulateString(String input);
    public String process(String input)
    {
    	String retVal = manipulateString(input);
    	if (next != null)
    	{
    		return next.process(retVal);
    	}
    	return retVal;
    }
}
