package com.sheat.todo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sheat.todo.entity.Task;
import com.sheat.todo.entity.TaskStatus;
import com.sheat.todo.service.TaskService;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // CREATE TASK
    @PostMapping
    public ResponseEntity <Task> createTask(@RequestBody Task task) {

        Task saved = taskService.createTask(task);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // GET ALL TASKS 
    @GetMapping
    public List<Task> getAllTasks() {

        return taskService.getAllTasks();
    }

    // GET TASK BY ID
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {

        return taskService.getTaskById(id);
    }

    // GET TASKS BY DATE
    // @GetMapping("/by-date")
    // public List<Task> getTasksByDate(
    //         @RequestParam
    //         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    //         LocalDate date) {

    //     return taskService.getTasksByDate(date);
    // }

    // UPDATE TASK
    @PutMapping("/{id}")
    public Task updateTask(
            @PathVariable Long id,
            @RequestBody Task task) {

        return taskService.updateTask(id, task);
    }

    // UPDATE TASK STATUS
    @PatchMapping("/{id}/status")
    public Task updateStatus(
            @PathVariable Long id,
            @RequestParam TaskStatus status) {

        return taskService.updateStatus(id, status);
    }

    // DELETE TASK
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {

        taskService.deleteTask(id);
    }
}