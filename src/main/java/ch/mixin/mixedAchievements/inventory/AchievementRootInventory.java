package ch.mixin.mixedAchievements.inventory;

import ch.mixin.mixedAchievements.blueprint.AchievementItemSetup;

import java.util.HashMap;
import java.util.TreeMap;

public class AchievementRootInventory extends AchievementInventoryFolderElement {
    private final TreeMap<String, AchievementInventoryFolderElement> achievementSetInventoryMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public AchievementRootInventory() {
        super(new AchievementItemSetup(), "Mixed Achievements");
    }

    @Override
    public void generate() {
        subAchievementInventoryElementMap = new HashMap<>();
        int slot = 0;

        for (String setName : achievementSetInventoryMap.keySet()) {
            AchievementInventoryElement aie = achievementSetInventoryMap.get(setName);
            subAchievementInventoryElementMap.put(slot, aie);
            slot++;
        }

        super.generate();
    }

    public TreeMap<String, AchievementInventoryFolderElement> getAchievementSetInventoryMap() {
        return achievementSetInventoryMap;
    }
}
