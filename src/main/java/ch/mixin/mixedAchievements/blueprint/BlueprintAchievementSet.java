package ch.mixin.mixedAchievements.blueprint;

public class BlueprintAchievementSet extends BlueprintAchievementCategory {
    private final String setId;

    public BlueprintAchievementSet(String setId, String inventoryName, AchievementItemSetup achievementItemSetup) {
        super(inventoryName, achievementItemSetup);
        this.setId = setId;
    }

    public String getSetId() {
        return setId;
    }
}
