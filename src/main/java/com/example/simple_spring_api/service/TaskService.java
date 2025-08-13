package com.example.simple_spring_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.simple_spring_api.model.Task;
import com.example.simple_spring_api.model.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Creates a new task or updates an existing one.
     * 
     * @param task the task to be saved
     * @return the saved task
     */
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Retrieves a task by its ID.
     * 
     * @param id the ID of the task
     * @return the task with the specified ID, or null if not found
     */
    public Task getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    /**
     * Deletes a task by its ID.
     * 
     * @param id the ID of the task to be deleted
     */
    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    /**
     * Retrieves all tasks.
     * 
     * @return a list of all tasks
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Updates an existing task.
     * 
     * @param task the task to be updated
     * @return the updated task, or null if the task does not exist
     */
    public Task updateTask(Task task) {
        return taskRepository.update(task);
    }
}