package src.oracle.mds.external.concurrentaskexecutor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import src.oracle.mds.external.JunitError;
import src.oracle.mds.external.values.JunitReportCategory;

public class TaskExecutorWithExecutorService extends ConcurrentTaskExecutor
{
	private ExecutorService service = Executors.newFixedThreadPool(2);
	private static TaskExecutorWithExecutorService instance
	                   = new TaskExecutorWithExecutorService();
    private TaskExecutorWithExecutorService()
    {
    	
    }
    
    
	public static ConcurrentTaskExecutor getInstance()
	{
         return instance;		
	}
	
	
	@Override
	public Future<Map<JunitReportCategory, List<JunitError>>> execute(Callable<Map<JunitReportCategory, List<JunitError>>> callable)
	{
		return service.submit(callable);
	}
	
	@Override
	public void stop()
	{
		service.shutdown();
	}
    
}
