package src.oracle.mds.external.userinput;

public abstract class UserInput
{
    public abstract String getDescription();
    public abstract String getUserTag();
    protected abstract boolean isMandatory();
    private String value;

    public void setValue(String value)
    {
        this.value = value;
    }


    public String getValue()
    {
        return value;
    }

}
