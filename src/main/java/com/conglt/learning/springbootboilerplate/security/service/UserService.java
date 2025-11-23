package com.conglt.learning.springbootboilerplate.security.service;

import com.conglt.learning.springbootboilerplate.model.User;
import com.conglt.learning.springbootboilerplate.model.UserRole;
import com.conglt.learning.springbootboilerplate.repository.UserRepository;
import com.conglt.learning.springbootboilerplate.security.dto.LoginRequest;
import com.conglt.learning.springbootboilerplate.security.dto.LoginResponse;
import com.conglt.learning.springbootboilerplate.security.dto.RegistrationRequest;
import com.conglt.learning.springbootboilerplate.security.dto.RegistrationResponse;
import com.conglt.learning.springbootboilerplate.security.jwt.JwtTokenProvider;
import com.conglt.learning.springbootboilerplate.security.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service for user authentication and registration.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserMapper userMapper;

    /**
     * Register a new user.
     *
     * @param registrationRequest the registration request
     * @return the registration response
     * @throws IllegalArgumentException if username or email already exists
     */
    public RegistrationResponse registration(RegistrationRequest registrationRequest) {
        log.info("Registering new user with username: {}", registrationRequest.getUsername());

        // Check if username already exists
        if (userRepository.existsByUsername(registrationRequest.getUsername())) {
            log.error("Username already exists: {}", registrationRequest.getUsername());
            throw new IllegalArgumentException("Username already exists");
        }

        // Check if email already exists
        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            log.error("Email already exists: {}", registrationRequest.getEmail());
            throw new IllegalArgumentException("Email already exists");
        }

        // Create new user
        User user = userMapper.registrationRequestToUser(registrationRequest);
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setRole(UserRole.ROLE_USER);
        user.setIsActive(true);

        // Save user
        User savedUser = userRepository.save(user);
        log.info("User registered successfully with ID: {}", savedUser.getId());

        return userMapper.userToRegistrationResponse(savedUser);
    }

    /**
     * Authenticate user and generate JWT token.
     *
     * @param loginRequest the login request
     * @return the login response with JWT token
     * @throws IllegalArgumentException if username not found or password is incorrect
     */
    public LoginResponse login(LoginRequest loginRequest) {
        log.info("Authenticating user: {}", loginRequest.getUsername());

        // Find user by username
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> {
                    log.error("User not found: {}", loginRequest.getUsername());
                    return new IllegalArgumentException("Invalid username or password");
                });

        // Verify password
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            log.error("Invalid password for user: {}", loginRequest.getUsername());
            throw new IllegalArgumentException("Invalid username or password");
        }

        // Generate JWT token
        String token = jwtTokenProvider.generateToken(user.getUsername());
        log.info("JWT token generated for user: {}", user.getUsername());

        return new LoginResponse(token);
    }

    /**
     * Get user by username.
     *
     * @param username the username
     * @return the user
     * @throws IllegalArgumentException if user not found
     */
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));
    }
}

