package ch.mixin.mixedAchievements.api;

import java.util.TreeMap;

public class InfoAchievementSet {
    private final TreeMap<String, InfoAchievement> infoAchievementMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public TreeMap<String, InfoAchievement> getInfoAchievementMap() {
        return infoAchievementMap;
    }
}
