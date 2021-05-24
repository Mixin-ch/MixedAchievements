package ch.mixin.mixedAchievements.inventory;

import ch.mixin.mixedAchievements.api.AchievementInfo;
import ch.mixin.mixedAchievements.blueprint.AchievementItemSetup;

public class AchievementInventoryLeafElement extends AchievementInventoryElement {
    private AchievementInfo achievementInfo;

    public AchievementInventoryLeafElement(AchievementItemSetup achievementItemSetup) {
        super(achievementItemSetup);
    }

    public AchievementInfo getAchievementInfo() {
        return achievementInfo;
    }

    public void setAchievementInfo(AchievementInfo achievementInfo) {
        this.achievementInfo = achievementInfo;
    }
}
