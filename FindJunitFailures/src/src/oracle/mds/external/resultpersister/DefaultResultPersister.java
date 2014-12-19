package src.oracle.mds.external.resultpersister;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;

import src.oracle.mds.external.conf.SystemConstants;

public class DefaultResultPersister extends PersistErrorAndFailureResult
{

	@Override
	public void persist(String outputPath, String result) throws Exception
	{
		//String rootPath = SystemConstants.OUTPUT_DIR;
		String newFileName  = outputPath+"/NewFailure"+Calendar.getInstance().getTimeInMillis()+".html";
		File file = new File(newFileName);
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(result);
        writer.close();
        System.out.println("Result stored at "+newFileName.toString());
	}

}
