package riley.dev;

import java.time.LocalDateTime;

public class Task 
{
    private String taskName;
    private Category category;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private TaskStatus status;

    public Task()
    {

    }

    public Task(String taskName, Category category)
    {
        this.taskName = taskName;
        this.category = category;
        this.startTime = LocalDateTime.now();
        this.status = TaskStatus.IN_PROGRESS;
    }

    public Task(String taskName, Category category, LocalDateTime startTime, LocalDateTime endTime)
    {
        this.taskName = taskName;
        this.category = category;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = TaskStatus.IN_PROGRESS;
    }

    public String getTaskName() { return this.taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }

    public Category getCategory() { return this.category; }
    public void setCategory(Category category) { this.category = category; }

    public LocalDateTime getStartTime() { return this.startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime;}

    public LocalDateTime getEndTime() { return this.endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public TaskStatus getStatus() { return this.status; }
    public void setStatus(TaskStatus status) { this.status = status; }
}
