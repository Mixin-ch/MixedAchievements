package ch.mixin.mixedAchievements.data;

import ch.mixin.mixedAchievements.customConfig.CustomConfig;
import ch.mixin.mixedAchievements.main.MixedAchievementsManagerAccessor;
import org.bukkit.configuration.ConfigurationSection;

public class DataAchievementManager {
    private final MixedAchievementsManagerAccessor mixedAchievementsManagerAccessor;
    private final CustomConfig achievementsConfig;
    private DataAchievementRoot dataAchievementRoot = new DataAchievementRoot();

    public DataAchievementManager(MixedAchievementsManagerAccessor mixedAchievementsManagerAccessor, CustomConfig achievementsConfig) {
        this.mixedAchievementsManagerAccessor = mixedAchievementsManagerAccessor;
        this.achievementsConfig = achievementsConfig;
        loadFromConfig();
        saveToConfig();
    }

    public void loadFromConfig() {
        achievementsConfig.reload();
        ConfigurationSection dataAchievementRootConfig = achievementsConfig.getConfiguration().getConfigurationSection("achievementData");

        if (dataAchievementRootConfig == null)
            return;

        dataAchievementRoot = new DataAchievementRoot(dataAchievementRootConfig);
    }

    public void saveToConfig() {
        ConfigurationSection dataAchievementRootConfig = achievementsConfig.getConfiguration().createSection("achievementData");
        dataAchievementRoot.saveToConfig(dataAchievementRootConfig);
        achievementsConfig.save();
    }

    public CustomConfig getAchievementsConfig() {
        return achievementsConfig;
    }

    public DataAchievementRoot getAchievementDataRoot() {
        return dataAchievementRoot;
    }

    public void setAchievementDataRoot(DataAchievementRoot dataAchievementRoot) {
        this.dataAchievementRoot = dataAchievementRoot;
    }
}
