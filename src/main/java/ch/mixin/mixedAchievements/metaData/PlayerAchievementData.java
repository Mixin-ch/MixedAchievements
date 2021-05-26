package ch.mixin.mixedAchievements.metaData;

import org.bukkit.configuration.ConfigurationSection;

public class PlayerAchievementData {
    private boolean achieved = false;
    private int points = 0;

    public PlayerAchievementData() {
    }

    public PlayerAchievementData(ConfigurationSection playerAchievementDataConfig) {
        loadFromConfig(playerAchievementDataConfig);
    }

    private void loadFromConfig(ConfigurationSection playerAchievementDataConfig) {
        if (playerAchievementDataConfig.contains("achieved"))
            achieved = playerAchievementDataConfig.getBoolean("achieved");

        if (playerAchievementDataConfig.contains("points"))
            points = playerAchievementDataConfig.getInt("points");
    }

    public void saveToConfig(ConfigurationSection playerAchievementDataConfig) {
        playerAchievementDataConfig.set("achieved", achieved);
        playerAchievementDataConfig.set("points", points);
    }

    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
