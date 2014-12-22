package src.oracle.mds.external.userinput;

import src.oracle.mds.external.util.LinuxCommandUtil;

public class OutputDirectory extends UserInput
{

    @Override
    public String getValue()
    {
        String value = super.getValue();
        if (value == null)
        {
            value = LinuxCommandUtil.getCurrentWorkingDir();
        }
        return value;
    }
    
    @Override
    public String getDescription()
    {                               
        return "Output directory of result."
                + " If this argument is missing,"
                + " output will be generated in current directory.";
    }

    @Override
    public String getUserTag()
    {               
        return "-output-dir";
    }

    @Override
    protected boolean isMandatory()
    {               
        return false;
    }           

}
