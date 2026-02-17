package kz.nexusedu.core.service;

import kz.nexusedu.core.policy.KindergartenGradingStrategy;
import kz.nexusedu.core.policy.SchoolGradingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EducationServiceTest {

    private EducationService educationService;

    @BeforeEach
    void setUp() {
        educationService = new EducationService(List.of(
                new SchoolGradingStrategy(),
                new KindergartenGradingStrategy()
        ));
    }

    @Test
    void shouldReturnGradeForSchool() {
        String result = educationService.processGrade("SCHOOL", 85);
        assertEquals("5", result);
    }

    @Test
    void shouldReturnNAForKindergarten() {
        String result = educationService.processGrade("KINDERGARTEN", 100);
        assertEquals("N/A", result);
    }

    @Test
    void shouldThrowExceptionForUnknownType() {
        assertThrows(RuntimeException.class, () -> {
            educationService.processGrade("UNIVERSITY", 90);
        });
    }
}