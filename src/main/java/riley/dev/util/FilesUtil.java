package riley.dev.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import riley.dev.data.Category;
import riley.dev.data.CurrentTasks;
import riley.dev.data.Task;
import riley.dev.data.TaskStatus;

public class FilesUtil 
{
    public static final String PATH = "task-info.csv";

    public static CurrentTasks getSavedTasks() throws IOException
    {
        Path filepath = Paths.get(PATH);
        if(Files.notExists(filepath))
        {
            Files.createFile(filepath);
        }
        Map<String, Task> taskMap = Files.lines(filepath)
                .map(line -> line.split(","))
                .filter(array -> array.length == 5)
                .map(array -> new Task(
                        array[0],
                        new Category(array[1]),
                        LocalDateTime.parse(array[2]),
                        array[3].equals("null") || array[3].isBlank() ? null : LocalDateTime.parse(array[3]),
                        TaskStatus.valueOf(array[4])
                ))
                .collect(Collectors.toMap(Task :: getTaskName, Function.identity()));
                return new CurrentTasks(taskMap);
    }

    public static void saveTasksToFile(CurrentTasks tasks) throws IOException
    {
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
