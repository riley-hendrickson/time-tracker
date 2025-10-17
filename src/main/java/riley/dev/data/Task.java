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
    public Task(String taskName, Category category, Duration totalElapsed, TaskStatus status)
    {
        this.taskName = taskName;
        this.category = category;
        this.currentStart = Instant.now();
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
        this.totalElapsed + "," +
        this.status;
    }
}
