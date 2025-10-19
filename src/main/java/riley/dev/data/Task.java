package riley.dev.data;

import java.time.Duration;
import java.time.Instant;

public class Task 
{
    private String taskName;
    private Category category;
    private Instant currentStart;
    private Duration totalElapsed;
    private TaskStatus status;

    public Task()
    {

    }
    // constructor to be used when creating new tasks
    public Task(String taskName, Category category)
    {
        this.taskName = taskName;
        this.category = category;
        this.totalElapsed = Duration.ZERO;
        this.status = TaskStatus.IN_PROGRESS;
    }
    // constructor to be used when retrieving previously saved tasks from output file
    public Task(String taskName, Category category, Instant currentStart, Duration totalElapsed, TaskStatus status)
    {
        this.taskName = taskName;
        this.category = category;
        this.currentStart = currentStart;
        this.totalElapsed = totalElapsed;
        this.status = status;
    }

    public String getTaskName() { return this.taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }

    public Category getCategory() { return this.category; }
    public void setCategory(Category category) { this.category = category; }

    public Instant getCurrentStart() { return currentStart; }
    public void setCurrentStart(Instant currentStart) { this.currentStart = currentStart; }

    public Duration getTotalElapsed() { return totalElapsed; }
    public void setTotalElapsed(Duration totalElapsed) { this.totalElapsed = totalElapsed; }

    public TaskStatus getStatus() { return this.status; }
    public void setStatus(TaskStatus status) { this.status = status; }

    // method to format duration in a more readable way than its toString method
    private String formatDuration(Duration d)
    {
        if(d == null || d.isZero()) return "00:00:00";
        long hours = d.toHours();
        long minutes = d.minusHours(hours).toMinutes();
        long seconds = d.minusHours(hours).minusMinutes(minutes).getSeconds();
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    // helper method to be called when the user wants a report of the tasks:
    // total elapsed time for a task is only computed when a task is stopped, but we also want to view elapsed time for in progress tasks when a report is requested,
    // so this method will be called when the user gives the report command
    public void updateElapsedTime()
    {
        this.totalElapsed = this.totalElapsed.plus(Duration.between(this.currentStart, Instant.now()));
        this.currentStart = Instant.now();
    }

    @Override
    public String toString()
    {
        return "{" + 
                "Task Name: " + this.taskName + "\n" + 
                "Category: " + this.category + "\n" + 
                "Total Elapsed Time:" + formatDuration(this.totalElapsed) + "\n" +
                "Status: " + this.status + 
                "}";
    }

    public String toCSVFormat()
    {
        return 
        this.taskName + "," +
        this.category + "," + 
        this.currentStart + "," +
        this.totalElapsed + "," +
        this.status;
    }
}
