package src.oracle.mds.external.requestprocessor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;

import src.oracle.mds.external.JunitError;
import src.oracle.mds.external.concurrentaskexecutor.ConcurrentTaskExecutor;
import src.oracle.mds.external.outputgenerator.OutputGeneratorCallback;
import src.oracle.mds.external.resultpersister.PersistErrorAndFailureResult;
import src.oracle.mds.external.values.JunitReportCategory;

public class ExplicitTargetRequestHandler extends RequestHandler
{
	final static Logger logger = Logger.getLogger(ExplicitTargetRequestHandler.class);
	@Override
	public void  processNewErrorAndFailure(
			Map<String, Object> input,
			OutputGeneratorCallback callback,
			PersistErrorAndFailureResult persister)
			throws Exception
	{
		String source = (String)input.get(SOURCE_PATH);
		String destination = (String)input.get(DESTINATION_PATH);
		String output = (String)input.get(OUTPUT_PATH);
		logger.info("Source "+source);
		logger.info("Destination "+destination);
//		
//		Map<JunitReportCategory, List<JunitError>>  sourceErrors =
//		    ErrorAndFailureProcessor.getInstance(source).process();
//		
//		Map<JunitReportCategory, List<JunitError>>  targetErrors =
//			    ErrorAndFailureProcessor.getInstance(destination).process();

		ErrorProcessorCallable sourceCallable = new ErrorProcessorCallable(source);
		ErrorProcessorCallable destCallable = new ErrorProcessorCallable(destination);
		ConcurrentTaskExecutor executor = ConcurrentTaskExecutor.getInstance();
		Future<Map<JunitReportCategory, List<JunitError>>> f1
		            = executor.execute(sourceCallable);
		Future<Map<JunitReportCategory, List<JunitError>>> f2
		            = executor.execute(destCallable);
		executor.stop();
		Map<JunitReportCategory, List<JunitError>>  sourceErrors = f1.get();
		Map<JunitReportCategory, List<JunitError>>  targetErrors = f2.get();
		
		callback.start();
		for(JunitError error : targetErrors.get(JunitReportCategory.ERROR))
		{
			boolean found = false;
			for(JunitError error1 : sourceErrors.get(JunitReportCategory.ERROR))
			{
				if (error1.equals(error))
				{
					found= true;
					break;
				}
			}
			if (!found)
			{
				callback.handleNewError(error);
			}
		}
		
		for(JunitError failure : targetErrors.get(JunitReportCategory.FAILURE))
		{
			boolean found = false;
			for(JunitError error1 : sourceErrors.get(JunitReportCategory.FAILURE))
			{
				if (error1.equals(failure))
				{
					found= true;
					break;
				}
			}
			if (!found)
			{
				callback.handleNewFailure(failure);
			}
			
		}
		callback.end();
		persister.persist(output, callback.result());
	}

}
