package src.oracle.mds.external.userinput;

import java.util.ArrayList;
import java.util.List;

import src.oracle.mds.external.util.LinuxCommandUtil;

public class UserInputHandler
{
    public enum MODE_IMPLICIT 
    {
    	LABEL_SERIES 
    	{
			@Override
			public String getDescription()
			{
				return "Label series of MDS release(.e.g JDEVADF_MAIN_GENERIC)";
			}

			@Override
			public String getUserTag()
			{				
				return "-label-series";
			}

			@Override
			protected boolean isMandatory()
			{
				return true;
			}

		},
    	LABEL
    	{
			@Override
			public String getDescription()
			{
				return "Label value of a Label series (.e.g. 141215.0331.S), If this is not specified latest label will be used";
			}

			@Override
			public String getUserTag()
			{
				return "-label";
			}

			@Override
			protected boolean isMandatory()
			{
				return false;
			}

		},
		OUTPUT_DIR
		{
			@Override
			public String getValue()
			{
				String value = super.getValue();
				if (value == null)
				{
					value = LinuxCommandUtil.getCurrentWorkingDir();
				}
				return value;
			}
			
			@Override
			public String getDescription()
			{								
				return "Output directory of result."
						+ " If this argument is missing,"
						+ " output will be generated in current directory.";
			}

			@Override
			public String getUserTag()
			{				
				return "-output-dir";
			}

			@Override
			protected boolean isMandatory()
			{				
				return false;
			}			
		},
    	TEST_PLATFORM 
    	{
            
			@Override
			public String getDescription()
			{								
				return "MDS test platform (.e.g. wls_oracle_PDB[TENANT1])";
			}

			@Override
			public String getUserTag()
			{				
				return "-test-platform";
			}

			@Override
			protected boolean isMandatory()
			{				
				return true;
			}

		};    	
		private String value;

		public void setValue(String value)
		{
			this.value = value;
		}


		public String getValue()
		{
			return value;
		}
		
    	public abstract String getDescription();
    	public abstract String getUserTag();
    	protected abstract boolean isMandatory();
    	
    	protected String getMandatoryString()
    	{
    		return isMandatory() ? "(Mandatory)" : "(Optional)";
    	}
    	
    	protected String getFinalDescription()
    	{
    		return getUserTag()+" <value> "+":"+getMandatoryString()+getDescription();
    	}
    	
    	public static void processUserInput(String[] userInput)
    	{
    		for(int i = 0 ; i < userInput.length ; i = i+2)
    		{
    			for (MODE_IMPLICIT thisEnum : values())
    			{
    				if (i+1 < userInput.length 
    						&& thisEnum.getUserTag().equals(userInput[i]))
    				{
    					if (i+1 < userInput.length)
    					{
    					    thisEnum.setValue(userInput[i+1]);
    					}
    				}
    			}
    		}
    	}
    	
    	public static List<String> validateUserInput()
    	{    		
    		List<String> errorMessage = new ArrayList<String>();
    		for(MODE_IMPLICIT thisEnum : values())
    		{
    			if (thisEnum.isMandatory() 
    					&& thisEnum.getValue() == null)
    			{
    				errorMessage.add(thisEnum.getFinalDescription());
    			}
    		}
    		return errorMessage;
    	}
    	
    	public static String getModeDescription()
    	{
    		StringBuffer sb = new StringBuffer();
    		sb.append("Implicit Mode\n");
    		for(MODE_IMPLICIT thisEnum : values())
    		{
    			sb.append(thisEnum.getFinalDescription()+"\n");
    		}
    		return sb.toString();
    	}
    }
    
    
    public enum MODE_EXPLICIT
    {
    	TEST_RESULT_SOURCE
    	{

			@Override
			public String getDescription()
			{
				return "Source location for Junit test results. This should contain alltests-errors.html and alltests-fails.html.\n"
						+" The errors and failures in source will be compared against destination erros and failures.\n"
						+" Any error and failure that is not found in source will be considered as new and will be reported.";
			}

			@Override
			public String getUserTag()
			{				
				return "-source";
			}

			@Override
			protected boolean isMandatory()
			{
				return true;
			}
    		
    	},
    	TEST_RESULT_DESTINATION
    	{

			@Override
			public String getDescription()
			{
				return "Destination location for Junit test results. This should contain alltests-errors.html and alltests-fails.html.";
			}

			@Override
			public String getUserTag()
			{
				return "-destination";
			}

			@Override
			protected boolean isMandatory()
			{
				return true;
			}
    		
    	},
		OUTPUT_DIR
		{
			@Override
			public String getValue()
			{
				String value = super.getValue();
				if (value == null)
				{
					value = LinuxCommandUtil.getCurrentWorkingDir();
				}
				return value;
			}
			
			@Override
			public String getDescription()
			{								
				return "Output directory of result."
						+ " If this argument is missing,"
						+ " output will be generated in current directory.";
			}

			@Override
			public String getUserTag()
			{				
				return "-output-dir";
			}

			@Override
			protected boolean isMandatory()
			{				
				return false;
			}			
		};
    	
		private String value;

		public void setValue(String value)
		{
			this.value = value;
		}


		public String getValue()
		{
			return value;
		}

    	public abstract String getDescription();
    	public abstract String getUserTag();
    	protected abstract boolean isMandatory();
    	
    	protected String getMandatoryString()
    	{
    		return isMandatory() ? "(Mandatory)" : "(Optional)";
    	}
    	
    	protected String getFinalDescription()
    	{
    		return getUserTag()+" <value> "+":"+getMandatoryString()+getDescription();
    	}
    	
    	public void processUserInput(String[] userInput)
    	{
    		for(int i = 0 ; i < userInput.length ; i = i+2 )
    		{
    			for (MODE_EXPLICIT thisEnum : values())
    			{
    				if (thisEnum.getUserTag().equals(userInput[i]))
    				{
    					if (i+1 < userInput.length)
    					{
    					    thisEnum.setValue(userInput[i+1]);
    					}
    				}
    			}
    		}
    	}
    	
    	public static List<String> validateUserInput()
    	{    		
    		List<String> errorMessage = new ArrayList<String>();
    		for(MODE_EXPLICIT thisEnum : values())
    		{
    			if (thisEnum.isMandatory() 
    				&& thisEnum.getValue() == null)
    			{
    				errorMessage.add(thisEnum.getFinalDescription());
    			}
    		}
    		return errorMessage;
    	}
    	
    	public static String getModeDescription()
    	{
    		StringBuffer sb = new StringBuffer();
    		sb.append("Explicit Mode\n");
    		for(MODE_EXPLICIT thisEnum : values())
    		{
    			sb.append(thisEnum.getFinalDescription()+"\n");
    		}
    		return sb.toString();
    	}
    	
    }
    
    public enum DEFAULT
    {
        
    }
    public static void main(String[] args)
	{
		UserInputHandler.MODE_IMPLICIT. processUserInput(new String[]{"-label-series","BLAHBLAH"});
		System.out.println(UserInputHandler.MODE_IMPLICIT.validateUserInput());
	}
}
