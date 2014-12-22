package src.oracle.mds.external.userinput;

public class TestResultSource extends UserInput
{

    @Override
    public String getDescription()
    {
        return "Source location for Junit test results. This should contain alltests-errors.html and alltests-fails.html.\n"
                +" The errors and failures in source will be compared against destination erros and failures.\n"
                +" Any error and failure that is not found in source will be considered as new and will be reported.";
    }

    @Override
    public String getUserTag()
    {               
        return "-source";
    }

    @Override
    protected boolean isMandatory()
    {
        return true;
    }

}
