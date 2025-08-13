package com.example.simple_spring_api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.simple_spring_api.model.Task;
import com.example.simple_spring_api.model.repository.TaskRepository;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTask() {
        Task task = new Task();
        task.setName("Test Task");

        when(taskRepository.save(task)).thenReturn(task);

        Task savedTask = taskService.saveTask(task);

        assertNotNull(savedTask, "Saved task should not be null");
        assertEquals("Test Task", savedTask.getName(), "Task name should match");
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testGetTaskById() {
        Task task = new Task();
        task.setId(1L);
        task.setName("Test Task");

        when(taskRepository.findById(1L)).thenReturn(task);

        Task foundTask = taskService.getTaskById(1L);

        assertNotNull(foundTask, "Task should be found");
        assertEquals(1L, foundTask.getId(), "Task ID should match");
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteTaskById() {
        Long taskId = 1L;

        doNothing().when(taskRepository).deleteById(taskId);

        taskService.deleteTaskById(taskId);

        verify(taskRepository, times(1)).deleteById(taskId);
    }

    @Test
    void testGetAllTasks() {
        Task task1 = new Task();
        task1.setName("Task 1");
        Task task2 = new Task();
        task2.setName("Task 2");

        when(taskRepository.findAll()).thenReturn(List.of(task1, task2));

        List<Task> tasks = taskService.getAllTasks();

        assertEquals(2, tasks.size(), "There should be 2 tasks");
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void testUpdateTask() {
        Task task = new Task();
        task.setId(1L);
        task.setName("Original Task");

        when(taskRepository.update(task)).thenReturn(task);

        Task updatedTask = taskService.updateTask(task);

        assertNotNull(updatedTask, "Updated task should not be null");
        assertEquals("Original Task", updatedTask.getName(), "Task name should match");
        verify(taskRepository, times(1)).update(task);
    }

    @Test
    void testUpdateNonExistingTask() {
        Task task = new Task();
        task.setId(999L);
        task.setName("Non-existing Task");

        when(taskRepository.update(task)).thenReturn(null);

        Task updatedTask = taskService.updateTask(task);

        assertNull(updatedTask, "Updating a non-existing task should return null");
        verify(taskRepository, times(1)).update(task);
    }
}