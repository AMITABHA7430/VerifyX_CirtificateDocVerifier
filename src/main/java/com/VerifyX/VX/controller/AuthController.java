package com.VerifyX.VX.controller;

import com.VerifyX.VX.dto.LoginRequest;
import com.VerifyX.VX.dto.LoginResponse;
import com.VerifyX.VX.dto.RegisterRequest;
import com.VerifyX.VX.dto.RegisterResponse;
import com.VerifyX.VX.entity.User;
import com.VerifyX.VX.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
