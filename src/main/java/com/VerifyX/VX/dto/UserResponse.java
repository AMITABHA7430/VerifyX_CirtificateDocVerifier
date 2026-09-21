package com.VerifyX.VX.dto;

import com.VerifyX.VX.entity.UserRole;

public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private UserRole role;
    private Long organizationId;

    public UserResponse(
            Long id,
            String name,
            String email,
            UserRole role,
            Long organizationId) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.organizationId = organizationId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }

    public Long getOrganizationId() {
        return organizationId;
    }
}