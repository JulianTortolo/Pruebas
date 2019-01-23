package org.apache.maven.utils;

import org.apache.maven.domain.Tasks.City;
import org.apache.maven.model.TaskModel;
import org.apache.maven.model.UserModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskModelUtils {

    public static TaskModel getTaskModel(){
        City city = new City(1, "Maria Juana");
        TaskModel taskModel = new TaskModel();
        taskModel.setId(1);
        taskModel.setName("Tarea TST");
        taskModel.setDescription("Tarea 1");
        taskModel.setCreateDate(new Date());
        taskModel.setUsers(new UserModel(1, "Juan", "Perez", city));

        return taskModel;
    }

    public static List<TaskModel> getTasksModel(){
        List<TaskModel> tasks = new ArrayList<>();
        City city1 = new City(1, "Maria Juana");
        TaskModel taskModel1 = new TaskModel();
        taskModel1.setId(1);
        taskModel1.setName("Tarea TST 1");
        taskModel1.setDescription("Tarea 1");
        taskModel1.setCreateDate(new Date());
        taskModel1.setUsers(new UserModel(1, "Juan", "Perez", city1));

        City city2 = new City(2, "Rafaela");
        TaskModel taskModel2 = new TaskModel();
        taskModel2.setId(2);
        taskModel2.setName("Tarea TST 2");
        taskModel2.setDescription("Tarea 2");
        taskModel2.setCreateDate(new Date());
        taskModel2.setUsers(new UserModel(2, "Pablo", "Picapiedra", city2));

        tasks.add(taskModel1);
        tasks.add(taskModel2);
        return tasks;
    }
}