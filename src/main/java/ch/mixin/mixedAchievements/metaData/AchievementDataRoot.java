package ch.mixin.mixedAchievements.metaData;

import org.bukkit.configuration.ConfigurationSection;

import java.util.TreeMap;

public class AchievementDataRoot {
    private TreeMap<String, AchievementSetData> achievementSetDataMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public AchievementDataRoot() {
    }

    public AchievementDataRoot(ConfigurationSection achievementDataRootConfig) {
        loadFromConfig(achievementDataRootConfig);
    }

    private void loadFromConfig(ConfigurationSection achievementDataRootConfig) {
        for (String setName : achievementDataRootConfig.getKeys(false)) {
            ConfigurationSection achievementSetDataConfig = achievementDataRootConfig.getConfigurationSection(setName);

            if (achievementSetDataConfig == null)
                continue;

            achievementSetDataMap.put(setName, new AchievementSetData(achievementSetDataConfig));
        }
    }

    public void saveToConfig(ConfigurationSection achievementDataRootConfig) {
        for (String setName : achievementSetDataMap.keySet()) {
            AchievementSetData achievementSetData = achievementSetDataMap.get(setName);
            ConfigurationSection achievementSetDataConfig = achievementDataRootConfig.createSection(setName);
            achievementSetData.saveToConfig(achievementSetDataConfig);
        }
    }

    public TreeMap<String, AchievementSetData> getAchievementSetDataMap() {
        return achievementSetDataMap;
    }

    public void setAchievementSetDataMap(TreeMap<String, AchievementSetData> achievementSetDataMap) {
        this.achievementSetDataMap = achievementSetDataMap;
    }
}
