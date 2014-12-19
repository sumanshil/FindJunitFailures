package src.oracle.mds.external.concurrentaskexecutor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import src.oracle.mds.external.JunitError;
import src.oracle.mds.external.values.JunitReportCategory;

public abstract class ConcurrentTaskExecutor
{
    public abstract Future<Map<JunitReportCategory, List<JunitError>>> execute(Callable<Map<JunitReportCategory, List<JunitError>>> callable);
    public abstract void stop();
    public static ConcurrentTaskExecutor getInstance()
    {
    	return  TaskExecutorWithExecutorService.getInstance();
    }
}
