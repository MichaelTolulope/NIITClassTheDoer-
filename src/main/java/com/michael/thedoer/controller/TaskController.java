package com.michael.thedoer.controller;


import com.michael.thedoer.Repository.TaskRepo;
import com.michael.thedoer.dto.UpdateUserTaskDto;
import com.michael.thedoer.model.Task;
import com.michael.thedoer.model.TaskStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Tag(name="Tasks",description = "All Tasks Endpoints")
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


    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        return new ResponseEntity<>(taskRepo.findById(id).get(), HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody UpdateUserTaskDto task) {

        Task foundTask = taskRepo.findById(id).get();

        if(task.getTitle() != null) {
            foundTask.setName(task.getTitle());
        }
        if(task.getDescription() != null) {
            foundTask.setDescription(task.getDescription());
        }
        if(task.getEndDate() != null) {
            foundTask.setEndDate(task.getEndDate());
        }
        return new ResponseEntity<>(taskRepo.save(foundTask), HttpStatus.OK);

    }

    @PatchMapping("/progress-status/{id}")
    public ResponseEntity<Task> updateProgress(@PathVariable Long id) {
        Task foundTask = taskRepo.findById(id).get();
        if(foundTask.getStatus() != TaskStatus.CANCELLED || foundTask.getStatus() != TaskStatus.COMPLETED) {
            if(foundTask.getStatus() == TaskStatus.PENDING) {
                foundTask.setStatus(TaskStatus.IN_PROGRESS);
                return new ResponseEntity<>(taskRepo.save(foundTask), HttpStatus.OK);
            }
            if(foundTask.getStatus() == TaskStatus.IN_PROGRESS) {
                foundTask.setStatus(TaskStatus.COMPLETED);
                return new ResponseEntity<>(taskRepo.save(foundTask), HttpStatus.OK);
            }

            if(foundTask.getStatus() == TaskStatus.COMPLETED) {
                return new ResponseEntity<>(taskRepo.save(foundTask), HttpStatus.OK);
            }

        }
        return null;
    }

    @PatchMapping("cancel/{id}")
    public ResponseEntity<Task> cancelTask(@PathVariable Long id) {
        Task foundTask = taskRepo.findById(id).get();
        if(foundTask.getStatus() != TaskStatus.CANCELLED
                ||  foundTask.getStatus() != TaskStatus.COMPLETED) {
            foundTask.setStatus(TaskStatus.CANCELLED);
            return new ResponseEntity<>(taskRepo.save(foundTask), HttpStatus.OK);
        }
        return null;
    }

}