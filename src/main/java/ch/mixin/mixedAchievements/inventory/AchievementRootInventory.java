package ch.mixin.mixedAchievements.inventory;

import ch.mixin.mixedAchievements.blueprint.AchievementItemSetup;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.TreeMap;

public class AchievementRootInventory extends AchievementInventoryFolderElement {
    private final TreeMap<String, AchievementInventoryFolderElement> achievementSetInventoryMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public AchievementRootInventory() {
        super(null, new AchievementItemSetup(), "Mixed Achievements");
    }

    @Override
    public Inventory generateInventory(Player player) {
        subAchievementInventoryElementMap = new HashMap<>();
        int slot = 0;

        for (String setName : achievementSetInventoryMap.keySet()) {
            AchievementInventoryElement aie = achievementSetInventoryMap.get(setName);
            subAchievementInventoryElementMap.put(slot, aie);
            slot++;
        }

        return super.generateInventory(player);
    }

    public TreeMap<String, AchievementInventoryFolderElement> getAchievementSetInventoryMap() {
        return achievementSetInventoryMap;
    }
}
