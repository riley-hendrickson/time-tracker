package riley.dev.util;

import riley.dev.Log;
import riley.dev.data.Category;

public class ArgUtil 
{
    public static Args parseArgs(String[] args)
    {
        if(!validate(args))throw new RuntimeException("Invalid Arguments");
        Args arguments = new Args(args);

        String command = args[0];
        switch(command)
        {
            case "start":
                arguments.setCommand(Commands.TASK_START);
                arguments.setTaskName(args[1]);
                arguments.setCategoryName(args.length == 3? args[2] : Category.NONE);
                break;
            case "stop":
                arguments.setCommand(Commands.TASK_STOP);
                arguments.setTaskName(args[1]);
                break;
            case "report":
                if(args[1].equals("tasks")) arguments.setCommand(Commands.REPORT_TASKS);
                else if(args[1].equals("category")) arguments.setCommand(Commands.REPORT_CATEGORIES);
                else Log.log("Invalid command. See README for more details...");
                break;
        }
        return arguments;
    }

    private static boolean validate(String[] args)
    {
        if(args.length < 2)
        {
            Log.log("Error: Not enough arguments. Refer to README for more information on valid arguments.");
            return false;
        }
        return true;
    }
}
