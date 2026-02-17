package kz.nexusedu.core.service;

import kz.nexusedu.core.domain.Organization;
import kz.nexusedu.core.repository.OrganizationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrganizationServiceTest {

    @Mock
    private OrganizationRepository repository;

    @InjectMocks
    private OrganizationService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindOrganizationById() {
        UUID id = UUID.randomUUID();
        Organization org = new Organization();
        org.setId(id);
        org.setName("Test Org");

        when(repository.findById(id)).thenReturn(Optional.of(org));

        Organization result = service.getById(id);

        assertNotNull(result);
        assertEquals("Test Org", result.getName());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void shouldThrowExceptionWhenOrganizationNotFound() {
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.getById(id));
    }
}