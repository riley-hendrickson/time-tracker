package riley.dev.util;

public class Args 
{
    private Commands command;
    private String taskName;
    private String categoryName;

    public Args(String[] args)
    {

    }

    public Commands getCommand() 
    {
        return command;
    }
    public void setCommand(Commands command) 
    {
        this.command = command;
    }
    public String getTaskName() 
    {
        return taskName;
    }
    public void setTaskName(String taskName) 
    {
        this.taskName = taskName;
    }
    public String getCategoryName() 
    {
        return categoryName;
    }
    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }
}
