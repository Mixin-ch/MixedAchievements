package ch.mixin.mixedAchievements.api;

public class InfoAchievementStage {
    private final InfoAchievement infoAchievement;
    private final int stageId;
    private final int maxPoints;

    public InfoAchievementStage(InfoAchievement infoAchievement, int stageId, int maxPoints) {
        this.infoAchievement = infoAchievement;
        this.stageId = stageId;
        this.maxPoints = maxPoints;
    }

    public InfoAchievement getAchievementInfo() {
        return infoAchievement;
    }

    public int getStageId() {
        return stageId;
    }

    public int getMaxPoints() {
        return maxPoints;
    }
}
