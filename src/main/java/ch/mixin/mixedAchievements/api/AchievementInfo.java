package ch.mixin.mixedAchievements.api;

import ch.mixin.mixedAchievements.metaData.AchievementData;

public class AchievementInfo {
    private final AchievementData achievementData;
    private final String achievementName;
    private final int maxPoints;

    public AchievementInfo(AchievementData achievementData, String achievementName, int maxPoints) {
        this.achievementData = achievementData;
        this.achievementName = achievementName;
        this.maxPoints = maxPoints;
    }

    public AchievementData getAchievementData() {
        return achievementData;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public int getMaxPoints() {
        return maxPoints;
    }
}
