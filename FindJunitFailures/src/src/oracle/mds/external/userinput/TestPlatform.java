package src.oracle.mds.external.userinput;

public class TestPlatform extends UserInput
{

    @Override
    public String getDescription()
    {                               
        return "MDS test platform (.e.g. wls_oracle_PDB[TENANT1])";
    }

    @Override
    public String getUserTag()
    {               
        return "-test-platform";
    }

    @Override
    protected boolean isMandatory()
    {               
        return true;
    }

}
