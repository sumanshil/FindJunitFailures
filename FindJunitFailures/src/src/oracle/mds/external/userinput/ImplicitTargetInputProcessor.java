package src.oracle.mds.external.userinput;

public class ImplicitTargetInputProcessor extends UserInputProcessor
{

    @Override
    protected UserInput[] getUserInputs()
    {
        return new UserInput[]{new Label(),
                new LabelSeries(),
                new TestPlatform(),
                new OutputDirectory()};
    }

}
