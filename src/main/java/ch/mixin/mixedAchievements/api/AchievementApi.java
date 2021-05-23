package ch.mixin.mixedAchievements.api;

public class AchievementApi {
    private final String setName;
    private final AchievementManager achievementManager;

    public AchievementApi(String setName, AchievementManager achievementManager) {
        this.setName = setName;
        this.achievementManager = achievementManager;
    }
}
