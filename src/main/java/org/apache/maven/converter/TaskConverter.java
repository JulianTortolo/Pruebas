package org.apache.maven.converter;

import org.apache.maven.domain.Tasks.Task;
import org.apache.maven.model.TaskModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("taskConverter")
public class TaskConverter {
    public List<TaskModel> convertList(List<Task> tasks){
        List<TaskModel> taskModels = new ArrayList<>();
        for(Task task :tasks){
            taskModels.add(new TaskModel(task));
        }

        return taskModels;
    }

    public TaskModel convertTask(Task task){
        TaskModel taskModel = new TaskModel(task);
        return taskModel;
    }
}
