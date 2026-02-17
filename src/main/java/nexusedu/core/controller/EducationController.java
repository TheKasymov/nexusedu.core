package kz.nexusedu.core.controller;

import kz.nexusedu.core.service.EducationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/education")
public class EducationController {

    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping("/grade")
    public String getGrade(@RequestParam String orgType, @RequestParam int score) {
        return educationService.processGrade(orgType, score);
    }
}