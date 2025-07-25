package dev.nz3x.backend.controller;

import dev.nz3x.backend.dto.requests.LoginRequest;
import dev.nz3x.backend.dto.requests.RegisterRequest;
import dev.nz3x.backend.dto.response.AuthResponse;
import dev.nz3x.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
    try {
      AuthResponse response = authService.register(request);
      return ResponseEntity.ok(response);
    } catch (RuntimeException e) {
      log.error(e.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
    try {
      AuthResponse response = authService.login(request);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      log.error(e.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }
}
