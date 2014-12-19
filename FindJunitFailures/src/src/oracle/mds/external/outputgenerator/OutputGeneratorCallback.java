package src.oracle.mds.external.outputgenerator;

import src.oracle.mds.external.JunitError;

public interface OutputGeneratorCallback
{
	public void start();
	public void handleNewError(JunitError junitError);
	public void handleNewFailure(JunitError junitError);
	public void end();
	
	public String result();
}
