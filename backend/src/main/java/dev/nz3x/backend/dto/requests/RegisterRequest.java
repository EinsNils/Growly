package dev.nz3x.backend.dto.requests;

import dev.nz3x.backend.domain.enums.Role;

public record RegisterRequest(String username, String password, Role role) {}
