package ch.mixin.mixedAchievements.command;


import ch.mixin.mixedAchievements.main.MixedAchievementsData;
import ch.mixin.mixedAchievements.main.MixedAchievementsPlugin;

public class CommandInitializer {
    public static void setupCommands(MixedAchievementsData mixedAchievementsData) {
        MixedAchievementsPlugin plugin = mixedAchievementsData.getPlugin();
        MxAcCommand mxCommand = new MxAcCommand(mixedAchievementsData);
        plugin.getCommand(mxCommand.getCommandName()).setExecutor(mxCommand);
        plugin.getCommand(mxCommand.getCommandName()).setTabCompleter(new CommandCompleter(mxCommand));
    }
}
