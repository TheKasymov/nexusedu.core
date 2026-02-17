package kz.nexusedu.core.policy;

import org.springframework.stereotype.Component;

@Component
public class KindergartenGradingStrategy implements GradingStrategy {

    @Override
    public boolean supports(String type) {
        return "KINDERGARTEN".equalsIgnoreCase(type);
    }

    @Override
    public String evaluateScore(int score) {
        return "N/A";
    }
}