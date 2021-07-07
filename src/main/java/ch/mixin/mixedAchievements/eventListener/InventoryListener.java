package ch.mixin.mixedAchievements.eventListener;

import ch.mixin.mixedAchievements.main.MixedAchievementsData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class InventoryListener implements Listener {
    protected final MixedAchievementsData mixedAchievementsData;

    public InventoryListener(MixedAchievementsData mixedAchievementsData) {
        this.mixedAchievementsData = mixedAchievementsData;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        mixedAchievementsData.getInventoryAchievementManager().click(event);
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        mixedAchievementsData.getInventoryAchievementManager().drag(event);
    }
}
