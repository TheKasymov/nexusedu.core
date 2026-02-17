package kz.nexusedu.core.policy;

import org.springframework.stereotype.Component;

@Component
public class SchoolGradingStrategy implements GradingStrategy {

    @Override
    public boolean supports(String type) {
        return "SCHOOL".equalsIgnoreCase(type);
    }

    @Override
    public String evaluateScore(int score) {
        if (score >= 85) return "5";
        if (score >= 70) return "4";
        if (score >= 50) return "3";
        return "2";
    }
}