package org.example.centrosnetapi.controllers;

import lombok.RequiredArgsConstructor;
import org.example.centrosnetapi.dtos.ChangePasswordRequestDTO;
import org.example.centrosnetapi.dtos.LoginRequest;
import org.example.centrosnetapi.dtos.UserResponseDTO;
import org.example.centrosnetapi.models.User;
import org.example.centrosnetapi.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public UserResponseDTO login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequestDTO dto,
            @AuthenticationPrincipal User user
    ) {
        authService.changePassword(user, dto);
        return ResponseEntity.ok().build();
    }
}
