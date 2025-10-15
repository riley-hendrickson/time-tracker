package riley.dev.util;

import riley.dev.Log;

public class ArgUtil 
{
    public Args parseArgs(String[] args)
    {
        if(!validate(args))throw new RuntimeException("Invalid Arguments");
        Args arguments = new Args(args);
        return null;
    }

    public boolean validate(String[] args)
    {
        if(args.length < 2)
        {
            Log.log("Error: Not enough arguments. Refer to README for more information on valid arguments.");
            return false;
        }
        return true;
    }
}
