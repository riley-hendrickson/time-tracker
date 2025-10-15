package riley.dev;

import riley.dev.data.Category;
import riley.dev.data.CurrentTasks;
import riley.dev.data.Task;

public class TimeTracker 
{
    

    public static void main(String[] args) 
    {
        if(args.length < 2)
        {
            Log.log("Incorrect number of arguments. See README file for details on acceptable arguments. Exiting...");
            System.exit(1);
        }
        String command = args[0];

        // get current tasks from file
        CurrentTasks currentTasks = new CurrentTasks();

        switch(command)
        {
            case "start":
                String taskName = args[1];
                String categoryName = args.length == 3? args[2] : Category.NONE;
                Task task = new Task(taskName, new Category(categoryName));
                currentTasks.startTask(task);
                break;

            case "stop":
                
                break;

            case "report":
                break;
        }
    }
}
