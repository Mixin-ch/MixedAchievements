package ch.mixin.mixedAchievements.blueprint;

public class AchievementSetBlueprint extends AchievementBlueprintFolderElement {
    private String setName;

    public AchievementSetBlueprint(AchievementItemSetup achievementItemSetup, String inventoryName, String setName) {
        super(achievementItemSetup, inventoryName);
        this.setName = setName;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }
}
