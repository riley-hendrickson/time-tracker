import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import riley.dev.util.*;
import riley.dev.data.*;

@DisplayName("File Utility Test Suite")
@Tag("main")
public class FilesUtilTest 
{
    @TempDir
    Path tempDir;

    private String originalUserDir;

    @BeforeEach
    void setUp() 
    {
        // make FilesUtil.PATH ("task-info.csv") land in the temp dir
        originalUserDir = System.getProperty("user.dir");
        System.setProperty("user.dir", tempDir.toString());
    }

    @AfterEach
    void tearDown() 
    {
        System.setProperty("user.dir", originalUserDir);
    }

    @Test
    @DisplayName("saveTasksToFile creates CSV and writes task data")
    void testSaveTasksToFile() throws IOException 
    {
        Task t = new Task("Breakfast", new Category("Eating"),
                Instant.parse("2025-01-01T10:00:00Z"),
                Duration.ofMinutes(20),
                TaskStatus.COMPLETE);
        Map<String, Task> map = new HashMap<>();
        map.put(t.getTaskName(), t);

        CurrentTasks currentTasks = new CurrentTasks(map);

        // Act
        FilesUtil.saveTasksToFile(currentTasks);

        // Assert
        Path csv = Paths.get(FilesUtil.PATH);
        assertTrue(Files.exists(csv), "task-info.csv should be created");
        String content = Files.readString(csv);
        assertTrue(content.contains("Breakfast"), "CSV should contain task name");
        assertTrue(content.contains("Eating"), "CSV should contain category");
    }

    @Test
    @DisplayName("getSavedTasks creates file if missing and loads tasks correctly")
    void testGetSavedTasks() throws IOException 
    {
        // Arrange: create a CSV file manually
        Path csv = Paths.get(FilesUtil.PATH);
        if (Files.notExists(csv)) Files.createFile(csv);
        String line = "Workout,Fitness,2025-01-01T08:00:00Z,PT30M,COMPLETE";
        Files.writeString(csv, line);

        // Act
        CurrentTasks loaded = FilesUtil.getSavedTasks();

        // Assert
        assertNotNull(loaded);
        assertTrue(loaded.getCurrentTasks().containsKey("Workout"));
        Task task = loaded.getCurrentTasks().get("Workout");
        assertEquals("Fitness", task.getCategoryName());
        assertEquals(Duration.ofMinutes(30), task.getTotalElapsed());
        assertEquals(TaskStatus.COMPLETE, task.getStatus());
    }
}
