package riley.dev;

import riley.dev.data.Category;
import riley.dev.data.CurrentTasks;
import riley.dev.data.Task;
import riley.dev.util.ArgUtil;
import riley.dev.util.Args;

public class TimeTracker 
{
    

    public static void main(String[] args) 
    {
        // get current tasks from file
        CurrentTasks currentTasks = new CurrentTasks();
        Args parsedArguments = ArgUtil.parseArgs(args);

    }
}
