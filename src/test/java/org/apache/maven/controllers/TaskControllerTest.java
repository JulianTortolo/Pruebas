package org.apache.maven.controllers;

import org.apache.maven.domain.Tasks.Task;
import com.google.gson.Gson;
import org.apache.maven.model.TaskModel;
import org.apache.maven.services.TaskService;
import org.apache.maven.utils.TaskModelUtils;
import org.apache.maven.utils.TaskUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    @Qualifier("taskService")
    private TaskService taskService;

    @Test
    public void getTaskSuccessfully() throws Exception{
        BDDMockito.given(this.taskService.GetTaskById(anyInt())).willReturn(TaskModelUtils.getTaskModel());
        mvc.perform(MockMvcRequestBuilders.get("/api/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Tarea TST"))
                .andExpect(jsonPath("$.description").value("Tarea 1"))
                .andExpect(jsonPath("$.users.id").value(1))
                .andExpect(jsonPath("$.users.firstName").value("Juan"))
                .andExpect(jsonPath("$.users.lastName").value("Perez"))
                .andExpect(jsonPath("$.users.city.name").value("Maria Juana"));

    }

    @Test
    public void getNonExistentTask() throws Exception{
        BDDMockito.given(this.taskService.GetTaskById(anyInt())).willReturn(null);
        mvc.perform(MockMvcRequestBuilders.get("/api/tasks/130")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getTasksSuccessfully() throws Exception {
        BDDMockito.given(this.taskService.GetTasks(null)).willReturn(TaskModelUtils.getTasksModel());
        mvc.perform(MockMvcRequestBuilders.get("/api/tasks/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].name").value("Tarea TST 1"))
                .andExpect(jsonPath("$.[0].description").value("Tarea 1"))
                .andExpect(jsonPath("$.[0].users.id").value("1"))
                .andExpect(jsonPath("$.[0].users.firstName").value("Juan"))
                .andExpect(jsonPath("$.[0].users.lastName").value("Perez"))
                .andExpect(jsonPath("$.[0].users.city.id").value(1))
                .andExpect(jsonPath("$.[0].users.city.name").value("Maria Juana"))
                .andExpect(jsonPath("$.[1].id").value(2))
                .andExpect(jsonPath("$.[1].name").value("Tarea TST 2"))
                .andExpect(jsonPath("$.[1].description").value("Tarea 2"))
                .andExpect(jsonPath("$.[1].users.id").value(2))
                .andExpect(jsonPath("$.[1].users.firstName").value("Pablo"))
                .andExpect(jsonPath("$.[1].users.lastName").value("Picapiedra"))
                .andExpect(jsonPath("$.[1].users.city.id").value(2))
                .andExpect(jsonPath("$.[1].users.city.name").value("Rafaela"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTaskSuccessfully() throws Exception{
        BDDMockito.given(this.taskService.DeleteTask(anyInt())).willReturn(true);
        mvc.perform(MockMvcRequestBuilders.delete("/api/tasks/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTaskFailed() throws Exception{
        BDDMockito.given(this.taskService.DeleteTask(anyInt())).willReturn(false);
        mvc.perform(MockMvcRequestBuilders.delete("/api/tasks/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void saveTaskSuccessfully() throws Exception{
        Task task = TaskUtils.getTask();
        Gson jsonParser = new Gson();
        String requestBody = jsonParser.toJson(task);
        BDDMockito.given(this.taskService.CreateTask(any(Task.class))).willReturn(new TaskModel());
        mvc.perform(MockMvcRequestBuilders.post("/api/tasks/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON));

    }

    @Test
    public void saveTaskBadRequest() throws Exception{
        Task task = new Task();
        Gson jsonParser = new Gson();
        String requestBody = jsonParser.toJson(task);
        BDDMockito.given(this.taskService.CreateTask(any(Task.class))).willReturn(new TaskModel());
        mvc.perform(MockMvcRequestBuilders.post("/api/tasks/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON));
    }
}