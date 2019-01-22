package org.apache.maven.controllers;

import org.apache.maven.domain.Tasks.Task;
import org.apache.maven.model.TaskModel;
import org.apache.maven.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    @Qualifier("taskService")
    TaskService taskService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskModel> GetAllTasks(){
        List<TaskModel> tasks = taskService.GetTasks();
        return tasks;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskModel> GetTask(@PathVariable int id)throws Exception{
        ResponseEntity<TaskModel> response;
        TaskModel taskModel = taskService.GetTaskById(id);
        if(taskModel != null && taskModel.getId() > 0){
            response = new ResponseEntity<>(taskModel, HttpStatus.OK);
        }
        else{
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> CreateTask(@RequestBody Task task) throws Exception{
        ResponseEntity<Object> response;

        boolean success = taskService.CreateTask(task);
        if(success){
            response = new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> UpdateTask(@PathVariable int id, @RequestBody Task task) throws Exception{
        ResponseEntity<Object> response;
        task.setId(id);
        boolean success = taskService.UpdateTask(task);
        if(success){
            response = new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public ResponseEntity<Object> DeleteTask(@PathVariable int id) throws Exception{
        ResponseEntity<Object> response;
        boolean success = taskService.DeleteTask(id);
        if(success){
            response = new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return response;
    }
}