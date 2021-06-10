package com.yube.controllers;

import com.yube.model.dto.payload.request.LoginRequest;
import com.yube.model.dto.payload.request.SignupRequest;
import com.yube.model.dto.payload.response.JwtResponse;
import com.yube.model.dto.payload.response.MessageResponse;
import com.yube.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return authService.registerUser(signUpRequest);
    }
}