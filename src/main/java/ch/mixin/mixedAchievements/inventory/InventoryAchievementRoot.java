package ch.mixin.mixedAchievements.inventory;

import ch.mixin.mixedAchievements.blueprint.AchievementItemSetup;
import ch.mixin.mixedAchievements.main.MixedAchievementsData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.TreeMap;

public class InventoryAchievementRoot extends InventoryAchievementCategory {
    private final TreeMap<String, InventoryAchievementSet> inventoryAchievementSetMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public InventoryAchievementRoot(MixedAchievementsData mixedAchievementsData) {
        super(mixedAchievementsData, null, "Mixed Achievements", new AchievementItemSetup());
    }

    @Override
    public Inventory generateInventory(Player player) {
        inventoryAchievementElementMap = new HashMap<>();
        int slot = 0;

        for (String setId : inventoryAchievementSetMap.keySet()) {
            if (slot == InventoryAchievementManager.CancelSlot)
                slot++;

            InventoryAchievementSet ais = inventoryAchievementSetMap.get(setId);
            inventoryAchievementElementMap.put(slot, ais);
            slot++;
        }

        return super.generateInventory(player);
    }

    public TreeMap<String, InventoryAchievementSet> getInventoryAchievementSetMap() {
        return inventoryAchievementSetMap;
    }
}
