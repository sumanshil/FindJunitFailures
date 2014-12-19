package src.oracle.mds.external.resultpersister;

public abstract class PersistErrorAndFailureResult
{
    public abstract void persist(String outputPath, String result) throws Exception;
    
    public static PersistErrorAndFailureResult getInstance()
    {
    	return new DefaultResultPersister();
    }
    
}
