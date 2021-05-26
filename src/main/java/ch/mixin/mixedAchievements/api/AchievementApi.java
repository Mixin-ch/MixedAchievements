package ch.mixin.mixedAchievements.api;

import java.util.UUID;

public class AchievementApi {
    private final String setName;
    private final AchievementManager achievementManager;

    public AchievementApi(String setName, AchievementManager achievementManager) {
        this.setName = setName;
        this.achievementManager = achievementManager;
    }

    public void setAchieved(String achievement, UUID playerId, boolean achieved) {
    }

    public boolean getAchieved(String achievement, UUID playerId) {
        return false;
    }

    public void setPoints(String achievement, UUID playerId, int value) {
    }

    public void addPoints(String achievement, UUID playerId, int value) {
    }

    public void revalueAchieved(String achievement, UUID playerId) {
    }
}
