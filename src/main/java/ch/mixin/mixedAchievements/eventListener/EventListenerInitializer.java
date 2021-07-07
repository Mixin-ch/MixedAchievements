package ch.mixin.mixedAchievements.eventListener;

import ch.mixin.mixedAchievements.main.MixedAchievementsData;
import ch.mixin.mixedAchievements.main.MixedAchievementsPlugin;

public class EventListenerInitializer {
    public static void setupEventListener(MixedAchievementsData mixedAchievementsData) {
        MixedAchievementsPlugin plugin = mixedAchievementsData.getPlugin();
        plugin.getServer().getPluginManager().registerEvents(new InventoryListener(mixedAchievementsData), plugin);
    }
}
