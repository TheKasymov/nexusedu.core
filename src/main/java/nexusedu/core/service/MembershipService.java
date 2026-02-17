package kz.nexusedu.core.service;

import kz.nexusedu.core.domain.Membership;
import kz.nexusedu.core.repository.MembershipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MembershipService {

    private final MembershipRepository repository;

    public MembershipService(MembershipRepository repository) {
        this.repository = repository;
    }

    public Membership create(Membership membership) {
        return repository.save(membership);
    }

    public List<Membership> getByUser(UUID userId) {
        return repository.findByUserId(userId);
    }

    public List<Membership> getByOrganization(UUID orgId) {
        return repository.findByOrganizationId(orgId);
    }
}