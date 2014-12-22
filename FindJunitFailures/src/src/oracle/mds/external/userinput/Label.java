package src.oracle.mds.external.userinput;

public class Label extends UserInput
{

    @Override
    public String getDescription()
    {
        return "Label value of a Label series (.e.g. 141215.0331.S), If this is not specified latest label will be used";
    }

    @Override
    public String getUserTag()
    {
        return "-label";
    }

    @Override
    protected boolean isMandatory()
    {
        return false;
    }

}
