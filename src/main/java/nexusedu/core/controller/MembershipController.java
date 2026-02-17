package kz.nexusedu.core.controller;

import kz.nexusedu.core.domain.Membership;
import kz.nexusedu.core.repository.MembershipRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/memberships")
public class MembershipController {

    private final MembershipRepository repository;

    public MembershipController(MembershipRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Membership create(@RequestBody Membership membership) {
        return repository.save(membership);
    }

    @GetMapping("/user/{userId}")
    public List<Membership> getByUser(@PathVariable UUID userId) {
        return repository.findByUserId(userId);
    }

    @GetMapping("/organization/{orgId}")
    public List<Membership> getByOrganization(@PathVariable UUID orgId) {
        return repository.findByOrganizationId(orgId);
    }
}