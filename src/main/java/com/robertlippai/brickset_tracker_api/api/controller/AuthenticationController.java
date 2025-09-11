package com.robertlippai.brickset_tracker_api.api.controller;

import com.robertlippai.brickset_tracker_api.api.dto.auth.AuthResponseDto;
import com.robertlippai.brickset_tracker_api.api.dto.auth.UserLoginRequestDto;
import com.robertlippai.brickset_tracker_api.api.dto.auth.UserRegistrationRequestDto;
import com.robertlippai.brickset_tracker_api.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authService;

    @Autowired
    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody UserRegistrationRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerUser(requestDto));

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody UserLoginRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.loginUser(requestDto));
    }
}