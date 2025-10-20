package riley.dev;

import java.io.IOException;

import riley.dev.data.*;
import riley.dev.util.*;

public class TimeTracker 
{
    public static void main(String[] args) 
    {
        // get current tasks from output file
        CurrentTasks currentTasks;
        try
        {
            currentTasks = FilesUtil.getSavedTasks();
        } catch (IOException e)
        {
            Log.log("Caught an exception while trying to read saved tasks");
            currentTasks = new CurrentTasks();
        }
        // parse input arguments
        Args parsedArguments = ArgUtil.parseArgs(args);
        switch(parsedArguments.getCommand())
        {
            case TASK_START ->
            {
                currentTasks.startTask(new Task(parsedArguments.getTaskName(), new Category(parsedArguments.getCategoryName())));
            }
            case TASK_STOP ->
            {
                currentTasks.stopTask(parsedArguments.getTaskName());
            }
            case REPORT_TASKS ->
            {
                currentTasks.reportTasks();
            }
            case REPORT_CATEGORIES ->
            {
                currentTasks.reportCategories();
            }
        }
        // save currently stored tasks to output file to be retrieved in later iterations
        try { FilesUtil.saveTasksToFile(currentTasks); } 
        catch (IOException e) { Log.log("Exception caught while trying to save current tasks to file");}
    }
}
