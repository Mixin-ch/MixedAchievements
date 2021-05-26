package ch.mixin.mixedAchievements.customConfig;

import ch.mixin.mixedAchievements.main.MixedAchievementsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomConfig {
    private final File file;
    private FileConfiguration configuration;

    public CustomConfig(MixedAchievementsPlugin mixedAchievementsPlugin, String name) {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin(mixedAchievementsPlugin.getPluginName()).getDataFolder(), name + ".yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        reload();
        configuration.options().copyDefaults(true);
        save();
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void save() {
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        configuration = YamlConfiguration.loadConfiguration(file);
    }
}
