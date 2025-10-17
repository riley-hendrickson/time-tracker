package riley.dev;

import java.io.IOException;
import java.util.HashMap;

import riley.dev.data.Category;
import riley.dev.data.CurrentTasks;
import riley.dev.data.Task;
import riley.dev.util.ArgUtil;
import riley.dev.util.Args;
import riley.dev.util.Commands;
import riley.dev.util.FilesUtil;
@SuppressWarnings("unused")
public class TimeTracker 
{
    

    public static void main(String[] args) 
    {
        // get current tasks from file
        CurrentTasks currentTasks;
        try
        {
            currentTasks = FilesUtil.getSavedTasks();
        } catch (IOException e)
        {
            Log.log("Caught an exception while trying to receive saved tasks");
            currentTasks = new CurrentTasks();
        }
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
        try { FilesUtil.saveTasksToFile(currentTasks); } 
        catch (IOException e) { Log.log("Exception caught while trying to save current tasks to file");}
    }
}
