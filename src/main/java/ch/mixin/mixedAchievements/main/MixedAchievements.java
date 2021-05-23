package ch.mixin.mixedAchievements.main;

import ch.mixin.mixedAchievements.api.AchievementApi;
import ch.mixin.mixedAchievements.api.AchievementManager;
import ch.mixin.mixedAchievements.blueprint.AchievementSetBlueprint;
import ch.mixin.mixedAchievements.metaData.AchievementMetaData;
import com.google.gson.Gson;
import org.bukkit.plugin.java.JavaPlugin;

import javax.naming.ServiceUnavailableException;
import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Scanner;

public final class MixedAchievements extends JavaPlugin {
    private boolean active = false;
    private String pluginName;
    private String rootDirectoryPath;
    private File metaDataFile;
    private AchievementMetaData achievementMetaData;
    private AchievementManager achievementManager;

    @Override
    public void onEnable() {
        initialize();
        active = true;
        System.out.println(pluginName + " enabled");
//        String msg = ChatColor.of("#FFFF") + "hello";
    }

    private void initialize() {
        String urlPath = MixedAchievements.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decodedPath = null;

        try {
            decodedPath = URLDecoder.decode(urlPath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        rootDirectoryPath = decodedPath.substring(0, decodedPath.lastIndexOf("/"));
        pluginName = getDescription().getName();
        initializeMetaData();
        achievementManager = new AchievementManager(this, achievementMetaData);
    }

    private void initializeMetaData() {
        String metaDataDirectoryPath = rootDirectoryPath + "/" + pluginName;
        File folder = new File(metaDataDirectoryPath);

        if (!folder.exists() && !folder.mkdirs())
            throw new RuntimeException("Failed to create Metadata Directory.");

        String metaDataFilePath = metaDataDirectoryPath + "/Metadata.txt";
        metaDataFile = new File(metaDataFilePath);

        if (!metaDataFile.exists()) {
            try {
                if (!metaDataFile.createNewFile())
                    throw new RuntimeException("Failed to create Metadata File.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String jsonString = String.join("\n", readFile(metaDataFile));

        if (jsonString.equals("")) {
            achievementMetaData = new AchievementMetaData();
        } else {
            achievementMetaData = new Gson().fromJson(jsonString, AchievementMetaData.class);
        }
    }

    @Override
    public void onDisable() {
        System.out.println(pluginName + " disabled");
        active = false;
        writeFile(metaDataFile, new Gson().toJson(achievementMetaData));
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
}
