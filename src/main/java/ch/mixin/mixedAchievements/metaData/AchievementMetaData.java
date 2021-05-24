package ch.mixin.mixedAchievements.metaData;

import java.util.TreeMap;

public class AchievementMetaData {
    private TreeMap<String, AchievementSetData> achievementSetDataMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public TreeMap<String, AchievementSetData> getAchievementSetDataMap() {
        return achievementSetDataMap;
    }

    public void setAchievementSetDataMap(TreeMap<String, AchievementSetData> achievementSetDataMap) {
        this.achievementSetDataMap = achievementSetDataMap;
    }
}
