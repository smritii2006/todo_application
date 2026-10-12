package com.sheat.todo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sheat.todo.entity.Task;
import com.sheat.todo.entity.TaskStatus;
import com.sheat.todo.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    // Constructor
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // CREATE TASK 
    public Task createTask(Task task) {

        return taskRepository.save(task);
    }

    // GET ALL TASKS
    public List<Task> getAllTasks() {

        return taskRepository.findAll();
    }

    // GET TASK BY ID
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Task not found with id: " + id));
    }

    // // GET TASKS BY DATE
    // public List<Task> getTasksByDate(LocalDate date) {

    //     return taskRepository.findByTaskDateOrderByTaskTimeAsc(date);
    // }

    // UPDATE TASK
    public Task updateTask(Long id, Task task) {

        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Task not found with id: " + id));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setTaskDate(task.getTaskDate());
        existingTask.setTaskTime(task.getTaskTime());
        existingTask.setStatus(task.getStatus());
        

        return taskRepository.save(existingTask);
    }

    // UPDATE TASK STATUS
    public Task updateStatus(Long id, TaskStatus status) {

        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Task not found with id: " + id));

        existingTask.setStatus(status);

        return taskRepository.save(existingTask);
    }

    // DELETE TASK
    public void deleteTask(Long id) {

        if (!taskRepository.existsById(id)) {

            throw new RuntimeException(
                    "Task not found with id: " + id
            );
        }

        taskRepository.deleteById(id);
    }
}
