package com.michael.thedoer.controller;

import com.michael.thedoer.Repository.UserRepo;
import com.michael.thedoer.dto.JwtResponseDto;
import com.michael.thedoer.dto.RegisterUserDto;
import com.michael.thedoer.dto.UserLoginDto;
import com.michael.thedoer.model.User;
import com.michael.thedoer.service.JwtService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("thedoer/v1/auth")
@Tag(name="Authentication", description = "Endpoints to authenticate users")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    JwtService  jwtService;

    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@RequestBody RegisterUserDto user) {
        if(userRepo.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return new ResponseEntity<>(userRepo.save(newUser), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody UserLoginDto userLoginData) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginData.getEmail(),
                        userLoginData.getPassword()
                )
        );

        var token = jwtService.generateToken(userLoginData.getEmail());
        return ResponseEntity.ok(new JwtResponseDto(token));
    }
}
