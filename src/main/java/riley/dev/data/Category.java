package riley.dev.data;

public class Category 
{
    public static final String NONE = "no-category";

    private String category;
    private int totalTime;

    public Category()
    {

    }

    public Category(String categoryName)
    {
        this.category = categoryName;
    }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getTotalTime() { return totalTime; }
    public void setTotalTime(int totalTime) { this.totalTime = totalTime; }
}
