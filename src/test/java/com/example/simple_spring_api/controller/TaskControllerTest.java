package com.example.simple_spring_api.controller;

import com.example.simple_spring_api.dto.TaskDto;
import com.example.simple_spring_api.model.Task;
import com.example.simple_spring_api.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for TaskController using Spring Boot's @WebMvcTest and MockMvc.
 * This test class verifies the behavior of REST endpoints without starting the full application context.
 */
@WebMvcTest(TaskController.class) // Loads only TaskController and MVC-related beans for testing
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc; // MockMvc allows simulating HTTP requests without running a server

    @MockitoBean // Creates a Mockito mock of TaskService and injects it into TaskController
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper; // Used to convert Java objects to JSON and vice versa

    /**
     * Test: POST /api/v1/tasks
     * Goal: Ensure that creating a new task returns HTTP 201 and the correct response body.
     */
    @Test
    void testCreateTask() throws Exception {
        // Input DTO representing the task to be created
        TaskDto dto = new TaskDto(1L, "Test Task", "Description", false);

        // Mocked Task object returned by the service after saving
        Task task = Task.builder()
                .id(1L)
                .name("Test Task")
                .description("Description")
                .completed(false)
                .build();

        // Define service mock behavior
        Mockito.when(taskService.saveTask(Mockito.any(Task.class))).thenReturn(task);

        // Perform POST request and validate response
        mockMvc.perform(post("/api/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON) // Sending JSON data
                .content(objectMapper.writeValueAsString(dto))) // Convert DTO to JSON string
                .andExpect(status().isCreated()) // Expect HTTP 201 Created
                .andExpect(jsonPath("$.message").value("Task created successfully")) // Verify response message
                .andExpect(jsonPath("$.data.name").value("Test Task")); // Verify returned task name
    }

    /**
     * Test: GET /api/v1/tasks/{taskId}
     * Goal: Ensure that fetching a task by ID returns HTTP 200 and the correct task data.
     */
    @Test
    void testGetTaskById() throws Exception {
        // Mocked Task object returned by the service
        Task task = Task.builder()
                .id(1L)
                .name("Test Task")
                .description("Description")
                .completed(false)
                .build();

        // Define service mock behavior
        Mockito.when(taskService.getTaskById(1L)).thenReturn(task);

        // Perform GET request and validate response
        mockMvc.perform(get("/api/v1/tasks/{taskId}", 1))
                .andExpect(status().isOk()) // Expect HTTP 200 OK
                .andExpect(jsonPath("$.message").value("Task fetched successfully")) // Verify response message
                .andExpect(jsonPath("$.data.name").value("Test Task")); // Verify returned task name
    }

    /**
     * Test: GET /api/v1/tasks
     * Goal: Ensure that fetching all tasks returns HTTP 200 and the correct list size.
     */
    @Test
    void testGetAllTasks() throws Exception {
        // Mocked list of tasks returned by the service
        List<Task> tasks = List.of(
                Task.builder().id(1L).name("Task 1").description("Desc 1").completed(false).build(),
                Task.builder().id(2L).name("Task 2").description("Desc 2").completed(true).build()
        );

        // Define service mock behavior
        Mockito.when(taskService.getAllTasks()).thenReturn(tasks);

        // Perform GET request and validate response
        mockMvc.perform(get("/api/v1/tasks"))
                .andExpect(status().isOk()) // Expect HTTP 200 OK
                .andExpect(jsonPath("$.message").value("Task fetched successfully")) // Verify response message
                .andExpect(jsonPath("$.data.length()").value(2)); // Verify returned list size
    }
}
