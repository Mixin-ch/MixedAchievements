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
    private MixedAchievementsManagerAccessor mixedAchievementsManagerAccessor;

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

        mixedAchievementsManagerAccessor = new MixedAchievementsManagerAccessor(this);
        mixedAchievementsManagerAccessor.setAchievementManager(new AchievementManager(mixedAchievementsManagerAccessor));
        mixedAchievementsManagerAccessor.setDataAchievementManager(new DataAchievementManager(mixedAchievementsManagerAccessor, achievementsConfig));
        mixedAchievementsManagerAccessor.setInventoryAchievementManager(new InventoryAchievementManager(mixedAchievementsManagerAccessor));
        mixedAchievementsManagerAccessor.setAchievementEventManager(new AchievementEventManager(mixedAchievementsManagerAccessor));

        EventListenerInitializer.setupEventListener(mixedAchievementsManagerAccessor);
        CommandInitializer.setupCommands(mixedAchievementsManagerAccessor);
    }

    @Override
    public void onDisable() {
        System.out.println(pluginName + " disabling");
        active = false;
        mixedAchievementsManagerAccessor.getDataAchievementManager().saveToConfig();
        System.out.println(pluginName + " successfully disabled");
    }

    public AchievementApi makeAchievementSet(BlueprintAchievementSet blueprintAchievementSet) throws ServiceUnavailableException {
        if (!active)
            throw new ServiceUnavailableException("Plugin is inactive.");

        AchievementManager achievementManager = mixedAchievementsManagerAccessor.getAchievementManager();
        achievementManager.integrateAchievementSet(blueprintAchievementSet);
        return new AchievementApi(blueprintAchievementSet.getSetId(), achievementManager);
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

    public String getRootDirectoryPath() {
        return rootDirectoryPath;
    }
}
