package ch.mixin.mixedAchievements.metaData;

import java.util.TreeMap;

public class AchievementData {
    private TreeMap<String, PlayerAchievementData> playerAchievementDataMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public TreeMap<String, PlayerAchievementData> getPlayerAchievementDataMap() {
        return playerAchievementDataMap;
    }

    public void setPlayerAchievementDataMap(TreeMap<String, PlayerAchievementData> playerAchievementDataMap) {
        this.playerAchievementDataMap = playerAchievementDataMap;
    }
}
