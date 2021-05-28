package ch.mixin.mixedAchievements.main;

import ch.mixin.mixedAchievements.api.AchievementManager;
import ch.mixin.mixedAchievements.inventory.AchievementInventoryManager;
import ch.mixin.mixedAchievements.metaData.AchievementDataManager;

public class MixedAchievementsManagerAccessor {
    private final MixedAchievementsPlugin plugin;
    private AchievementManager achievementManager;
    private AchievementDataManager achievementDataManager;
    private AchievementInventoryManager achievementInventoryManager;

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

    public AchievementDataManager getAchievementDataManager() {
        return achievementDataManager;
    }

    public void setAchievementDataManager(AchievementDataManager achievementDataManager) {
        this.achievementDataManager = achievementDataManager;
    }

    public AchievementInventoryManager getAchievementInventoryManager() {
        return achievementInventoryManager;
    }

    public void setAchievementInventoryManager(AchievementInventoryManager achievementInventoryManager) {
        this.achievementInventoryManager = achievementInventoryManager;
    }
}
