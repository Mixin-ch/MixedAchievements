package ch.mixin.mixedAchievements.blueprint;

import java.util.List;

public class AchievementFolderElement extends AchievementElement {
    protected List<AchievementElement> subAchievementElementList;

    public List<AchievementElement> getSubElementList() {
        return subAchievementElementList;
    }

    public void setSubElementList(List<AchievementElement> subAchievementElementList) {
        this.subAchievementElementList = subAchievementElementList;
    }
}
