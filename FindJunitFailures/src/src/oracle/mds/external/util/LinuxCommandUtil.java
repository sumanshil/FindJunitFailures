package src.oracle.mds.external.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class LinuxCommandUtil
{
	final static Logger logger = Logger.getLogger(LinuxCommandUtil.class);
    public static String getLongHostName()
    {
//    	String retVal = executeCommand("hostname -f");
//    	return retVal;
    	return "D:/Test";
    }

    public static String getTestConfigurationValue()
    {
    	String retVal = executeCommand("echo $TEST_CONF");
    	logger.info("Current View Root "+ retVal);
    	return retVal;    	    	
    }

    public static String getCurrentWorkingDir()
    {
    	String retVal = executeCommand("pwd");
    	logger.info("Current working dir "+ retVal);
    	return retVal;
    	//return "D:/Test";
    }
    
    public static String getCurrentViewRootPath()
    {
    	String retVal = executeCommand("echo $ADE_VIEW_ROOT");
    	logger.info("Current View Root "+ retVal);
    	return retVal;
//    	return "D:/Test";
//    	return "D:/Test";
    }
    
    private static String executeCommand(String command) {
    	 
		StringBuffer output = new StringBuffer();
 
		Process p = null;
		try {
			p = Runtime.getRuntime().exec(new String[]{"bash", "-c", command});
			p.waitFor();
			BufferedReader reader = 
                            new BufferedReader
                            (new InputStreamReader(p.getInputStream()));
 
                        String line = "";			
			while ((line = reader.readLine())!= null) 
			{
				output.append(line);
			}
 
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			if (p!= null)
			{
				p.destroy();
			}
		}
 
		return output.toString();
 
	}    
}
