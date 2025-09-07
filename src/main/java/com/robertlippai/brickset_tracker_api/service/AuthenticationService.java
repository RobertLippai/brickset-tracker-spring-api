package com.robertlippai.brickset_tracker_api.service;

import com.robertlippai.brickset_tracker_api.api.dto.UserDto;
import com.robertlippai.brickset_tracker_api.api.dto.UserLoginRequestDto;
import com.robertlippai.brickset_tracker_api.api.dto.UserRegistrationRequestDto;
import com.robertlippai.brickset_tracker_api.api.model.User;
import com.robertlippai.brickset_tracker_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public UserDto registerUser(UserRegistrationRequestDto requestDto) {
        if (userRepository.findByUsername(requestDto.getUsername()).isPresent()) {
            throw new IllegalStateException("Username already exists");
        }

        User newUser = new User();
        newUser.setUsername(requestDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(requestDto.getPassword())); // hashing before saving!
        newUser.setRole("USER");

        User savedUser = userRepository.save(newUser);
        return UserDto.fromEntity(savedUser);
    }

    public UserDto loginUser(UserLoginRequestDto requestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.getUsername(),
                        requestDto.getPassword()
                )
        );

        var user =  userRepository.findByUsername(requestDto.getUsername()).orElseThrow(
                () -> new IllegalStateException("User not found"));

        return UserDto.fromEntity(user);
    }
}