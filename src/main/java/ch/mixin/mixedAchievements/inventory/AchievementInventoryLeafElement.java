package ch.mixin.mixedAchievements.inventory;

import ch.mixin.mixedAchievements.api.AchievementInfo;
import ch.mixin.mixedAchievements.blueprint.AchievementItemSetup;
import ch.mixin.mixedAchievements.main.MixedAchievementsManagerAccessor;

public class AchievementInventoryLeafElement extends AchievementInventoryElement {
    private AchievementInfo achievementInfo;

    public AchievementInventoryLeafElement(MixedAchievementsManagerAccessor mixedAchievementsManagerAccessor, AchievementInventoryFolderElement parent, AchievementItemSetup achievementItemSetup) {
        super(mixedAchievementsManagerAccessor, parent, achievementItemSetup);
    }

    public AchievementInfo getAchievementInfo() {
        return achievementInfo;
    }

    public void setAchievementInfo(AchievementInfo achievementInfo) {
        this.achievementInfo = achievementInfo;
    }
}
