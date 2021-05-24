package ch.mixin.mixedAchievements.inventory;

import ch.mixin.mixedAchievements.blueprint.AchievementItemSetup;

import java.util.TreeMap;

public class AchievementRootInventory {
    private final TreeMap<String, AchievementInventoryFolderElement> achievementSetInventoryMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public TreeMap<String, AchievementInventoryFolderElement> getAchievementSetInventoryMap() {
        return achievementSetInventoryMap;
    }
}
