package ch.mixin.mixedAchievements.data;

import org.bukkit.configuration.ConfigurationSection;

public class DataPlayerAchievement {
    private final DataAchievement parent;
    private final String PlayerId;
    private int stage = 0;
    private int points = 0;

    public DataPlayerAchievement(DataAchievement parent, String playerId) {
        this.parent = parent;
        PlayerId = playerId;
    }

    public DataPlayerAchievement(DataAchievement parent, String playerId, ConfigurationSection dataPlayerAchievementConfig) {
        this.parent = parent;
        PlayerId = playerId;
        loadFromConfig(dataPlayerAchievementConfig);
    }

    private void loadFromConfig(ConfigurationSection dataPlayerAchievementConfig) {
        if (dataPlayerAchievementConfig.contains("stage"))
            stage = dataPlayerAchievementConfig.getInt("stage");

        if (dataPlayerAchievementConfig.contains("points"))
            points = dataPlayerAchievementConfig.getInt("points");
    }

    public void saveToConfig(ConfigurationSection dataPlayerAchievementConfig) {
        dataPlayerAchievementConfig.set("stage", stage);
        dataPlayerAchievementConfig.set("points", points);
    }

    public DataAchievement getParent() {
        return parent;
    }

    public String getPlayerId() {
        return PlayerId;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
