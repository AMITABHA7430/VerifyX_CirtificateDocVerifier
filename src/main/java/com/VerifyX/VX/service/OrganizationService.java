package com.VerifyX.VX.service;

import com.VerifyX.VX.entity.Organization;
import com.VerifyX.VX.Repository.OrganizationRepository;
import com.VerifyX.VX.exception.OrganizationNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Organization saveOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }
    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }
    public Organization getOrganizationById(Long id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new OrganizationNotFoundException(id));
    }
}
