package org.apache.maven.services;

import org.apache.maven.converter.TaskConverter;
import org.apache.maven.domain.Tasks.Task;
import org.apache.maven.model.TaskModel;
import org.apache.maven.model.UserModel;
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

    public boolean CreateTask(Task task) {
        boolean success;

        try {
            if(!userRepository.exists(task.getUser().getId())){
                userRepository.save(task.getUser());
            }

            taskRepository.save(task);
            success = true;
        }
        catch (Exception e){
            success = false;
        }

        return success;
    }

    public List<TaskModel> GetTasks(){
        return mapTasksToTasksModel(taskRepository.findAll());
    }

    public TaskModel GetTaskById(int taskId){
        Task task = taskRepository.findOne(taskId);
        return mapTaskToTaskModel(task);
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

    public boolean UpdateTask(Task task){
        boolean success;
        try{
            Task taskSaved = taskRepository.findOne(task.getId());
            if(task.getId() > 0) {
                taskSaved.setCreateDate(task.getCreateDate());
                taskSaved.setDescription(task.getDescription());
                taskSaved.setName(task.getName());
                taskSaved.setUser(task.getUser());
                taskRepository.save(taskSaved);
            }

            success = true;
        }
        catch(Exception e){
            success = false;
        }

        return success;
    }

    public TaskModel mapTaskToTaskModel(Task task){
        TaskModel taskModel = new TaskModel();
        if(task != null) {
            taskModel.setId(task.getId());
            taskModel.setDescription(task.getDescription());
            taskModel.setName(task.getName());
            taskModel.setUsers(new UserModel(task.getUser().getId(), task.getUser().getname()));
            taskModel.setCreateDate(task.getCreateDate());
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

