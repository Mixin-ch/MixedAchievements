package ch.mixin.mixedAchievements.achievement;

import java.util.TreeMap;

public class AchievementSetData {
    private final TreeMap<String, AchievementData> achievementGroupDataMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private final TreeMap<String, AchievementData> achievementPlayerDataMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
}
