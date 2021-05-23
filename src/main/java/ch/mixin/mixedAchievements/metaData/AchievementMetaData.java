package ch.mixin.mixedAchievements.metaData;

import java.util.TreeMap;

public class AchievementMetaData {
    private TreeMap<String, AchievementData> achievementDataMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public TreeMap<String, AchievementData> getAchievementDataMap() {
        return achievementDataMap;
    }

    public void setAchievementDataMap(TreeMap<String, AchievementData> achievementDataMap) {
        this.achievementDataMap = achievementDataMap;
    }
}
