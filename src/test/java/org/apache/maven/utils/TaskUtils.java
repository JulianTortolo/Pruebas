package org.apache.maven.utils;

import org.apache.maven.domain.Tasks.Task;
import org.apache.maven.domain.Tasks.Users.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskUtils {

    public static Task getTask(){
        Task task = new Task();
        task.setId(1);
        task.setName("Tarea 1");
        task.setDescription("Tarea");
        task.setCreateDate(new Date());
        task.setUser(new User(1, "Juan"));
        return task;
    }

    public static List<Task> getTasks(){
        List<Task> tasks = new ArrayList<>();
        Task task1 = new Task();
        task1.setId(1);
        task1.setName("Tarea 1");
        task1.setDescription("Tarea");
        task1.setCreateDate(new Date());
        task1.setUser(new User(1, "Juan"));

        Task task2 = new Task();
        task2.setId(2);
        task2.setName("Tarea 2");
        task2.setDescription("Tarea test 2");
        task2.setCreateDate(new Date());
        task2.setUser(new User(2, "Julian"));

        tasks.add(task1);
        tasks.add(task2);
        return tasks;
    }
}
