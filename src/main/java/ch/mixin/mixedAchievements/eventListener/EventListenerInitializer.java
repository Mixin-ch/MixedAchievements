package ch.mixin.mixedAchievements.eventListener;

import ch.mixin.mixedAchievements.main.MixedAchievementsManagerAccessor;
import ch.mixin.mixedAchievements.main.MixedAchievementsPlugin;

public class EventListenerInitializer {
    public static void setupEventListener(MixedAchievementsManagerAccessor mixedAchievementsManagerAccessor) {
        MixedAchievementsPlugin plugin = mixedAchievementsManagerAccessor.getPlugin();
        plugin.getServer().getPluginManager().registerEvents(new InventoryListener(mixedAchievementsManagerAccessor), plugin);
    }
}
