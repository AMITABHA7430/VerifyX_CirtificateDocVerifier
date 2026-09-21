package com.VerifyX.VX.controller;

import com.VerifyX.VX.dto.CreateIssuerRequest;
import com.VerifyX.VX.dto.UserResponse;
import com.VerifyX.VX.entity.User;
import com.VerifyX.VX.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/issuer")
    @PreAuthorize("hasRole('ORG_ADMIN')")
    public UserResponse createIssuer(
            @RequestBody CreateIssuerRequest request,
            Authentication authentication) {

        String email = authentication.getName();

        User admin = userService.getUserByEmail(email);

        return userService.createIssuer(
                request,
                admin.getOrganization().getId()
        );
    }
}