package ch.mixin.mixedAchievements.blueprint;

public class AchievementBlueprintLeafElement extends AchievementBlueprintElement {
    private String achievementId;
    private String achievementName;
    private int maxPoints;

    public AchievementBlueprintLeafElement(AchievementItemSetup achievementItemSetup, String achievementId, String achievementName, int maxPoints) {
        super(achievementItemSetup);
        this.achievementId = achievementId;
        this.maxPoints = maxPoints;
    }

    public String getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(String achievementId) {
        this.achievementId = achievementId;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }
}
