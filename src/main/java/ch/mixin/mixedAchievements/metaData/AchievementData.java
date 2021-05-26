package ch.mixin.mixedAchievements.metaData;

import org.bukkit.configuration.ConfigurationSection;

import java.util.TreeMap;

public class AchievementData {
    private TreeMap<String, PlayerAchievementData> playerAchievementDataMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public AchievementData() {
    }

    public AchievementData(ConfigurationSection achievementDataConfig) {
        loadFromConfig(achievementDataConfig);
    }

    private void loadFromConfig(ConfigurationSection achievementDataConfig) {
        for (String playerId : achievementDataConfig.getKeys(false)) {
            ConfigurationSection playerAchievementDataConfig = achievementDataConfig.getConfigurationSection(playerId);

            if (playerAchievementDataConfig == null)
                continue;

            playerAchievementDataMap.put(playerId, new PlayerAchievementData(playerAchievementDataConfig));
        }
    }

    public void saveToConfig(ConfigurationSection achievementDataConfig) {
        for (String playerId : playerAchievementDataMap.keySet()) {
            PlayerAchievementData playerAchievementData = playerAchievementDataMap.get(playerId);
            ConfigurationSection playerAchievementDataConfig = achievementDataConfig.createSection(playerId);
            playerAchievementData.saveToConfig(playerAchievementDataConfig);
        }
    }

    public TreeMap<String, PlayerAchievementData> getPlayerAchievementDataMap() {
        return playerAchievementDataMap;
    }

    public void setPlayerAchievementDataMap(TreeMap<String, PlayerAchievementData> playerAchievementDataMap) {
        this.playerAchievementDataMap = playerAchievementDataMap;
    }
}
