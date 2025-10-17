package riley.dev.data;

import java.time.Duration;
import java.time.Instant;
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
        // stop any currently running tasks
        for(Task currentTask : currentTasks.values())
        {
            if(currentTask.getStatus() == TaskStatus.IN_PROGRESS) stopTask(currentTask.getTaskName());
        }
        // add the task to the map if it doesn't already exist
        if(!currentTasks.containsKey(task.getTaskName()))
        {
            currentTasks.put(task.getTaskName(), task);
        }
        // start the task
        task.setCurrentStart(Instant.now());
    }

    public void stopTask(String taskName)
    {
        Task currentTask = currentTasks.get(taskName);
        if(currentTask == null) 
        {
            Log.log("No Tasks found. Verify Task Name");
            return;
        }
        // increment totalElapsed of the current task and set its status to complete
        currentTask.setTotalElapsed(currentTask.getTotalElapsed().plus(Duration.between(currentTask.getCurrentStart(), Instant.now())));
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
