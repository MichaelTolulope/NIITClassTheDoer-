package com.michael.thedoer.controller;

import com.michael.thedoer.Repository.UserRepo;
import com.michael.thedoer.dto.UserLoginDto;
import com.michael.thedoer.dto.UserProfileDto;
import com.michael.thedoer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/thedoer/api/v1/users")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findById(@PathVariable Long userId) {
        return new ResponseEntity<>(userRepo.findById(userId).get(), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        if(userRepo.findByEmail(user.getEmail()).isPresent()) {
            return null;
        }
        return new ResponseEntity<>(userRepo.save(user), HttpStatus.CREATED);
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

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserLoginDto userLoginData) {

        String email = userLoginData.getEmail();
        String password = userLoginData.getPassword();

        var  foundUser = userRepo.findByEmail(email);
        if(foundUser.isPresent()){
            if(foundUser.get().getPassword().equals(password)) {
                return new ResponseEntity<>(foundUser.get(), HttpStatus.OK);
            }
        }
        return null;
    }

    @GetMapping("/user-profile")
    public ResponseEntity<UserProfileDto> getUserProfile(@RequestParam Long userId) {
        var foundUser = userRepo.findById(userId).get();
        var userProfile = new UserProfileDto(foundUser.getFirstName(), foundUser.getLastName(), foundUser.getEmail());
        return new ResponseEntity<>(userProfile,HttpStatus.FOUND);

    }

}
