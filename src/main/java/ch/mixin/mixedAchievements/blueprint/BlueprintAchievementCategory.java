package ch.mixin.mixedAchievements.blueprint;

import java.util.HashMap;

public class BlueprintAchievementCategory extends BlueprintAchievementElement {
    protected final String inventoryName;
    protected final AchievementItemSetup achievementItemSetup;
    protected final HashMap<Integer, BlueprintAchievementElement> blueprintAchievementElementMap;

    public BlueprintAchievementCategory(String inventoryName, AchievementItemSetup achievementItemSetup) {
        this.achievementItemSetup = achievementItemSetup;
        this.inventoryName = inventoryName;
        blueprintAchievementElementMap = new HashMap<>();
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public AchievementItemSetup getAchievementItemSetup() {
        return achievementItemSetup;
    }

    public HashMap<Integer, BlueprintAchievementElement> getBlueprintAchievementElementMap() {
        return blueprintAchievementElementMap;
    }
}
