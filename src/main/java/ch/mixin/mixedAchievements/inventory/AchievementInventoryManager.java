package ch.mixin.mixedAchievements.inventory;

public class AchievementInventoryManager {
    private final AchievementRootInventory achievementRootInventory = new AchievementRootInventory();

    public AchievementRootInventory getAchievementRootInventory() {
        return achievementRootInventory;
    }

    public void reload() {
        //TODO: close Inventory
    }
}
