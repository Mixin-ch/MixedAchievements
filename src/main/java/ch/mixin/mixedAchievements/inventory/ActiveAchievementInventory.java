package ch.mixin.mixedAchievements.inventory;

import org.bukkit.inventory.Inventory;

public class ActiveAchievementInventory {
    private final AchievementInventoryFolderElement achievementInventoryFolderElement;
    private final Inventory inventory;

    public ActiveAchievementInventory(AchievementInventoryFolderElement achievementInventoryFolderElement, Inventory inventory) {
        this.achievementInventoryFolderElement = achievementInventoryFolderElement;
        this.inventory = inventory;
    }

    public AchievementInventoryFolderElement getAchievementInventoryFolderElement() {
        return achievementInventoryFolderElement;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
