package kz.nexusedu.core.service;

import kz.nexusedu.core.domain.Organization;
import kz.nexusedu.core.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrganizationService {

    private final OrganizationRepository repository;

    public OrganizationService(OrganizationRepository repository) {
        this.repository = repository;
    }

    public Organization create(Organization organization) {
        return repository.save(organization);
    }

    public List<Organization> getAll() {
        return repository.findAll();
    }

    public Organization getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found"));
    }
}