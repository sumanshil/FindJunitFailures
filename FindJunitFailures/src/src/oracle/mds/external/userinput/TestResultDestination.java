package src.oracle.mds.external.userinput;

public class TestResultDestination extends UserInput
{

    @Override
    public String getDescription()
    {
        return "Destination location for Junit test results. This should contain alltests-errors.html and alltests-fails.html.";
    }

    @Override
    public String getUserTag()
    {
        return "-destination";
    }

    @Override
    protected boolean isMandatory()
    {
        return true;
    }

}
