package ch.mixin.mixedAchievements.blueprint;

import java.util.ArrayList;
import java.util.List;

public class BlueprintAchievementLeaf extends BlueprintAchievementElement {
    private final String achievementId;
    private final List<BlueprintAchievementStage> blueprintAchievementStageList;

    public BlueprintAchievementLeaf(String achievementId, List<BlueprintAchievementStage> blueprintAchievementStageList) {
        this.achievementId = achievementId;
        this.blueprintAchievementStageList = blueprintAchievementStageList;
    }

    public static BlueprintAchievementLeaf createSimple(String achievementId, AchievementItemSetup achievementItemSetup) {
        List<BlueprintAchievementStage> blueprintAchievementStageList = new ArrayList<>();
        blueprintAchievementStageList.add(new BlueprintAchievementStage(achievementItemSetup));
        return new BlueprintAchievementLeaf(achievementId, blueprintAchievementStageList);
    }

    public static BlueprintAchievementLeaf createSimplePoints(String achievementId, AchievementItemSetup achievementItemSetup, int maxPoints) {
        List<BlueprintAchievementStage> blueprintAchievementStageList = new ArrayList<>();
        blueprintAchievementStageList.add(new BlueprintAchievementStage(achievementItemSetup, maxPoints));
        return new BlueprintAchievementLeaf(achievementId, blueprintAchievementStageList);
    }

    public static BlueprintAchievementLeaf createList(String achievementId, List<AchievementItemSetup> achievementItemSetupList) {
        List<BlueprintAchievementStage> blueprintAchievementStageList = new ArrayList<>();

        for (AchievementItemSetup ais : achievementItemSetupList) {
            blueprintAchievementStageList.add(new BlueprintAchievementStage(ais));
        }

        return new BlueprintAchievementLeaf(achievementId, blueprintAchievementStageList);
    }

    public static BlueprintAchievementLeaf createListPoints(String achievementId, List<BlueprintAchievementStage> blueprintAchievementStageList) {
        return new BlueprintAchievementLeaf(achievementId, blueprintAchievementStageList);
    }

    public String getAchievementId() {
        return achievementId;
    }

    public List<BlueprintAchievementStage> getBlueprintAchievementStageList() {
        return blueprintAchievementStageList;
    }
}
