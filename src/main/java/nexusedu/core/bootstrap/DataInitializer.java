package kz.nexusedu.core.bootstrap;

import kz.nexusedu.core.domain.Organization;
import kz.nexusedu.core.domain.User;
import kz.nexusedu.core.domain.Membership;
import kz.nexusedu.core.repository.OrganizationRepository;
import kz.nexusedu.core.repository.UserRepository;
import kz.nexusedu.core.repository.MembershipRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final MembershipRepository membershipRepository;

    public DataInitializer(UserRepository userRepository,
                           OrganizationRepository organizationRepository,
                           MembershipRepository membershipRepository) {
        this.userRepository = userRepository;
        this.organizationRepository = organizationRepository;
        this.membershipRepository = membershipRepository;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setFullName("Sanzhar Admin");
            admin.setSystemRole("SUPER_ADMIN");
            userRepository.save(admin);

            Organization school = new Organization();
            school.setName("Education IT School");
            school.setType("SCHOOL");
            school.setSettings("{\"gradingSystem\": \"5-point\", \"maxCapacity\": 500}");
            organizationRepository.save(school);

            Organization kindergarten = new Organization();
            kindergarten.setName("Kids");
            kindergarten.setType("KINDERGARTEN");
            kindergarten.setSettings("{\"napTime\": \"13:00-15:00\", \"dietary\": \"halal\"}");
            organizationRepository.save(kindergarten);

            Membership membership = new Membership();
            membership.setUserId(admin.getId());
            membership.setOrganizationId(school.getId());
            membership.setRole("DIRECTOR");
            membership.setStatus("ACTIVE");
            membershipRepository.save(membership);
        }
    }
}