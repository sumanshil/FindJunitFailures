package src.oracle.mds.external.requestprocessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.oracle.mds.external.JunitError;
import src.oracle.mds.external.conf.Configuration;
import src.oracle.mds.external.conf.SystemConstants;
import src.oracle.mds.external.htmlparser.HtmlErrorAndFailureLinkParser;
import src.oracle.mds.external.htmlparser.HtmlErrorAndFailureParser;
import src.oracle.mds.external.htmlparser.HtmlParser;
import src.oracle.mds.external.htmlparser.HtmlParser.ParserType;
import src.oracle.mds.external.values.JunitReportCategory;
import src.oracle.mds.external.values.MDSTestCategory;
import src.oracle.mds.external.values.MDSTestType;

public class LrgResultManager
{
	  private LrgResultManager()
	  {
		  
	  }
	  
      public static LrgResultManager getInstance()
      {
    	  return new LrgResultManager();
      }
      
      public Map<JunitReportCategory, List<JunitError>> 
                   getRemoteLrgFailuresAndErrors(MDSTestCategory category,
    		                                    String branch) throws Exception
      {
          return null;
      }
      
      public Map<JunitReportCategory, List<JunitError>> 
                     getLocalErrorsAndFailures(MDSTestType type)
                    		 throws Exception
       
      {
    	  // /ade/sshil_main-pmanager-refactoring/oracle/built/-junit-report/
    	  ///ade/sshil_main-pmanager-refactoring/oracle/built/mds-tests/junit-report/
    	  String viewRoot= SystemConstants.VIEW_ROOT;
    	  if (viewRoot == null
    			  || viewRoot.length() == 0)
    	  {
    		  throw new Exception("View root directory not found");
    	  }
    	  StringBuffer sb = new StringBuffer();
    	  sb.append(viewRoot);
    	  sb.append("/oracle/built");
    	  sb.append(MDSTestType.All.getResultPath());
    	  sb.append("/");
    	  sb.append(Configuration.ERROR_HTML_FILE);
    	  HtmlErrorAndFailureParser parser = HtmlErrorAndFailureParser.getInstance(ParserType.LOCAL);
    	  
    	  List<JunitError> errors = parser.getErrorsAndFailures(sb.toString());
    	  
    	  sb = new StringBuffer();
    	  sb.append(viewRoot);
    	  sb.append("/oracle/built");
    	  sb.append(MDSTestType.All.getResultPath());
    	  sb.append("/");
    	  sb.append(Configuration.FAILURE_HTML_FILE);
    	  
    	  List<JunitError> failures = parser.getErrorsAndFailures(sb.toString());
    	  Map<JunitReportCategory, List<JunitError>> map =
    			  new HashMap<JunitReportCategory, List<JunitError>>();
    	  map.put(JunitReportCategory.ERROR, errors);
    	  map.put(JunitReportCategory.FAILURE, failures);
    	  return map;
      }
      
      private String getRootPath()
      {
		  StringBuilder sb = new StringBuilder(Configuration.REMOTE_RESULT_SERVER_URL.length()+
				  Configuration.REMOTE_RESULT_PATH.length());
    	  sb.append(Configuration.REMOTE_RESULT_SERVER_URL);
    	  sb.append(Configuration.REMOTE_RESULT_PATH);
    	  sb.append("/");
    	  return sb.toString();
      }
      
      public static void main(String[] args) throws Exception
	  {
		 new LrgResultManager().getRemoteLrgFailuresAndErrors(MDSTestCategory.PDB_MT_WLS_ORACLE, "MAIN");
	  }
}
