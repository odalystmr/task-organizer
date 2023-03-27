package com.example.taskorganizer.auth.controllers;

import com.example.taskorganizer.auth.requests.LoginPostRequest;
import com.example.taskorganizer.auth.requests.RegisterPostRequest;
import com.example.taskorganizer.auth.services.interfaces.IAuthService;
import com.example.taskorganizer.user.models.User;
import com.example.taskorganizer.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IAuthService service;

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginPostRequest requestBody) {
//        System.out.println( requestBody.getUsername() + requestBody.getPassword());
//        try {
//            String token = service.login(
//                    requestBody.getUsername(),
//                    requestBody.getPassword());
//
//            return ResponseEntity.ok(token);
//        } catch (InvalidPasswordException e) {
//            return new ResponseEntity<>("invalid password", HttpStatus.UNAUTHORIZED);
//        }
//    }


    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginPostRequest requestBody){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                requestBody.getUsername(),
                requestBody.getPassword()
                )
        );
        String token = service.assignToken(requestBody.getUsername());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody RegisterPostRequest requestBody){

        if(userRepository.existsByUsername(requestBody.getUsername())){
            return new ResponseEntity<>("El nombre de usuario ya esta en uso.", HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(requestBody.getEmail())){
            return new ResponseEntity<>("El email ya esta en uso.", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setFullName(requestBody.getFullName());
        user.setUsername(requestBody.getUsername());
        user.setEmail(requestBody.getEmail());
        user.setPassword(passwordEncoder.encode(requestBody.getPassword()));

        userRepository.save(user);

        return new ResponseEntity<>("Usuario registrado correctamente.", HttpStatus.OK);

    }
}