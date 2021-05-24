package ch.mixin.mixedAchievements.inventory;

import ch.mixin.mixedAchievements.blueprint.AchievementItemSetup;

import java.util.HashMap;

public class AchievementInventoryFolderElement extends AchievementInventoryElement {
    protected String inventoryName;
    protected HashMap<Integer, AchievementInventoryElement> subAchievementInventoryElementMap;

    public AchievementInventoryFolderElement(AchievementItemSetup achievementItemSetup, String inventoryName) {
        super(achievementItemSetup);
        this.inventoryName = inventoryName;
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    public HashMap<Integer, AchievementInventoryElement> getSubAchievementInventoryElementMap() {
        return subAchievementInventoryElementMap;
    }

    public void setSubAchievementInventoryElementMap(HashMap<Integer, AchievementInventoryElement> subAchievementInventoryElementMap) {
        this.subAchievementInventoryElementMap = subAchievementInventoryElementMap;
    }
}
