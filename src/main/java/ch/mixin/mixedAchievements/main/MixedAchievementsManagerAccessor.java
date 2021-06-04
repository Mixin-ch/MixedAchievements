package ch.mixin.mixedAchievements.main;

import ch.mixin.mixedAchievements.api.AchievementManager;
import ch.mixin.mixedAchievements.event.AchievementEventManager;
import ch.mixin.mixedAchievements.inventory.InventoryAchievementManager;
import ch.mixin.mixedAchievements.data.DataAchievementManager;

public class MixedAchievementsManagerAccessor {
    private final MixedAchievementsPlugin plugin;
    private AchievementManager achievementManager;
    private DataAchievementManager dataAchievementManager;
    private InventoryAchievementManager inventoryAchievementManager;
    private AchievementEventManager achievementEventManager;

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

    public DataAchievementManager getDataAchievementManager() {
        return dataAchievementManager;
    }

    public void setDataAchievementManager(DataAchievementManager dataAchievementManager) {
        this.dataAchievementManager = dataAchievementManager;
    }

    public InventoryAchievementManager getInventoryAchievementManager() {
        return inventoryAchievementManager;
    }

    public void setInventoryAchievementManager(InventoryAchievementManager inventoryAchievementManager) {
        this.inventoryAchievementManager = inventoryAchievementManager;
    }

    public AchievementEventManager getAchievementEventManager() {
        return achievementEventManager;
    }

    public void setAchievementEventManager(AchievementEventManager achievementEventManager) {
        this.achievementEventManager = achievementEventManager;
    }
}
