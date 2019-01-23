package org.apache.maven.services;

import org.apache.maven.clients.UsersAPI;
import org.apache.maven.converter.TaskConverter;
import org.apache.maven.domain.Tasks.Task;
import org.apache.maven.model.TaskModel;
import org.apache.maven.repository.TaskRepository;
import org.apache.maven.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service("taskService")
public class TaskService{

    @Autowired
    public TaskRepository taskRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public TaskConverter taskConverter;

    public TaskModel CreateTask(Task task) {
        TaskModel taskModel;
        try {
            if (!userRepository.exists(task.getUser().getId())) {
                userRepository.save(task.getUser());
            }

            taskModel = taskConverter.convertTask(taskRepository.save(task));
        }
        catch (Exception e){
            taskModel = null;
        }

        return taskModel;
    }

    public List<TaskModel> GetTasks(){
        return mapTasksToTasksModel(taskRepository.findAll());
    }

    public TaskModel GetTaskById(int taskId){
        TaskModel taskModel;
        try{
            Task task = taskRepository.findOne(taskId);
            taskModel = mapTaskToTaskModel(task);
        }
        catch (Exception e){
            taskModel = null;
        }

        return taskModel;
    }

    public boolean DeleteTask(int taskId){
        boolean success;
        try {
            taskRepository.delete(taskId);
            success = true  ;
        }
        catch (Exception e){
            success = false;
        }

        return success;
    }

    public TaskModel UpdateTask(Task task){
        TaskModel taskModel = new TaskModel();
        try{
            Task taskSaved = taskRepository.findOne(task.getId());
            if(task.getId() > 0) {
                taskSaved.setCreateDate(task.getCreateDate());
                taskSaved.setDescription(task.getDescription());
                taskSaved.setName(task.getName());
                taskSaved.setUser(task.getUser());
                taskRepository.save(taskSaved);
            }

            taskModel = taskConverter.convertTask(task);
        }
        catch(Exception e){
            taskModel = null;
        }

        return taskModel;
    }

    public TaskModel mapTaskToTaskModel(Task task){
        TaskModel taskModel = new TaskModel();
        if(task != null) {
            taskModel.setId(task.getId());
            taskModel.setDescription(task.getDescription());
            taskModel.setName(task.getName());
            taskModel.setCreateDate(task.getCreateDate());
            taskModel.setUsers(UsersAPI.GetUser(task.getUser().getId()));
        }
        return  taskModel;
    }

    public List<TaskModel> mapTasksToTasksModel(List<Task> tasks){
        List<TaskModel> taskModels = new ArrayList<>();
        for(Task task :tasks){
            taskModels.add(new TaskModel(task));
        }

        return taskModels;
    }
}

