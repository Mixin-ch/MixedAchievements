package ch.mixin.mixedAchievements.inventory;

import ch.mixin.mixedAchievements.blueprint.AchievementItemSetup;
import ch.mixin.mixedAchievements.main.MixedAchievementsManagerAccessor;

public class InventoryAchievementSet extends InventoryAchievementCategory {
    private final String setId;

    public InventoryAchievementSet(MixedAchievementsManagerAccessor mixedAchievementsManagerAccessor, InventoryAchievementCategory parent, String setId, String inventoryName, AchievementItemSetup achievementItemSetup) {
        super(mixedAchievementsManagerAccessor, parent, inventoryName, achievementItemSetup);
        this.setId = setId;
    }

    public String getSetId() {
        return setId;
    }
}
