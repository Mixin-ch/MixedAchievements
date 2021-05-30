package ch.mixin.mixedAchievements.inventory;

import org.bukkit.inventory.Inventory;

public class ActiveInventoryAchievement {
    private final InventoryAchievementCategory inventoryAchievementCategory;
    private final Inventory inventory;

    public ActiveInventoryAchievement(InventoryAchievementCategory inventoryAchievementCategory, Inventory inventory) {
        this.inventoryAchievementCategory = inventoryAchievementCategory;
        this.inventory = inventory;
    }

    public InventoryAchievementCategory getAchievementInventoryFolderElement() {
        return inventoryAchievementCategory;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
