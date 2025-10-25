package com.michael.thedoer.controller;


import com.michael.thedoer.Repository.TaskRepo;
import com.michael.thedoer.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {

    @Autowired
    TaskRepo taskRepo;

    @GetMapping("/tasks")
    protected List<Task> findAll() {
       return taskRepo.findAll();
    }

    @PostMapping("/create-task")
    public Task createTask(@RequestBody Task task) {
        task.setCreatedAt(LocalDate.now());
       return taskRepo.save(task);
    }

    @PatchMapping("/update-task/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        var foundTask = taskRepo.findById(id);
        if (foundTask.isPresent()) {
            if (task.getName() !=null) {
                foundTask.get().setName(task.getName());
            }
            if  (task.getDescription() !=null) {
                foundTask.get().setDescription(task.getDescription());
            }
            if (task.getEndDate() !=null) {
                foundTask.get().setEndDate(task.getEndDate());
            }
        }

        return taskRepo.save(foundTask.get());
    }
}