package org.apache.maven.service;

import org.apache.maven.converter.TaskConverter;
import org.apache.maven.domain.Tasks.Task;
import org.apache.maven.model.TaskModel;
import org.apache.maven.repository.TaskRepository;
import org.apache.maven.repository.UserRepository;
import org.apache.maven.services.TaskService;
import org.apache.maven.utils.TaskUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TaskServiceTest {

    TaskService service;

    @Mock
    TaskRepository mockTaskRepository;

    @Mock
    UserRepository mockUserRepository;

    @Mock
    TaskConverter mockTaskConverter;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        service = new TaskService();
        service.taskRepository = mockTaskRepository;
        service.userRepository = mockUserRepository;
        service.taskConverter = mockTaskConverter;
    }

    @Test
    public void findTaskByIdSuccess(){
        Mockito.when(mockTaskRepository.findOne(anyInt()))
                .thenReturn(TaskUtils.getTask());
        TaskModel taskTest = service.GetTaskById(1);
        Assert.assertEquals("Error de validacion. No se pudo encontrar la tarea con id 1", taskTest.getId(), 1 );

        verify(mockTaskRepository, times(0)).save(any(Task.class));
        verify(mockTaskRepository, times(1)).findOne(anyInt());
    }

    @Test
    public void findTaskListAndValidateLengthSuccess(){
        Mockito.when(mockTaskRepository.findAll()).thenReturn(TaskUtils.getTasks());
        List<TaskModel> tasksTests = service.GetTasks(null);
        assertThat(tasksTests.size()).isEqualTo(2);
        Assert.assertEquals("Error validating task[0].name", "Tarea 1", tasksTests.get(0).getName());
        Assert.assertEquals("Error validating task[1].name", "Tarea 2", tasksTests.get(1).getName());

        verify(mockTaskRepository, times(0)).save(any(Task.class));
        verify(mockTaskRepository, times(1)).findAll();
    }

    @Test
    public void findTasksListsNotDuplicatedSuccess(){
        Mockito.when(mockTaskRepository.findAll()).thenReturn(TaskUtils.getTasks());
        List<TaskModel> tasksTests = service.GetTasks(null);
        assertThat(tasksTests.size()).isEqualTo(2);
        Assert.assertEquals("Error validating task[0].name", "Tarea 1", tasksTests.get(0).getName());
        Assert.assertEquals("Error validating task[1].name", "Tarea 2", tasksTests.get(1).getName());

        verify(mockTaskRepository, times(0)).save(any(Task.class));
        verify(mockTaskRepository, times(1)).findAll();
    }
}
