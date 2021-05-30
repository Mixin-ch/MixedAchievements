package ch.mixin.mixedAchievements.data;

import org.bukkit.configuration.ConfigurationSection;

import java.util.TreeMap;

public class DataAchievementRoot {
    private TreeMap<String, DataAchievementSet> dataAchievementSetMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public DataAchievementRoot() {
    }

    public DataAchievementRoot(ConfigurationSection dataAchievementRootConfig) {
        loadFromConfig(dataAchievementRootConfig);
    }

    private void loadFromConfig(ConfigurationSection dataAchievementRootConfig) {
        for (String setId : dataAchievementRootConfig.getKeys(false)) {
            ConfigurationSection dataAchievementSetConfig = dataAchievementRootConfig.getConfigurationSection(setId);

            if (dataAchievementSetConfig == null)
                continue;

            dataAchievementSetMap.put(setId, new DataAchievementSet(this, setId, dataAchievementSetConfig));
        }
    }

    public void saveToConfig(ConfigurationSection dataAchievementRootConfig) {
        for (String setId : dataAchievementSetMap.keySet()) {
            DataAchievementSet dataAchievementSet = dataAchievementSetMap.get(setId);
            ConfigurationSection dataAchievementSetConfig = dataAchievementRootConfig.createSection(setId);
            dataAchievementSet.saveToConfig(dataAchievementSetConfig);
        }
    }

    public TreeMap<String, DataAchievementSet> getDataAchievementSetMap() {
        return dataAchievementSetMap;
    }

    public void setDataAchievementSetMap(TreeMap<String, DataAchievementSet> dataAchievementSetMap) {
        this.dataAchievementSetMap = dataAchievementSetMap;
    }
}
