package riley.dev.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import riley.dev.data.Category;
import riley.dev.data.CurrentTasks;
import riley.dev.data.Task;
import riley.dev.data.TaskStatus;

public class FilesUtil 
{
    // intended order of task fields in the saved output file, see Task.toCSVFormat() 
    private static final int TASK_NAME = 0;
    private static final int TASK_CATEGORY = 1;
    private static final int TASK_STARTING_TIME = 2;
    private static final int TASK_ELAPSED_TIME = 3;
    private static final int TASK_STATUS = 4;

    // output file to store saved tasks
    public static final String PATH = "task-info.csv";

    // method to retrieve previously saved tasks, throws an IOException if there was an error opening the file 
    public static CurrentTasks getSavedTasks() throws IOException
    {
        // if the file doesn't exist yet, create it
        Path filepath = Paths.get(PATH);
        if(Files.notExists(filepath))
        {
            Files.createFile(filepath);
        }
        Map<String, Task> taskMap = Files.lines(filepath)
                .map(line -> line.split(","))
                .filter(array -> array.length == Task.NUM_SAVED_FIELDS)
                .map(array -> new Task(
                        array[TASK_NAME],
                        new Category(array[TASK_CATEGORY]),
                        Instant.parse(array[TASK_STARTING_TIME]),
                        array[TASK_ELAPSED_TIME].equals("null") || array[TASK_ELAPSED_TIME].equals("00:00:00") ? Duration.ZERO : Duration.parse(array[TASK_ELAPSED_TIME]),
                        TaskStatus.valueOf(array[TASK_STATUS])
                ))
                .collect(Collectors.toMap(Task :: getTaskName, Function.identity()));
                return new CurrentTasks(taskMap);
    }
    // method to save the current state of the currentTasks map to an output csv file so we can retrieve that data in later iterations of the program
    public static void saveTasksToFile(CurrentTasks tasks) throws IOException
    {
        // if the file doesn't yet exist, create it
        Path filePath = Paths.get(PATH);
        if(Files.notExists(filePath))
        {
            Files.createFile(filePath);
        }
        List<String> lines = tasks.getCurrentTasks()
                .values()
                .stream()
                .map(Task :: toCSVFormat)
                .toList();
        Files.write(filePath, lines);
    }
}
