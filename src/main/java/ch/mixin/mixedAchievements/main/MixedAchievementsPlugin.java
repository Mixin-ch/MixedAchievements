package ch.mixin.mixedAchievements.main;

import ch.mixin.mixedAchievements.api.AchievementApi;
import ch.mixin.mixedAchievements.api.AchievementManager;
import ch.mixin.mixedAchievements.blueprint.AchievementSetBlueprint;
import ch.mixin.mixedAchievements.command.CommandInitializer;
import ch.mixin.mixedAchievements.customConfig.CustomConfig;
import ch.mixin.mixedAchievements.eventListener.EventListenerInitializer;
import ch.mixin.mixedAchievements.inventory.AchievementInventoryManager;
import ch.mixin.mixedAchievements.metaData.AchievementDataManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.naming.ServiceUnavailableException;
import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Scanner;

public final class MixedAchievementsPlugin extends JavaPlugin {
    private boolean active = false;
    private String pluginName;
    private String rootDirectoryPath;
    private CustomConfig achievementsConfig;
    private AchievementDataManager achievementDataManager;
    private AchievementManager achievementManager;
    private AchievementInventoryManager achievementInventoryManager;

    @Override
    public void onEnable() {
        System.out.println(pluginName + " enabling");
        initialize();
        active = true;
        System.out.println(pluginName + " successfully enabled");
    }

    private void initialize() {
        String urlPath = MixedAchievementsPlugin.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decodedPath = null;

        try {
            decodedPath = URLDecoder.decode(urlPath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        rootDirectoryPath = decodedPath.substring(0, decodedPath.lastIndexOf("/"));
        pluginName = getDescription().getName();
        achievementsConfig = new CustomConfig(this, "achievements");
        achievementDataManager = new AchievementDataManager(achievementsConfig);
        achievementInventoryManager = new AchievementInventoryManager();
        achievementManager = new AchievementManager(this, achievementDataManager, achievementInventoryManager);
        achievementInventoryManager.initialize(achievementManager);
        EventListenerInitializer.setupEventListener(this, achievementInventoryManager);
        CommandInitializer.setupCommands(this, achievementInventoryManager);
    }

    @Override
    public void onDisable() {
        System.out.println(pluginName + " disabling");
        active = false;
        achievementDataManager.saveToConfig();
        System.out.println(pluginName + " successfully disabled");
    }

    public AchievementApi makeAchievementSet(AchievementSetBlueprint achievementSetBlueprint) throws ServiceUnavailableException {
        if (!active)
            throw new ServiceUnavailableException("Plugin is inactive.");

        achievementManager.makeAchievementSet(achievementSetBlueprint);
        return new AchievementApi(achievementSetBlueprint.getSetName(), achievementManager);
    }

    private ArrayList<String> readFile(File file) {
        ArrayList<String> text = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                text.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return text;
    }

    private void writeFile(File file, String text) {
        try {
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(text);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isActive() {
        return active;
    }

    public String getPluginName() {
        return pluginName;
    }
}
