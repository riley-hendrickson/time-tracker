package riley.dev.util;

import riley.dev.Log;
import riley.dev.data.Category;

public class ArgUtil 
{
    // intended index of arguments from command line, see README.md
    private static final int COMMAND_IDX = 0;
    private static final int TASK_NAME_IDX = 1;
    private static final int CATEGORY_IDX = 2;
    private static final int MIN_ARGS = 2;

    // helper method to parse the command line arguments and store/format them as needed
    public static Args parseArgs(String[] args)
    {
        if(!validate(args))throw new RuntimeException("Invalid Arguments");
        Args arguments = new Args(args);

        String command = args[COMMAND_IDX];
        switch(command)
        {
            case "start":
                arguments.setCommand(Commands.TASK_START);
                arguments.setTaskName(args[TASK_NAME_IDX]);
                arguments.setCategoryName(args.length == 3? args[CATEGORY_IDX] : Category.NONE);
                break;
            case "stop":
                arguments.setCommand(Commands.TASK_STOP);
                arguments.setTaskName(args[TASK_NAME_IDX]);
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
        // if there are less than two arguments the user has not provided sufficient arguments.
        if(args.length < MIN_ARGS)
        {
            Log.log("Error: Not enough arguments. Refer to README for more information on valid arguments.");
            return false;
        }
        return true;
    }
}
