package ch.mixin.mixedAchievements.inventory;

import ch.mixin.mixedAchievements.blueprint.AchievementItemSetup;
import ch.mixin.mixedAchievements.main.MixedAchievementsManagerAccessor;

public abstract class AchievementInventoryElement {
    protected final MixedAchievementsManagerAccessor mixedAchievementsManagerAccessor;
    protected final AchievementInventoryFolderElement parent;
    protected final AchievementItemSetup achievementItemSetup;

    public AchievementInventoryElement(MixedAchievementsManagerAccessor mixedAchievementsManagerAccessor, AchievementInventoryFolderElement parent, AchievementItemSetup achievementItemSetup) {
        this.mixedAchievementsManagerAccessor = mixedAchievementsManagerAccessor;
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
