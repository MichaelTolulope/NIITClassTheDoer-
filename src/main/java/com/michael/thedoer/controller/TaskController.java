package com.michael.thedoer.controller;


import com.michael.thedoer.Repository.TaskRepo;
import com.michael.thedoer.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("thedoer/api/v1/tasks")
public class TaskController {

    @Autowired
    TaskRepo taskRepo;

    @GetMapping("/all")
    protected ResponseEntity<List<Task>> findAll() {
       return new ResponseEntity<>(taskRepo.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        task.setCreatedAt(LocalDate.now());
       return new ResponseEntity<>(taskRepo.save(task), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
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

        return new ResponseEntity<>(taskRepo.save(foundTask.get()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        return new ResponseEntity<>(taskRepo.findById(id).get(), HttpStatus.OK);
    }
}