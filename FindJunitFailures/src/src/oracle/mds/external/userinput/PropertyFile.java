package src.oracle.mds.external.userinput;

public class PropertyFile extends UserInput
{

    @Override
    public String getDescription()
    {        
        return "Property file name which contains input key/value pair. User input can be passed using coomand line arguments or using property file";
    }

    @Override
    public String getUserTag()
    {
        return "-property-file";
    }

    @Override
    protected boolean isMandatory()
    {
        return false;
    }

}
