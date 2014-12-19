package src.oracle.mds.external;

public interface JunitError
{
    public String getClassName();
    public String getMethodName();
    public String getMessage();
    public String getTestCategory();
    public String getHtmlLink();
}
