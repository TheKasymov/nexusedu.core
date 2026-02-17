package kz.nexusedu.core.policy;

public interface GradingStrategy {
    boolean supports(String type);
    String evaluateScore(int score);
}