package src.oracle.mds.external.requestprocessor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import src.oracle.mds.external.JunitError;
import src.oracle.mds.external.values.JunitReportCategory;

public class ErrorProcessorCallable implements Callable<Map<JunitReportCategory, List<JunitError>>>
{
	private String fileName;
	public  ErrorProcessorCallable(String fileName)
	{
		this.fileName = fileName;
	}

	@Override
	public Map<JunitReportCategory, List<JunitError>> call() throws Exception
	{
		Map<JunitReportCategory, List<JunitError>>  errors =
			    ErrorAndFailureProcessor.getInstance(fileName).process();
        return errors;
	}

}
