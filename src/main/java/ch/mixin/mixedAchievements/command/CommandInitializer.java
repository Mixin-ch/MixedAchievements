package ch.mixin.mixedAchievements.command;


import ch.mixin.mixedAchievements.inventory.AchievementInventoryManager;
import ch.mixin.mixedAchievements.main.MixedAchievementsPlugin;

public class CommandInitializer {
    public static void setupCommands(MixedAchievementsPlugin plugin, AchievementInventoryManager achievementInventoryManager) {
        ch.mixin.mixedAchievements.command.MxAcCommand mxCommand = new ch.mixin.mixedAchievements.command.MxAcCommand(plugin, achievementInventoryManager);
        plugin.getCommand(mxCommand.getCommandName()).setExecutor(mxCommand);
        plugin.getCommand(mxCommand.getCommandName()).setTabCompleter(new CommandCompleter(mxCommand));
    }
}
