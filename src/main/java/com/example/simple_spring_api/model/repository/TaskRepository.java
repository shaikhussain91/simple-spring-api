package com.example.simple_spring_api.model.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.simple_spring_api.model.Task;

@Repository
public class TaskRepository {

    // This class can be used to interact with the database or any data source
    // for now we will use a map to simulate a database

    private Map<Long, Task> tasks = new HashMap<>();

    /**
     * Generates a new unique ID for the task.
     * Finds the largest ID in the map and returns id + 1, or returns 1 if the map
     * is empty.
     * 
     * @return a new unique ID for the task.
     */
    private Long generateNewId() {
        return tasks.isEmpty() ? 1L : tasks.keySet().stream().max(Long::compare).orElse(0L) + 1;
    }

    /**
     * * Saves a task to the repository.
     * * If the task does not have an ID, a new ID is generated.
     * * The task is then added to the map with its ID as the key.
     * 
     * @param task the task to be saved
     * @return the saved task with its ID set
     */
    public Task save(Task task) {
        if (task.getId() == null) {
            task.setId(generateNewId());
        }
        
        tasks.put(task.getId(), task);
        return task;
    }

    /**
     * Retrieves a task by its ID from the repository.
     * 
     * @param id the ID of the task to be retrieved
     * @return the task with the specified ID, or null if not found
     */
    public Task findById(Long id) {
        return tasks.get(id);
    }

    /**
     * Deletes a task by its ID from the repository.
     * 
     * @param id the ID of the task to be deleted
     */
    public void deleteById(Long id) {
        tasks.remove(id);
    }

    /**
     * Retrieves all tasks from the repository.
     * 
     * @return a collection of all tasks
     */
    public List<Task> findAll() {
        return List.copyOf(tasks.values());
    }

    /**
     * Updates an existing task in the repository.
     * 
     * @param task the task to be updated
     * @return the updated task, or null if the task does not exist
     */
    public Task update(Task task) {
        if (task.getId() == null || !tasks.containsKey(task.getId())) {
            return null; // Task does not exist
        }
        tasks.put(task.getId(), task);
        return task;
    }

}
