package ch.mixin.mixedAchievements.eventListener;

import ch.mixin.mixedAchievements.inventory.AchievementInventoryManager;
import ch.mixin.mixedAchievements.main.MixedAchievementsPlugin;

public class EventListenerInitializer {
    public static void setupEventListener(MixedAchievementsPlugin plugin, AchievementInventoryManager achievementInventoryManager) {
        plugin.getServer().getPluginManager().registerEvents(new InventoryListener(plugin, achievementInventoryManager), plugin);
    }
}
