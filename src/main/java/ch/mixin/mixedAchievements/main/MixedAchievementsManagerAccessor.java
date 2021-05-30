package ch.mixin.mixedAchievements.main;

import ch.mixin.mixedAchievements.api.AchievementManager;
import ch.mixin.mixedAchievements.inventory.InventoryAchievementManager;
import ch.mixin.mixedAchievements.data.DataAchievementManager;

public class MixedAchievementsManagerAccessor {
    private final MixedAchievementsPlugin plugin;
    private AchievementManager achievementManager;
    private DataAchievementManager dataAchievementManager;
    private InventoryAchievementManager inventoryAchievementManager;

    public MixedAchievementsManagerAccessor(MixedAchievementsPlugin plugin) {
        this.plugin = plugin;
    }

    public MixedAchievementsPlugin getPlugin() {
        return plugin;
    }

    public AchievementManager getAchievementManager() {
        return achievementManager;
    }

    public void setAchievementManager(AchievementManager achievementManager) {
        this.achievementManager = achievementManager;
    }

    public DataAchievementManager getAchievementDataManager() {
        return dataAchievementManager;
    }

    public void setAchievementDataManager(DataAchievementManager dataAchievementManager) {
        this.dataAchievementManager = dataAchievementManager;
    }

    public InventoryAchievementManager getAchievementInventoryManager() {
        return inventoryAchievementManager;
    }

    public void setAchievementInventoryManager(InventoryAchievementManager inventoryAchievementManager) {
        this.inventoryAchievementManager = inventoryAchievementManager;
    }
}
