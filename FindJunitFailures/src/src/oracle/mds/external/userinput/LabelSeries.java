package src.oracle.mds.external.userinput;

public class LabelSeries extends UserInput
{

    @Override
    public String getDescription()
    {
        return "Label series of MDS release(.e.g JDEVADF_MAIN_GENERIC)";
    }

    @Override
    public String getUserTag()
    {               
        return "-label-series";
    }

    @Override
    protected boolean isMandatory()
    {
        return true;
    }

}
