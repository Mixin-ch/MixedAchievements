package ch.mixin.mixedAchievements.blueprint;

import java.util.HashMap;

public class AchievementBlueprintFolderElement extends AchievementBlueprintElement {
    protected String inventoryName;
    protected HashMap<Integer, AchievementBlueprintElement> subAchievementBlueprintElementMap;

    public AchievementBlueprintFolderElement(AchievementItemSetup achievementItemSetup, String inventoryName) {
        super(achievementItemSetup);
        this.inventoryName = inventoryName;
        subAchievementBlueprintElementMap = new HashMap<>();
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    public HashMap<Integer, AchievementBlueprintElement> getSubAchievementBlueprintElementMap() {
        return subAchievementBlueprintElementMap;
    }

    public void setSubAchievementBlueprintElementMap(HashMap<Integer, AchievementBlueprintElement> subAchievementBlueprintElementMap) {
        this.subAchievementBlueprintElementMap = subAchievementBlueprintElementMap;
    }
}
