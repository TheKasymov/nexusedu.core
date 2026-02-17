package kz.nexusedu.core.controller;

import kz.nexusedu.core.domain.Organization;
import kz.nexusedu.core.repository.OrganizationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

    private final OrganizationRepository repository;

    public OrganizationController(OrganizationRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Organization create(@RequestBody Organization organization) {
        return repository.save(organization);
    }

    @GetMapping
    public List<Organization> getAll() {
        return repository.findAll();
    }
}