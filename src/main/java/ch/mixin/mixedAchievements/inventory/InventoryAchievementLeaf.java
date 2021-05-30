package ch.mixin.mixedAchievements.inventory;

import ch.mixin.mixedAchievements.api.InfoAchievement;
import ch.mixin.mixedAchievements.blueprint.AchievementItemSetup;
import ch.mixin.mixedAchievements.main.MixedAchievementsManagerAccessor;

import java.util.List;

public class InventoryAchievementLeaf extends InventoryAchievementElement {
    private InfoAchievement infoAchievement;
    private final List<AchievementItemSetup> achievementItemSetupList;

    public InventoryAchievementLeaf(MixedAchievementsManagerAccessor mixedAchievementsManagerAccessor, InventoryAchievementCategory parent, List<AchievementItemSetup> achievementItemSetupList) {
        super(mixedAchievementsManagerAccessor, parent);
        this.achievementItemSetupList = achievementItemSetupList;
    }

    public InfoAchievement getInfoAchievement() {
        return infoAchievement;
    }

    public void setInfoAchievement(InfoAchievement infoAchievement) {
        this.infoAchievement = infoAchievement;
    }

    public List<AchievementItemSetup> getAchievementItemSetupList() {
        return achievementItemSetupList;
    }
}
