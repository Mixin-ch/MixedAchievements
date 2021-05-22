package ch.mixin.mixedAchievements.main;

import ch.mixin.mixedAchievements.achievement.MetaData;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public final class MixedAchievements extends JavaPlugin {
    public static MixedAchievements Plugin;
    public static boolean Active = false;
    public static String PluginName;
    public static String RootDirectoryPath;
    public static MetaData MetaData;

    static {
        String urlPath = MixedAchievements.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decodedPath = null;

        try {
            decodedPath = URLDecoder.decode(urlPath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        RootDirectoryPath = decodedPath.substring(0, decodedPath.lastIndexOf("/"));
    }

    @Override
    public void onEnable() {
        Plugin = this;
        PluginName = getDescription().getName();
        initialize();
        Active = true;
        System.out.println(PluginName + " enabled");
        //String msg = ChatColor.of("#FFFF") + "hello";
    }

    private void initialize() {
        MetaData = new MetaData();
    }

    @Override
    public void onDisable() {
        Active = false;
        System.out.println(PluginName + " disabled");
    }
}
