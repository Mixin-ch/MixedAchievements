package ch.mixin.mixedAchievements.blueprint;

public class BlueprintAchievementStage {
    private final AchievementItemSetup achievementItemSetup;
    private final int maxPoints;

    public BlueprintAchievementStage(AchievementItemSetup achievementItemSetup, int maxPoints) {
        this.achievementItemSetup = achievementItemSetup;
        this.maxPoints = maxPoints;
    }

    public BlueprintAchievementStage(AchievementItemSetup achievementItemSetup) {
        this.achievementItemSetup = achievementItemSetup;
        this.maxPoints = 0;
    }

    public AchievementItemSetup getAchievementItemSetup() {
        return achievementItemSetup;
    }

    public int getMaxPoints() {
        return maxPoints;
    }
}
