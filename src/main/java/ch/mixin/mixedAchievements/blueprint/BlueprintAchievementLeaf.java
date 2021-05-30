package ch.mixin.mixedAchievements.blueprint;

import java.util.ArrayList;
import java.util.List;

public class BlueprintAchievementLeaf extends BlueprintAchievementElement {
    private final String achievementId;
    private final List<BlueprintAchievementStage> blueprintAchievementStageList;
    private final boolean usesPoints;

    public BlueprintAchievementLeaf(String achievementId, AchievementItemSetup achievementItemSetup) {
        this.achievementId = achievementId;
        blueprintAchievementStageList = new ArrayList<>();
        blueprintAchievementStageList.add(new BlueprintAchievementStage(achievementItemSetup));
        usesPoints = false;
    }

    public BlueprintAchievementLeaf(AchievementItemSetup achievementItemSetup, String achievementId, int maxPoints) {
        this.achievementId = achievementId;
        blueprintAchievementStageList = new ArrayList<>();
        blueprintAchievementStageList.add(new BlueprintAchievementStage(achievementItemSetup, maxPoints));
        usesPoints = true;
    }

    public BlueprintAchievementLeaf(String achievementId, List<AchievementItemSetup> achievementItemSetupList) {
        this.achievementId = achievementId;
        blueprintAchievementStageList = new ArrayList<>();

        for (AchievementItemSetup ais : achievementItemSetupList) {
            blueprintAchievementStageList.add(new BlueprintAchievementStage(ais));
        }

        usesPoints = false;
    }

    public BlueprintAchievementLeaf(String achievementId, List<BlueprintAchievementStage> blueprintAchievementStageList, boolean usesPoints) {
        this.achievementId = achievementId;
        this.blueprintAchievementStageList = blueprintAchievementStageList;
        this.usesPoints = usesPoints;
    }

    public String getAchievementId() {
        return achievementId;
    }

    public List<BlueprintAchievementStage> getBlueprintAchievementStageList() {
        return blueprintAchievementStageList;
    }

    public boolean isUsesPoints() {
        return usesPoints;
    }
}
