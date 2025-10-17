package riley.dev.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
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
                .filter(array -> array.length == 4)
                .map(array -> new Task(
                        array[0],
                        new Category(array[1]),
                        array[2].equals("null") || array[2].isBlank() ? Duration.ZERO : Duration.parse(array[2]),
                        TaskStatus.valueOf(array[3])
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
