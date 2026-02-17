package kz.nexusedu.core.repository;

import kz.nexusedu.core.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
}