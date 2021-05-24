package ch.mixin.mixedAchievements.inventory;

public class AchievementInventoryManager {
    private final AchievementRootInventory achievementRootInventory = new AchievementRootInventory();

    public AchievementInventoryManager() {
        reload();
    }

    public AchievementRootInventory getAchievementRootInventory() {
        return achievementRootInventory;
    }

    public void reload() {
        achievementRootInventory.close();
        achievementRootInventory.generate();
    }
}
