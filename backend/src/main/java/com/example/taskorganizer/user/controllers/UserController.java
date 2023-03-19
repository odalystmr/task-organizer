package com.example.taskorganizer.user.controllers;

import com.example.taskorganizer.user.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    //Importar Interfaz


    @Autowired
    private IUserService service;
    @GetMapping("")
    public String findAll() {
        return "ver todos";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id) {
        return "ver solo " + id;
    }

    @PostMapping("")
    public String add() {
        return "añadir nuevo";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id) {
        return "actualizar uno existente " + id;
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        return "borrar solo " + id;
    }

}
