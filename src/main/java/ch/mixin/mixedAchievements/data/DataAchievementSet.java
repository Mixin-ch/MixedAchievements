package ch.mixin.mixedAchievements.data;

import org.bukkit.configuration.ConfigurationSection;

import java.util.TreeMap;

public class DataAchievementSet {
    private final DataAchievementRoot parent;
    private final String setId;
    private TreeMap<String, DataAchievement> dataAchievementMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public DataAchievementSet(DataAchievementRoot parent, String setId) {
        this.parent = parent;
        this.setId = setId;
    }

    public DataAchievementSet(DataAchievementRoot parent, String setId, ConfigurationSection dataAchievementSetConfig) {
        this.parent = parent;
        this.setId = setId;
        loadFromConfig(dataAchievementSetConfig);
    }

    private void loadFromConfig(ConfigurationSection dataAchievementSetConfig) {
        for (String achievementId : dataAchievementSetConfig.getKeys(false)) {
            ConfigurationSection dataAchievementConfig = dataAchievementSetConfig.getConfigurationSection(achievementId);

            if (dataAchievementConfig == null)
                continue;

            dataAchievementMap.put(achievementId, new DataAchievement(this, achievementId, dataAchievementConfig));
        }
    }

    public void saveToConfig(ConfigurationSection dataAchievementSetConfig) {
        for (String achievementName : dataAchievementMap.keySet()) {
            DataAchievement dataAchievement = dataAchievementMap.get(achievementName);
            ConfigurationSection dataAchievementConfig = dataAchievementSetConfig.createSection(achievementName);
            dataAchievement.saveToConfig(dataAchievementConfig);
        }
    }

    public DataAchievementRoot getParent() {
        return parent;
    }

    public String getSetId() {
        return setId;
    }

    public TreeMap<String, DataAchievement> getDataAchievementMap() {
        return dataAchievementMap;
    }

    public void setDataAchievementMap(TreeMap<String, DataAchievement> dataAchievementMap) {
        this.dataAchievementMap = dataAchievementMap;
    }
}
