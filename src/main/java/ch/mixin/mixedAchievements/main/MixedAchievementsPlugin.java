package ch.mixin.mixedAchievements.main;

import ch.mixin.mixedAchievements.api.AchievementApi;
import ch.mixin.mixedAchievements.api.AchievementManager;
import ch.mixin.mixedAchievements.blueprint.BlueprintAchievementSet;
import ch.mixin.mixedAchievements.command.CommandInitializer;
import ch.mixin.mixedAchievements.customConfig.CustomConfig;
import ch.mixin.mixedAchievements.event.AchievementEventManager;
import ch.mixin.mixedAchievements.eventListener.EventListenerInitializer;
import ch.mixin.mixedAchievements.inventory.InventoryAchievementManager;
import ch.mixin.mixedAchievements.data.DataAchievementManager;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import javax.naming.ServiceUnavailableException;
import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class MixedAchievementsPlugin extends JavaPlugin {
    private String pluginName;
    private String rootDirectoryPath;
    private CustomConfig achievementsConfig;
    private MixedAchievementsData mixedAchievementsData;

    public boolean PluginFlawless;

    @Override
    public void onEnable() {
        pluginName = getDescription().getName();
        System.out.println(pluginName + " enabling");
        setup();
        load();
        PluginFlawless = true;
        System.out.println(pluginName + " successfully enabled");
    }

    @Override
    public void onDisable() {
        System.out.println(pluginName + " disabling");
        PluginFlawless = false;
        mixedAchievementsData.getDataAchievementManager().saveToConfig();
        System.out.println(pluginName + " successfully disabled");
    }

    private void setup() {
        String urlPath = MixedAchievementsPlugin.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decodedPath = null;

        try {
            decodedPath = URLDecoder.decode(urlPath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        rootDirectoryPath = decodedPath.substring(0, decodedPath.lastIndexOf("/"));
        achievementsConfig = new CustomConfig(this, "achievements");

        mixedAchievementsData = new MixedAchievementsData(this);
        mixedAchievementsData.setAchievementManager(new AchievementManager(mixedAchievementsData));
        mixedAchievementsData.setDataAchievementManager(new DataAchievementManager(mixedAchievementsData, achievementsConfig));
        mixedAchievementsData.setInventoryAchievementManager(new InventoryAchievementManager(mixedAchievementsData));
        mixedAchievementsData.setAchievementEventManager(new AchievementEventManager(mixedAchievementsData));

        EventListenerInitializer.setupEventListener(mixedAchievementsData);
        CommandInitializer.setupCommands(mixedAchievementsData);
    }

    public void reload() {
        load();
    }

    private void load() {
        achievementsConfig.reload();
    }

    public AchievementApi makeAchievementSet(BlueprintAchievementSet blueprintAchievementSet) throws ServiceUnavailableException {
        if (!PluginFlawless)
            throw new ServiceUnavailableException("Plugin is inactive.");

        AchievementManager achievementManager = mixedAchievementsData.getAchievementManager();
        achievementManager.integrateAchievementSet(blueprintAchievementSet);
        return new AchievementApi(blueprintAchievementSet.getSetId(), achievementManager);
    }

    public String getPluginName() {
        return pluginName;
    }

    public String getRootDirectoryPath() {
        return rootDirectoryPath;
    }

    @Deprecated
    public boolean isActive() {
        return PluginFlawless;
    }
}
