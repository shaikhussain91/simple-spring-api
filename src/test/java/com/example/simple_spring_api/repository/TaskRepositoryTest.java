package com.example.simple_spring_api.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.simple_spring_api.model.Task;
import com.example.simple_spring_api.model.repository.TaskRepository;

class TaskRepositoryTest {

    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository = new TaskRepository();
    }

    @Test
    void testSaveNewTask() {
        Task task = new Task();
        task.setName("Test Task");

        Task savedTask = taskRepository.save(task);

        assertNotNull(savedTask.getId(), "Task ID should not be null after saving");
        assertEquals("Test Task", savedTask.getName(), "Task name should match");
    }

    @Test
    void testFindById() {
        Task task = new Task();
        task.setName("Test Task");
        Task savedTask = taskRepository.save(task);

        Task foundTask = taskRepository.findById(savedTask.getId());

        assertNotNull(foundTask, "Task should be found by ID");
        assertEquals(savedTask.getId(), foundTask.getId(), "Found task ID should match saved task ID");
    }

    @Test
    void testDeleteById() {
        Task task = new Task();
        task.setName("Test Task");
        Task savedTask = taskRepository.save(task);

        taskRepository.deleteById(savedTask.getId());

        assertNull(taskRepository.findById(savedTask.getId()), "Task should be null after deletion");
    }

    @Test
    void testFindAll() {
        Task task1 = new Task();
        task1.setName("Task 1");
        Task task2 = new Task();
        task2.setName("Task 2");

        taskRepository.save(task1);
        taskRepository.save(task2);

        List<Task> tasks = taskRepository.findAll();

        assertEquals(2, tasks.size(), "There should be 2 tasks in the repository");
    }

    @Test
    void testUpdateExistingTask() {
        Task task = new Task();
        task.setName("Original Task");
        Task savedTask = taskRepository.save(task);

        savedTask.setName("Updated Task");
        Task updatedTask = taskRepository.update(savedTask);

        assertNotNull(updatedTask, "Updated task should not be null");
        assertEquals("Updated Task", updatedTask.getName(), "Task name should be updated");
    }

    @Test
    void testUpdateNonExistingTask() {
        Task task = new Task();
        task.setId(999L); // Non-existing ID
        task.setName("Non-existing Task");

        Task updatedTask = taskRepository.update(task);

        assertNull(updatedTask, "Updating a non-existing task should return null");
    }
}