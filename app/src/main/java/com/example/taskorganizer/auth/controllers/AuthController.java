package com.example.taskorganizer.auth.controllers;

import com.example.taskorganizer.auth.exceptions.EmailAlreadyExistsException;
import com.example.taskorganizer.auth.exceptions.UserNotFoundException;
import com.example.taskorganizer.auth.exceptions.UsernameAlreadyExistsException;
import com.example.taskorganizer.auth.requests.LoginPostRequest;
import com.example.taskorganizer.auth.requests.RegisterPostRequest;
import com.example.taskorganizer.auth.responses.GetUserProfileResponse;
import com.example.taskorganizer.auth.services.interfaces.IAuthService;
import com.example.taskorganizer.user.models.User;
import com.example.taskorganizer.user.services.interfaces.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IAuthService authService;
    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginPostRequest requestBody) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestBody.getUsername(), requestBody.getPassword())
            );

            String token = authService.assignToken(requestBody.getUsername());

            authService.getSecurityContext().setAuthentication(authentication);

            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("Credenciales incorrectas", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterPostRequest requestBody) {
        try {
            authService.register(requestBody.getFullName(), requestBody.getUsername(), requestBody.getEmail(), requestBody.getPassword());
        } catch (UsernameAlreadyExistsException e) {
            return new ResponseEntity<>("Nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
        } catch (EmailAlreadyExistsException e) {
            return new ResponseEntity<>("Email ya existe", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Usuario registrado correctamente", HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (!StringUtils.hasText(bearerToken) || !bearerToken.startsWith("Bearer ")) {
            return new ResponseEntity<>("Usuario no tiene token", HttpStatus.UNAUTHORIZED);
        }

        String token = bearerToken.substring(7);

        try {
            User user = userService.getUserByToken(token);

            return new GetUserProfileResponse(user);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("No hay usuario con ese token", HttpStatus.NOT_FOUND);
        }
    }
}
