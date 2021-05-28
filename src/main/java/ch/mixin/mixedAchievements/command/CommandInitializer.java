package ch.mixin.mixedAchievements.command;


import ch.mixin.mixedAchievements.main.MixedAchievementsManagerAccessor;
import ch.mixin.mixedAchievements.main.MixedAchievementsPlugin;
import org.bukkit.plugin.Plugin;

public class CommandInitializer {
    public static void setupCommands(MixedAchievementsManagerAccessor mixedAchievementsManagerAccessor) {
        MixedAchievementsPlugin plugin = mixedAchievementsManagerAccessor.getPlugin();
        MxAcCommand mxCommand = new MxAcCommand(mixedAchievementsManagerAccessor);
        plugin.getCommand(mxCommand.getCommandName()).setExecutor(mxCommand);
        plugin.getCommand(mxCommand.getCommandName()).setTabCompleter(new CommandCompleter(mxCommand));
    }
}
