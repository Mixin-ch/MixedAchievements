package ch.mixin.mixedAchievements.command;


import ch.mixin.mixedAchievements.inventory.AchievementInventoryManager;
import ch.mixin.mixedAchievements.main.MixedAchievementsPlugin;

public class CommandInitializer {
    public static void setupCommands(MixedAchievementsPlugin plugin, AchievementInventoryManager achievementInventoryManager) {
        MxCommand mxCommand = new MxCommand(plugin, achievementInventoryManager);
        plugin.getCommand(mxCommand.getCommandName()).setExecutor(mxCommand);
        plugin.getCommand(mxCommand.getCommandName()).setTabCompleter(new CommandCompleter(mxCommand));
    }
}
