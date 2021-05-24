package ch.mixin.mixedAchievements.inventory;

import ch.mixin.mixedAchievements.blueprint.AchievementItemSetup;

public abstract class AchievementInventoryElement {
    protected final AchievementItemSetup achievementItemSetup;

    public AchievementInventoryElement(AchievementItemSetup achievementItemSetup) {
        this.achievementItemSetup = achievementItemSetup;
    }

    public AchievementItemSetup getAchievementItemSetup() {
        return achievementItemSetup;
    }
}
