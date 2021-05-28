package ch.mixin.mixedAchievements.eventListener;

import ch.mixin.mixedAchievements.main.MixedAchievementsManagerAccessor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class InventoryListener implements Listener {
    protected final MixedAchievementsManagerAccessor mixedAchievementsManagerAccessor;

    public InventoryListener(MixedAchievementsManagerAccessor mixedAchievementsManagerAccessor) {
        this.mixedAchievementsManagerAccessor = mixedAchievementsManagerAccessor;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        mixedAchievementsManagerAccessor.getAchievementInventoryManager().click(event);
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        mixedAchievementsManagerAccessor.getAchievementInventoryManager().drag(event);
    }
}
