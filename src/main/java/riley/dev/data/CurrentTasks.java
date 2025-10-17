package riley.dev.data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import riley.dev.Log;

public class CurrentTasks 
{
    private Map<String, Task> currentTasks = new HashMap<>();

    public Map<String, Task> getCurrentTasks() { return this.currentTasks; }
    public void setCurrentTasks(Map<String, Task> currentTasks) { this.currentTasks = currentTasks; }

    public CurrentTasks()
    {
        
    }

    public CurrentTasks(Map<String, Task> taskMap)
    {
        this.currentTasks = taskMap;
    }

    public void startTask(Task task)
    {
        // only add the task if it doesn't already exist
        if(!currentTasks.containsKey(task.getTaskName()))
        {
            currentTasks.put(task.getTaskName(), task);
        }
        else Log.log("Task is already being tracked.");
    }

    public void completeTask(String taskName)
    {
        Task currentTask = currentTasks.get(taskName);
        if(currentTask == null) 
        {
            Log.log("No Tasks found. Verify Task Name");
            return;
        }
        currentTask.setEndTime(LocalDateTime.now());
        currentTask.setStatus(TaskStatus.COMPLETE);
    }

    @Override
    public String toString()
    {
        return currentTasks.entrySet().stream()
            .map(e -> e.getValue().toString())
            .collect(Collectors.joining("\n", "Current Tasks:\n", ""));
    }
}
