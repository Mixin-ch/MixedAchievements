package ch.mixin.mixedAchievements.blueprint;

public class AchievementLeafElement extends AchievementElement {
    private String achievementId;
    private int maxPoints;

    public String getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(String achievementId) {
        this.achievementId = achievementId;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }
}
