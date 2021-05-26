package ch.mixin.mixedAchievements.metaData;

import org.bukkit.configuration.ConfigurationSection;

import java.util.TreeMap;

public class AchievementSetData {
    private TreeMap<String, AchievementData> achievementDataMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public AchievementSetData() {
    }

    public AchievementSetData(ConfigurationSection achievementSetDataConfig) {
        loadFromConfig(achievementSetDataConfig);
    }

    private void loadFromConfig(ConfigurationSection achievementSetDataConfig) {
        for (String achievementName : achievementSetDataConfig.getKeys(false)) {
            ConfigurationSection achievementDataConfig = achievementSetDataConfig.getConfigurationSection(achievementName);

            if (achievementDataConfig == null)
                continue;

            achievementDataMap.put(achievementName, new AchievementData(achievementDataConfig));
        }
    }

    public void saveToConfig(ConfigurationSection achievementSetDataConfig) {
        for (String achievementName : achievementDataMap.keySet()) {
            AchievementData achievementData = achievementDataMap.get(achievementName);
            ConfigurationSection achievementDataConfig = achievementSetDataConfig.createSection(achievementName);
            achievementData.saveToConfig(achievementDataConfig);
        }
    }

    public TreeMap<String, AchievementData> getAchievementDataMap() {
        return achievementDataMap;
    }

    public void setAchievementDataMap(TreeMap<String, AchievementData> achievementDataMap) {
        this.achievementDataMap = achievementDataMap;
    }
}
