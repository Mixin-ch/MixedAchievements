package ch.mixin.mixedAchievements.inventory;

import ch.mixin.mixedAchievements.api.AchievementManager;
import ch.mixin.mixedAchievements.blueprint.AchievementItemSetup;

public abstract class AchievementInventoryElement {
    protected final AchievementManager achievementManager;
    protected final AchievementInventoryFolderElement parent;
    protected final AchievementItemSetup achievementItemSetup;

    public AchievementInventoryElement(AchievementManager achievementManager, AchievementInventoryFolderElement parent, AchievementItemSetup achievementItemSetup) {
        this.achievementManager = achievementManager;
        this.parent = parent;
        this.achievementItemSetup = achievementItemSetup;
    }

    public AchievementInventoryFolderElement getParent() {
        return parent;
    }

    public AchievementItemSetup getAchievementItemSetup() {
        return achievementItemSetup;
    }
}
