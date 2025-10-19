package riley.dev.data;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import riley.dev.Log;

public class CurrentTasks 
{
    private Map<String, Task> currentTasks = new HashMap<>();
    private Map<String, Duration> categories = new HashMap<>();

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
        // if the passed task already exists, grab that instance, otherwise add the new instance to the map
        if(currentTasks.containsKey(task.getTaskName()))
        {
            task = currentTasks.get(task.getTaskName());
        }
        else currentTasks.put(task.getTaskName(), task);
        // start the task
        task.setCurrentStart(Instant.now());
        task.setStatus(TaskStatus.IN_PROGRESS);
    }

    public void stopTask(String taskName)
    {
        if(currentTasks.get(taskName).getStatus() == TaskStatus.COMPLETE) return;
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

    public void reportTasks()
    { 
        for(Task currentTask : currentTasks.values()) 
        {
            if(currentTask.getStatus() == TaskStatus.IN_PROGRESS) currentTask.updateElapsedTime();
            System.out.println(currentTask);
        } 
    }

    public void reportCategories()
    {
        for(Task currentTask : currentTasks.values())
        {
            if(currentTask.getStatus() == TaskStatus.IN_PROGRESS) currentTask.updateElapsedTime();
            if(categories.containsKey(currentTask.getCategoryName()))
            {
                categories.put(currentTask.getCategoryName(), categories.get(currentTask.getCategoryName()).plus(currentTask.getTotalElapsed()));
            }
            else
            {
                categories.put(currentTask.getCategoryName(), currentTask.getTotalElapsed());
            }
        }
        for(Entry<String, Duration> e : categories.entrySet())
        {
            System.out.println("Total Time Spent on Tasks in " + e.getKey() + " Category: " + formatDuration(e.getValue()));
        }
    }

    // method to format duration in a more readable way than its toString method
    private String formatDuration(Duration d)
    {
        if(d == null || d.isZero()) return "00:00:00";
        long hours = d.toHours();
        long minutes = d.minusHours(hours).toMinutes();
        long seconds = d.minusHours(hours).minusMinutes(minutes).getSeconds();
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public String toString()
    {
        return currentTasks.entrySet().stream()
            .map(e -> e.getValue().toString())
            .collect(Collectors.joining("\n", "Current Tasks:\n", ""));
    }
}
