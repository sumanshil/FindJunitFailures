package src.oracle.mds.external.outputgenerator;

import src.oracle.mds.external.JunitError;

public class DefaultOutputGenerator implements OutputGeneratorCallback
{


	@Override
	public void handleNewError(JunitError junitError)
	{
		System.out.println(junitError);
		
	}

	@Override
	public void handleNewFailure(JunitError junitError)
	{
		System.out.println(junitError);
		
	}

	@Override
	public void start()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void end()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String result()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
