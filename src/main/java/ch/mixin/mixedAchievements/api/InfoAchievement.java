package ch.mixin.mixedAchievements.api;

import ch.mixin.mixedAchievements.data.DataAchievement;

import java.util.List;

public class InfoAchievement {
    private final DataAchievement dataAchievement;
    private final String setId;
    private final String achievementId;
    private List<InfoAchievementStage> infoAchievementStageList;
    private boolean usesPoints;

    public InfoAchievement(DataAchievement dataAchievement, String setId, String achievementId) {
        this.dataAchievement = dataAchievement;
        this.setId = setId;
        this.achievementId = achievementId;
    }

    public int getStageNumber() {
        return infoAchievementStageList.size();
    }

    public DataAchievement getDataAchievement() {
        return dataAchievement;
    }

    public String getSetId() {
        return setId;
    }

    public String getAchievementId() {
        return achievementId;
    }

    public List<InfoAchievementStage> getInfoAchievementStageList() {
        return infoAchievementStageList;
    }

    public void setInfoAchievementStageList(List<InfoAchievementStage> infoAchievementStageList) {
        this.infoAchievementStageList = infoAchievementStageList;
    }

    public boolean usesPoints() {
        return usesPoints;
    }

    public void setUsesPoints(boolean usesPoints) {
        this.usesPoints = usesPoints;
    }
}
