package ch.mixin.mixedAchievements.api;

import java.util.TreeMap;

public class AchievementSetInfo {
    private final TreeMap<String, AchievementInfo> achievementInfoMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public TreeMap<String, AchievementInfo> getAchievementInfoMap() {
        return achievementInfoMap;
    }
}
