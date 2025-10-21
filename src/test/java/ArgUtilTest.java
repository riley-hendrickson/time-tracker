import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import riley.dev.util.Commands;
import riley.dev.util.Args;
import riley.dev.util.ArgUtil;

@DisplayName("Command Line Argument Parsing Utility Test Suite")
@Tag("main")
public class ArgUtilTest 
{
    Args parsedArgs;
    String [] args;

    @BeforeEach
    void setup()
    {

    }

    @Test
    @DisplayName("Testing Arguments are correctly parsed with valid input")
    void testValidInput()
    {
        args = new String[]{"start", "eating breakfast", "food"};
        parsedArgs = ArgUtil.parseArgs(args);
        assertEquals(Commands.TASK_START, parsedArgs.getCommand(), "Command is not properly parsed");
        assertEquals("eating breakfast", parsedArgs.getTaskName(), "Task Name is not properly parsed");
        assertEquals("food", parsedArgs.getCategoryName(), "Category is not properly parsed");
    }

    @Test
    @DisplayName("Testing how invalid input is handled")
    void validateInput()
    {
        args = new String[]{"start"};
        assertThrows(RuntimeException.class, () -> ArgUtil.parseArgs(args), "parseArgs method should throw a runtime exception for invalid arguments");
    }
}
