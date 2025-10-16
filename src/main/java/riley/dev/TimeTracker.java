package riley.dev;

import riley.dev.data.Category;
import riley.dev.data.CurrentTasks;
import riley.dev.data.Task;
import riley.dev.util.ArgUtil;
import riley.dev.util.Args;
import riley.dev.util.Commands;
@SuppressWarnings("unused")
public class TimeTracker 
{
    

    public static void main(String[] args) 
    {
        // get current tasks from file
        CurrentTasks currentTasks = new CurrentTasks();
        Args parsedArguments = ArgUtil.parseArgs(args);
        switch(parsedArguments.getCommand())
        {
            case TASK_START ->
            {
                currentTasks.startTask(new Task(parsedArguments.getTaskName(), new Category(parsedArguments.getCategoryName())));
            }
            case TASK_STOP ->
            {
                currentTasks.completeTask(parsedArguments.getTaskName());
            }
            case REPORT_TASKS ->
            {

            }
            case REPORT_CATEGORIES ->
            {

            }
        }
    }
}
