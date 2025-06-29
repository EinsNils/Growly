package dev.nz3x.backend.service;

import dev.nz3x.backend.config.JwtConfig;
import dev.nz3x.backend.domain.enums.Role;
import dev.nz3x.backend.domain.model.User;
import dev.nz3x.backend.dto.requests.LoginRequest;
import dev.nz3x.backend.dto.requests.RegisterRequest;
import dev.nz3x.backend.dto.response.AuthResponse;
import dev.nz3x.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtConfig jwtConfig;
  private final AuthenticationManager authenticationManager;

  public AuthResponse register(RegisterRequest request) {
    if (userRepository.existsByUsername(request.username())) {
      throw new RuntimeException("Username already exists");
    }

    User user =
        User.builder()
            .username(request.username())
            .password(passwordEncoder.encode(request.password()))
            .role(Role.USER)
            .build();

    userRepository.save(user);

    String token = jwtConfig.generateToken(user.getUsername(), user.getRole().name());
    return new AuthResponse(token, user.getUsername(), user.getRole().name());
  }

  public AuthResponse login(LoginRequest request) {
    // Authenticate user - this will throw an exception if credentials are invalid
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.username(), request.password()));

    User user =
        userRepository
            .findByUsername(request.username())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    String token = jwtConfig.generateToken(user.getUsername(), user.getRole().name());
    return new AuthResponse(token, user.getUsername(), user.getRole().name());
  }
}
