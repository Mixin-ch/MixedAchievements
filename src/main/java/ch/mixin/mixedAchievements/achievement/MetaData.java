package ch.mixin.mixedAchievements.achievement;

import java.util.TreeMap;

public class MetaData {
    private final TreeMap<String, AchievementSetData> achievementSetDataMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private final TreeMap<String, PlayerData> playerDataMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
}
