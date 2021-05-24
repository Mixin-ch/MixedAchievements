package ch.mixin.mixedAchievements.inventory;

import ch.mixin.mixedAchievements.blueprint.AchievementItemSetup;

public abstract class AchievementInventoryElement {
    protected final AchievementInventoryElement parent;
    protected final AchievementItemSetup achievementItemSetup;

    public AchievementInventoryElement(AchievementInventoryElement parent, AchievementItemSetup achievementItemSetup) {
        this.parent = parent;
        this.achievementItemSetup = achievementItemSetup;
    }

    public AchievementInventoryElement getParent() {
        return parent;
    }

    public AchievementItemSetup getAchievementItemSetup() {
        return achievementItemSetup;
    }
}
