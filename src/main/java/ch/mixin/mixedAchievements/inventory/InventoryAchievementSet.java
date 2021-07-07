package ch.mixin.mixedAchievements.inventory;

import ch.mixin.mixedAchievements.blueprint.AchievementItemSetup;
import ch.mixin.mixedAchievements.main.MixedAchievementsData;

public class InventoryAchievementSet extends InventoryAchievementCategory {
    private final String setId;

    public InventoryAchievementSet(MixedAchievementsData mixedAchievementsData, InventoryAchievementCategory parent, String setId, String inventoryName, AchievementItemSetup achievementItemSetup) {
        super(mixedAchievementsData, parent, inventoryName, achievementItemSetup);
        this.setId = setId;
    }

    public String getSetId() {
        return setId;
    }
}
