package ch.mixin.mixedAchievements.command;

import ch.mixin.mixedAchievements.inventory.AchievementInventoryManager;
import ch.mixin.mixedAchievements.main.MixedAchievementsPlugin;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class SubCommand {
    protected final MixedAchievementsPlugin plugin;
    protected final AchievementInventoryManager achievementInventoryManager;

    public SubCommand(MixedAchievementsPlugin plugin, AchievementInventoryManager achievementInventoryManager) {
        this.plugin = plugin;
        this.achievementInventoryManager = achievementInventoryManager;
    }

    public abstract SubCommand fetchCommand(List<String> arguments);

    public abstract void execute(CommandSender sender, List<String> arguments);

    public abstract List<String> getOptions(List<String> arguments);
}
