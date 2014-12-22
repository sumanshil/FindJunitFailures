package src.oracle.mds.external.userinput;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public abstract class UserInputProcessor
{
    public enum MODE
    {
        IMPLICIT_TARGET,
        EXPLICIT_TARGET
    }
    
    public static UserInputProcessor getInstance(MODE mode)
    {
        switch (mode)
        {
            case IMPLICIT_TARGET:            
                return new ImplicitTargetInputProcessor();

            case EXPLICIT_TARGET:            
                return new ExplicitTargetInputProcessor();

            default:
                break;
        }
        throw new IllegalArgumentException();
    }
    protected abstract UserInput[] getUserInputs();
    protected String getMandatoryString(UserInput userInput)
    {
        return userInput.isMandatory() ? "(Mandatory)" : "(Optional)";
    }
    
    protected String getFinalDescription(UserInput userInput)
    {
        return userInput.getUserTag()+" <value> "+":"
               +getMandatoryString(userInput)
               +userInput.getDescription();
    }
    
    public void processUserInput(String[] userInput)
    {
        for(int i = 0 ; i < userInput.length ; i = i+2 )
        {
            for (UserInput thisInput : getUserInputs())
            {
                if (thisInput.getUserTag().equals(userInput[i]))
                {
                    if (i+1 < userInput.length)
                    {
                        thisInput.setValue(userInput[i+1]);
                    }
                }
            }
        }
    }
    
    public List<String> validateUserInput()
    {           
        List<String> errorMessage = new ArrayList<String>();
        for(UserInput thisInput : getUserInputs())
        {
            if (thisInput.isMandatory() 
                && thisInput.getValue() == null)
            {
                errorMessage.add(getFinalDescription(thisInput));
            }
        }
        return errorMessage;
    }
    
    public String getModeDescription()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("Explicit Mode\n");
        for(UserInput thisInput : getUserInputs())
        {
            sb.append(getFinalDescription(thisInput)+"\n");
        }
        return sb.toString();
    }
    
    public static String checkIfPropertyFileArgumentPresent(String[] userInput)
    {
        for(int i = 0 ; i < userInput.length ; i++)
        {
            if (new PropertyFile().getUserTag().equals(userInput[i]))
            {
                return userInput[i+1];
            }
        }
        return null;
    }
    
    public static String[] getPropertiesAsArray(String path)
                           throws Exception
    {
        Properties prop = new Properties();
        InputStream input = null;
     
        try
        {
            input = new FileInputStream(path);     
            // load a properties file
            prop.load(input);
            int size = prop.size();
            String[] retVal = new String[size*2];
            int index = 0;
            for(Object key : prop.keySet())
            {
                String value = prop.getProperty((String)key);
                retVal[index++] = (String)key;
                retVal[index++] = value;
            }
            return retVal;
        }
        finally
        {
            if (input != null) 
            {
                input.close();
            } 
        }
    }
    
}
