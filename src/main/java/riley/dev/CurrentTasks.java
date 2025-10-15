package riley.dev;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CurrentTasks 
{
    private Map<String, Task> currentTasks = new HashMap<>();

    public Map<String, Task> getCurrentTasks() { return this.currentTasks; }
    public void setCurrentTasks(Map<String, Task> currentTasks) { this.currentTasks = currentTasks; }

    public void startTask(Task task)
    {
        // only add the task if it doesn't already exist
        if(!currentTasks.containsKey(task.getTaskName()))
        {
            currentTasks.put(task.getTaskName(), task);
        }
        else Log.log("Task is already being tracked.");
    }

    public void completeTask(Task task)
    {
        Task currentTask = currentTasks.get(task.getTaskName());
        if(currentTask == null) 
        {
            Log.log("No Tasks found. Verify Task Name");
            return;
        }
        currentTask.setEndTime(LocalDateTime.now());
        currentTask.setStatus(TaskStatus.COMPLETE);
    }
}
