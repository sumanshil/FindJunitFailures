package src.oracle.mds.external.values;

public enum MDSTestType
{
	
	///ade/sshil_main1/oracle/built/mds-tests/junit-report/index.html
    MDS_TESTS {
		@Override
		public String getResultPath()
		{
			return "/mds-tests/junit-report";
		}
	},
    MDS_TESTS_J2EE {
		@Override
		public String getResultPath()
		{
			return "/mds-tests-j2ee/junit-report";			
		}
	},
    MDS_LCM {
		@Override
		public String getResultPath()
		{
			return "/mds-lcm/junit-report";
		}
	},
    MDS_IDE_TESTS {
		@Override
		public String getResultPath()
		{
            return "/mds-ide-tests/junit-report";
			
		}
	},
	
    All
    {
		@Override
		public String getResultPath()
		{
			return "/-junit-report";
		}
		
    };
    public abstract String getResultPath();
	

}
