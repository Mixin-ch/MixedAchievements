package ch.mixin.mixedAchievements.api;

import ch.mixin.mixedAchievements.metaData.AchievementData;

public class AchievementInfo {
    private final AchievementData achievementData;
    private final int maxPoints;

    public AchievementInfo(AchievementData achievementData, int maxPoints) {
        this.achievementData = achievementData;
        this.maxPoints = maxPoints;
    }

    public AchievementData getAchievementSetData() {
        return achievementData;
    }

    public int getMaxPoints() {
        return maxPoints;
    }
}
