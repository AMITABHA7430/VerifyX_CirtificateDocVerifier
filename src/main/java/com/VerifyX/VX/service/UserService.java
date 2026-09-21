package com.VerifyX.VX.service;

import com.VerifyX.VX.dto.CreateIssuerRequest;
import com.VerifyX.VX.dto.UserResponse;
import com.VerifyX.VX.entity.Organization;
import com.VerifyX.VX.entity.User;
import com.VerifyX.VX.entity.UserRole;
import com.VerifyX.VX.Repository.OrganizationRepository;
import com.VerifyX.VX.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            OrganizationRepository organizationRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.organizationRepository = organizationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse createIssuer(
            CreateIssuerRequest request,
            Long organizationId) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        Organization organization = organizationRepository
                .findById(organizationId)
                .orElseThrow(() ->
                        new RuntimeException("Organization not found"));

        User issuer = new User();

        issuer.setName(request.getName());
        issuer.setEmail(request.getEmail());

        issuer.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        issuer.setRole(UserRole.ISSUER);
        issuer.setOrganization(organization);

        User savedIssuer = userRepository.save(issuer);

        return new UserResponse(
                savedIssuer.getId(),
                savedIssuer.getName(),
                savedIssuer.getEmail(),
                savedIssuer.getRole(),
                savedIssuer.getOrganization().getId()
        );
    }

    public User getUserByEmail(String email) {

        return userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }
}