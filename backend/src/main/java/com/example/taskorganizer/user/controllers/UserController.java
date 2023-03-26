package com.example.taskorganizer.user.controllers;

import com.example.taskorganizer.user.models.User;
import com.example.taskorganizer.user.requests.UsersPostRequest;
import com.example.taskorganizer.user.requests.UserPutRequest;
import com.example.taskorganizer.user.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    //Importar Interfaz
    @Autowired
    private IUserService service;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody UsersPostRequest requestBody) {
        User user = service.create(
                requestBody.getFullName(),
                requestBody.getUsername(),
                requestBody.getEmail(),
                requestBody.getPassword());

        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody UserPutRequest requestBody) {
        User user = service.update(
                id,
                requestBody.getFullName(),
                requestBody.getUsername(),
                requestBody.getEmail(),
                requestBody.getPassword()
        );

        return ResponseEntity.ok(user);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        service.delete(id);
    }

}
