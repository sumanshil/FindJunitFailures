package src.oracle.mds.external.values;

public enum MDSTestCategory
{
    CHECK_T_T1_WLS_ORACLE 
    {
		@Override
		public String getTestPath()
		{
			
			return "wls_oracle_PDB[TENANT1]";
		}
	},
    PDB_MT_WLS_ORACLE 
    {
		@Override
		public String getTestPath()
		{
			return "wls_oracle_PDB";
		}
	},
    
	MT_UUID_2
	{
		@Override
		public String getTestPath()
		{			
			return "wls_oracle_MT_UUID_2";
		}
		
	};
    public abstract String getTestPath();
}
