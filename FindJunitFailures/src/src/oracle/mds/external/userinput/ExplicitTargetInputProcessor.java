package src.oracle.mds.external.userinput;

public class ExplicitTargetInputProcessor extends UserInputProcessor
{

    @Override
    protected UserInput[] getUserInputs()
    {
        return new UserInput[]{new TestResultSource(),
                               new TestResultDestination(),
                               new OutputDirectory()};
    }

}
