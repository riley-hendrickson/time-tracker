package riley.dev.data;

public class Category 
{
    public static final String NONE = "no-category";

    private String category;

    public Category()
    {

    }

    public Category(String categoryName)
    {
        this.category = categoryName;
    }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    @Override
    public String toString()
    {
        return this.category;
    }
}
