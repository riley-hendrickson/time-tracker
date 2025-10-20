import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import riley.dev.data.*;
import riley.dev.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.HashMap;

@SuppressWarnings("unused")
@DisplayName("Current Tasks Test Suite")
@Tag("main")
public class CurrentTasksTest 
{
    CurrentTasks currentTasks;
    Map<String, Duration> timesPerCategory;

    @BeforeEach
    void setup()
    {
        currentTasks = new CurrentTasks();
        timesPerCategory = new HashMap<>();
    }

    @Nested
    @DisplayName("Start Task Method Testing")
    class startTask
    {
        @Test
        @DisplayName("Start Task Method")
        void testStartTask()
        {
            // test to make sure the task is inserted into the map
            Instant currentTime = Instant.now();
            Task task = new Task("Breakfast", new Category("Eating"), currentTime, Duration.ZERO, TaskStatus.COMPLETE);
            currentTasks.startTask(task);
            assertTrue(currentTasks.getCurrentTasks().containsKey(task.getTaskName()), "currentTasks map should contain the task name of the task we are starting to track");
            assertTrue(currentTasks.getCurrentTasks().containsValue(task), "currentTasks map should contain the task we are starting to track");

            // verify the task status and startTime has been updated
            assertEquals(TaskStatus.IN_PROGRESS, task.getStatus(), "Task status should be in progress after a start task operation");
            assertNotEquals(currentTime, task.getCurrentStart(), "currentStartTime should be updated during a start task operation");

            // verify task status is restarted when we try to start a previously completed task
            currentTasks.stopTask(task.getTaskName());
            task.setCurrentStart(currentTime);
            currentTasks.startTask(task);
            assertEquals(TaskStatus.IN_PROGRESS, task.getStatus(), "Task status should be in progress after restarting an existing task");
            assertNotEquals(currentTime, task.getCurrentStart(), "currentStartTime needs to be updated when restarting an existing task");
        
            // verify that two different tasks with the same taskName are not allowed
            Task task2 = new Task("Breakfast", new Category("Not Eating"), currentTime, Duration.ZERO, TaskStatus.COMPLETE);
            currentTasks.startTask(task2);
            assertEquals(1, currentTasks.getCurrentTasks().size(), "There cannot be two tasks with the same task name. startTask should start the existing task with the given task name, and not begin tracking the newly passed task");
        }
    }

    @Nested
    @DisplayName("Testing Stop Task Command")
    class testStopTask
    {
        
    }

    @Test
    void testReportTask()
    {
        
    }

    @Test
    void testReportCategory()
    {
        
    }

    @Test
    void testToString()
    {
        // be sure to test all toString method implementations  
    }
}
