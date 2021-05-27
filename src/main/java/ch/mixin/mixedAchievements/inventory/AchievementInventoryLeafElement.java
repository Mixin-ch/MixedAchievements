package ch.mixin.mixedAchievements.inventory;

import ch.mixin.mixedAchievements.api.AchievementInfo;
import ch.mixin.mixedAchievements.api.AchievementManager;
import ch.mixin.mixedAchievements.blueprint.AchievementItemSetup;

public class AchievementInventoryLeafElement extends AchievementInventoryElement {
    private AchievementInfo achievementInfo;

    public AchievementInventoryLeafElement(AchievementManager achievementManager, AchievementInventoryFolderElement parent, AchievementItemSetup achievementItemSetup) {
        super(achievementManager, parent, achievementItemSetup);
    }

    public AchievementInfo getAchievementInfo() {
        return achievementInfo;
    }

    public void setAchievementInfo(AchievementInfo achievementInfo) {
        this.achievementInfo = achievementInfo;
    }
}
