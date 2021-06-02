package ch.mixin.mixedAchievements.inventory;

import ch.mixin.mixedAchievements.api.InfoAchievement;
import ch.mixin.mixedAchievements.blueprint.AchievementItemSetup;
import ch.mixin.mixedAchievements.main.MixedAchievementsManagerAccessor;

import java.util.List;

public class InventoryAchievementLeaf extends InventoryAchievementElement {
    private final InfoAchievement infoAchievement;
    private final List<AchievementItemSetup> achievementItemSetupList;

    public InventoryAchievementLeaf(MixedAchievementsManagerAccessor mixedAchievementsManagerAccessor, InventoryAchievementCategory parent, InfoAchievement infoAchievement, List<AchievementItemSetup> achievementItemSetupList) {
        super(mixedAchievementsManagerAccessor, parent);
        this.infoAchievement= infoAchievement;
        this.achievementItemSetupList = achievementItemSetupList;
    }

    public InfoAchievement getInfoAchievement() {
        return infoAchievement;
    }

    public List<AchievementItemSetup> getAchievementItemSetupList() {
        return achievementItemSetupList;
    }
}
