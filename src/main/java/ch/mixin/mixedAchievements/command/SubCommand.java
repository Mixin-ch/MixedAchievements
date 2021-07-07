package ch.mixin.mixedAchievements.command;

import ch.mixin.mixedAchievements.main.MixedAchievementsData;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class SubCommand {
    protected final MixedAchievementsData mixedAchievementsData;

    public SubCommand(MixedAchievementsData mixedAchievementsData) {
        this.mixedAchievementsData = mixedAchievementsData;
    }

    public abstract SubCommand fetchCommand(List<String> arguments);

    public abstract void execute(CommandSender sender, List<String> arguments);

    public abstract List<String> getOptions(List<String> arguments);
}
