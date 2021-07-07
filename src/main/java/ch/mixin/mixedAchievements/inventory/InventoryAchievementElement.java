package ch.mixin.mixedAchievements.inventory;

import ch.mixin.mixedAchievements.main.MixedAchievementsData;

public abstract class InventoryAchievementElement {
    protected final MixedAchievementsData mixedAchievementsData;
    protected final InventoryAchievementCategory parent;

    public InventoryAchievementElement(MixedAchievementsData mixedAchievementsData, InventoryAchievementCategory parent) {
        this.mixedAchievementsData = mixedAchievementsData;
        this.parent = parent;
    }

    public InventoryAchievementCategory getParent() {
        return parent;
    }
}
