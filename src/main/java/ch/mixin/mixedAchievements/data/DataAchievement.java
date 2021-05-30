package ch.mixin.mixedAchievements.data;

import org.bukkit.configuration.ConfigurationSection;

import java.util.TreeMap;

public class DataAchievement {
    private final DataAchievementSet parent;
    private final String achievementId;
    private TreeMap<String, DataPlayerAchievement> dataPlayerAchievementMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public DataAchievement(DataAchievementSet parent, String achievementId) {
        this.parent = parent;
        this.achievementId = achievementId;
    }

    public DataAchievement(DataAchievementSet parent, String achievementId, ConfigurationSection dataAchievementConfig) {
        this.parent = parent;
        this.achievementId = achievementId;
        loadFromConfig(dataAchievementConfig);
    }

    private void loadFromConfig(ConfigurationSection dataAchievementConfig) {
        for (String playerId : dataAchievementConfig.getKeys(false)) {
            ConfigurationSection dataPlayerAchievementConfig = dataAchievementConfig.getConfigurationSection(playerId);

            if (dataPlayerAchievementConfig == null)
                continue;

            dataPlayerAchievementMap.put(playerId, new DataPlayerAchievement(this, playerId, dataPlayerAchievementConfig));
        }
    }

    public void saveToConfig(ConfigurationSection dataAchievementConfig) {
        for (String playerId : dataPlayerAchievementMap.keySet()) {
            DataPlayerAchievement dataPlayerAchievement = dataPlayerAchievementMap.get(playerId);
            ConfigurationSection dataPlayerAchievementConfig = dataAchievementConfig.createSection(playerId);
            dataPlayerAchievement.saveToConfig(dataPlayerAchievementConfig);
        }
    }

    public DataAchievementSet getParent() {
        return parent;
    }

    public String getAchievementId() {
        return achievementId;
    }

    public TreeMap<String, DataPlayerAchievement> getDataPlayerAchievementMap() {
        return dataPlayerAchievementMap;
    }

    public void setDataPlayerAchievementMap(TreeMap<String, DataPlayerAchievement> dataPlayerAchievementMap) {
        this.dataPlayerAchievementMap = dataPlayerAchievementMap;
    }
}
