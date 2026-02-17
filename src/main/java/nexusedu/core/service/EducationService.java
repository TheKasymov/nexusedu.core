package kz.nexusedu.core.service;

import kz.nexusedu.core.policy.GradingStrategy;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EducationService {

    private final List<GradingStrategy> strategies;

    public EducationService(List<GradingStrategy> strategies) {
        this.strategies = strategies;
    }

    public String processGrade(String orgType, int score) {
        return strategies.stream()
                .filter(strategy -> strategy.supports(orgType))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unknown organization type"))
                .evaluateScore(score);
    }
}