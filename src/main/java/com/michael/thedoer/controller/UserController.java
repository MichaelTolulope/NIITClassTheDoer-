package com.michael.thedoer.controller;

import com.michael.thedoer.Repository.TaskRepo;
import com.michael.thedoer.Repository.UserRepo;
import com.michael.thedoer.dto.CreateTaskDto;
import com.michael.thedoer.dto.UpdateUserTaskDto;
import com.michael.thedoer.dto.UserLoginDto;
import com.michael.thedoer.dto.UserProfileDto;
import com.michael.thedoer.model.Task;
import com.michael.thedoer.model.TaskStatus;
import com.michael.thedoer.model.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name="User", description = "Endpoints for users")
@RestController
@RequestMapping("/thedoer/api/v1/users")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TaskRepo taskRepo;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.FOUND)
    public List<User> findAll() {
        return userRepo.findAll();
    }


    @GetMapping("/{userId}")
    public ResponseEntity<User> findById(@PathVariable Long userId) {
        return new ResponseEntity<>(userRepo.findById(userId).get(), HttpStatus.OK);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
        User foundUser =  userRepo.findById(userId).get();
        if (user.getFirstName() != null) {
            foundUser.setFirstName(user.getFirstName());
        }
        if(user.getLastName() != null) {
            foundUser.setLastName(user.getLastName());
        }
        if(user.getEmail() != null) {
            foundUser.setEmail(user.getEmail());
        }
        if(user.getPassword() != null) {
            foundUser.setPassword(user.getPassword());
        }
        if(user.getDateOfBirth() != null) {
            foundUser.setDateOfBirth(user.getDateOfBirth());
        }
        return new ResponseEntity<>(userRepo.save(foundUser), HttpStatus.OK);
    }

//    @PostMapping("/login")
//    public ResponseEntity<User> login(@RequestBody UserLoginDto userLoginData) {
//
//        String email = userLoginData.getEmail();
//        String password = userLoginData.getPassword();
//
//        var  foundUser = userRepo.findByEmail(email);
//        if(foundUser.isPresent()){
//            if(foundUser.get().getPassword().equals(password)) {
//                return new ResponseEntity<>(foundUser.get(), HttpStatus.OK);
//            }
//        }
//        return null;
//    }

    @GetMapping("/user-profile")
    public ResponseEntity<UserProfileDto> getUserProfile(@RequestParam Long userId) {
        var foundUser = userRepo.findById(userId).get();
        var userProfile = new UserProfileDto(foundUser.getFirstName(), foundUser.getLastName(), foundUser.getEmail(), foundUser.getDateOfBirth());
        return new ResponseEntity<>(userProfile,HttpStatus.FOUND);

    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

//    get user tasks

    @GetMapping("/user-tasks/{userId}")
    public ResponseEntity<List<Task>> getTasks(@RequestParam(required = false) TaskStatus status, @PathVariable Long userId) {
        User user = userRepo.findById(userId).get();
        List<Task> tasks = user.getTasks();
           for(Task task : tasks) {
               if(task.getStatus().equals(status)) {
                   return new ResponseEntity<>(tasks, HttpStatus.FOUND);
               }
           }
        return new ResponseEntity<>(tasks,HttpStatus.FOUND);

    }

    @PostMapping("/add-task")
    public ResponseEntity<Task> addTask(@Valid @RequestBody CreateTaskDto task) {
        User user = userRepo.findById(task.getUserId()).get();
        Task newTask = new Task(
                task.getTitle(),
                task.getDescription(),
                task.getEndDate(),
                user
        );

        taskRepo.save(newTask);

        user.getTasks().add(newTask);
        userRepo.save(user);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }




}
