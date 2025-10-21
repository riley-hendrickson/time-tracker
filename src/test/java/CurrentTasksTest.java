import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import riley.dev.data.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.HashMap;

@DisplayName("Current Tasks Test Suite")
@Tag("main")
public class CurrentTasksTest 
{
    CurrentTasks currentTasks;
    Map<String, Duration> timesPerCategory;
    private Clock fixedClock;

    @BeforeEach
    void setup()
    {
        currentTasks = new CurrentTasks();
        timesPerCategory = new HashMap<>();
        fixedClock = Clock.fixed(Instant.parse("2026-01-01T00:00:00Z"), ZoneOffset.UTC);
    }
    // nesting this class so further tests can be added in a readable way
    @Nested
    @DisplayName("Start and Stop Task Tests")
    class startAndStop
    {
        Instant before;
        Task task;

        @BeforeEach
        void init()
        {
            before = Instant.now(fixedClock);
            task = new Task("Breakfast", new Category("Eating"), before, Duration.ZERO, TaskStatus.COMPLETE);
            currentTasks.startTask(task);
        }

        @Test
        @DisplayName("Should Create and Start New Task")
        void testTaskInsertion()
        {
            // test to make sure the task is inserted into the map
            assertTrue(currentTasks.getCurrentTasks().containsKey(task.getTaskName()), "currentTasks map should contain the task name of the task we are starting to track");
            assertTrue(currentTasks.getCurrentTasks().containsValue(task), "currentTasks map should contain the task we are starting to track");
        }

        @Test
        @DisplayName("Verifying Task Status and Start Time have been updated")
        void testUpdatedTaskInfo()
        {
            // verify the task status and startTime has been updated
            assertEquals(TaskStatus.IN_PROGRESS, task.getStatus(), "Task status should be in progress after a start task operation");
            assertNotEquals(before, task.getCurrentStart(), "currentStartTime should be updated during a start task operation");
        }

        @Test
        @DisplayName("Verifying Task is properly stopped")
        void testSimpleStop()
        {
            currentTasks.stopTask(task.getTaskName());
            System.out.println(task);
            System.out.println("Task start time: " + task.getCurrentStart());
            // assertNotEquals(Duration.ZERO, task.getTotalElapsed(), "Duration needs to be updated when a task is stopped");
            assertEquals(TaskStatus.COMPLETE, task.getStatus(), "Task status needs to be updated when a task is stopped");
        }

        @Test
        @DisplayName("Verifying existing tasks are properly restarted")
        void testTaskRestart()
        {
            // verify task status is restarted when we try to start a previously completed task
            currentTasks.stopTask(task.getTaskName());
            currentTasks.startTask(task);
            assertEquals(TaskStatus.IN_PROGRESS, task.getStatus(), "Task status should be in progress after restarting an existing task");
            assertNotEquals(before, task.getCurrentStart(), "currentStartTime needs to be updated when restarting an existing task");
        }
    
        @Test
        @DisplayName("Verify enforcement of different task names")
        void testTaskNameEnforcement()
        {
            // verify that two different tasks with the same taskName are not allowed
            Task task2 = new Task("Breakfast", new Category("Not Eating"), before, Duration.ZERO, TaskStatus.COMPLETE);
            currentTasks.startTask(task2);
            assertEquals(1, currentTasks.getCurrentTasks().size(), "There cannot be two tasks with the same task name. startTask should start the existing task with the given task name, and not begin tracking the newly passed task");
        }
    }
}
