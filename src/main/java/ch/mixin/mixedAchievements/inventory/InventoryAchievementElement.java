package ch.mixin.mixedAchievements.inventory;

import ch.mixin.mixedAchievements.main.MixedAchievementsManagerAccessor;

public abstract class InventoryAchievementElement {
    protected final MixedAchievementsManagerAccessor mixedAchievementsManagerAccessor;
    protected final InventoryAchievementCategory parent;

    public InventoryAchievementElement(MixedAchievementsManagerAccessor mixedAchievementsManagerAccessor, InventoryAchievementCategory parent) {
        this.mixedAchievementsManagerAccessor = mixedAchievementsManagerAccessor;
        this.parent = parent;
    }

    public InventoryAchievementCategory getParent() {
        return parent;
    }
}
