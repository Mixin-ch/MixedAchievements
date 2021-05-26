package ch.mixin.eventListener;

import ch.mixin.mixedAchievements.inventory.AchievementInventoryManager;
import ch.mixin.mixedAchievements.main.MixedAchievementsPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class InventoryListener implements Listener {
    protected final MixedAchievementsPlugin plugin;
    protected final AchievementInventoryManager achievementInventoryManager;

    public InventoryListener(MixedAchievementsPlugin plugin, AchievementInventoryManager achievementInventoryManager) {
        this.plugin = plugin;
        this.achievementInventoryManager = achievementInventoryManager;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        achievementInventoryManager.click(event);
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        achievementInventoryManager.drag(event);
    }
}
