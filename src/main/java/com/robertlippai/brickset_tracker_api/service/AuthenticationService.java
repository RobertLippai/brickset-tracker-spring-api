package com.robertlippai.brickset_tracker_api.service;

import com.robertlippai.brickset_tracker_api.api.dto.auth.AuthResponseDto;
import com.robertlippai.brickset_tracker_api.api.dto.auth.UserLoginRequestDto;
import com.robertlippai.brickset_tracker_api.api.dto.auth.UserRegistrationRequestDto;
import com.robertlippai.brickset_tracker_api.api.model.Role;
import com.robertlippai.brickset_tracker_api.api.model.User;
import com.robertlippai.brickset_tracker_api.repository.RoleRepository;
import com.robertlippai.brickset_tracker_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthResponseDto registerUser(UserRegistrationRequestDto requestDto) {
        if (userRepository.findByUsername(requestDto.getUsername()).isPresent()) {
            throw new IllegalStateException("Username already exists");
        }

        User newUser = new User();
        newUser.setUsername(requestDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(requestDto.getPassword())); // hashing before saving!

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Error: Default role ROLE_USER not found."));

        newUser.getRoles().add(userRole);

        User savedUser = userRepository.save(newUser);

        String jwtToken = jwtService.generateToken(savedUser);

        return AuthResponseDto.builder().token(jwtToken).build();
    }

    public AuthResponseDto loginUser(UserLoginRequestDto requestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.getUsername(),
                        requestDto.getPassword()
                )
        );

        var user =  userRepository.findByUsername(requestDto.getUsername()).orElseThrow(
                () -> new IllegalStateException("User not found"));


        String jwtToken = jwtService.generateToken(user);

        return AuthResponseDto.builder().token(jwtToken).build();
    }
}