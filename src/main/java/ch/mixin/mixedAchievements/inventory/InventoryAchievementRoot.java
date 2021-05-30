package ch.mixin.mixedAchievements.inventory;

import ch.mixin.mixedAchievements.blueprint.AchievementItemSetup;
import ch.mixin.mixedAchievements.main.MixedAchievementsManagerAccessor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.TreeMap;

public class InventoryAchievementRoot extends InventoryAchievementCategory {
    private final TreeMap<String, InventoryAchievementSet> inventoryAchievementSetMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public InventoryAchievementRoot(MixedAchievementsManagerAccessor mixedAchievementsManagerAccessor) {
        super(mixedAchievementsManagerAccessor, null, "Mixed Achievements", new AchievementItemSetup());
    }

    @Override
    public Inventory generateInventory(Player player) {
        inventoryAchievementElementMap = new HashMap<>();
        int slot = 0;

        for (String setId : inventoryAchievementSetMap.keySet()) {
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
