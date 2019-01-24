package org.apache.maven.controllers;

import org.apache.maven.domain.Tasks.Task;
import org.apache.maven.model.TaskModel;
import org.apache.maven.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<List<TaskModel>> GetAllTasks(Pageable pageable){
        List<TaskModel> tasks = taskService.GetTasks(pageable);
        ResponseEntity<List<TaskModel>> response = tasks.size() > 0 ? new ResponseEntity<>(tasks, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskModel> GetTask(@PathVariable int id)throws Exception{
        TaskModel taskModel = taskService.GetTaskById(id);
        ResponseEntity<TaskModel> response = taskModel != null ? new ResponseEntity<>(taskModel, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return response;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TaskModel> CreateTask(@RequestBody Task task) throws Exception{
        TaskModel taskModel = taskService.CreateTask(task);
        ResponseEntity<TaskModel> response = taskModel != null ? new ResponseEntity<>(taskModel, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<TaskModel> UpdateTask(@PathVariable int id, @RequestBody Task task) throws Exception{
        task.setId(id);
        TaskModel taskModel = taskService.UpdateTask(task);
        ResponseEntity<TaskModel> response = taskModel != null ? new ResponseEntity<>(taskModel, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return response;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public ResponseEntity<Object> DeleteTask(@PathVariable int id) throws Exception{
        boolean success = taskService.DeleteTask(id);
        ResponseEntity<Object> response = success ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return response;
    }
}