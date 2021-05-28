package ch.mixin.mixedAchievements.command;

import ch.mixin.mixedAchievements.main.MixedAchievementsManagerAccessor;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class SubCommand {
    protected final MixedAchievementsManagerAccessor mixedAchievementsManagerAccessor;

    public SubCommand(MixedAchievementsManagerAccessor mixedAchievementsManagerAccessor) {
        this.mixedAchievementsManagerAccessor = mixedAchievementsManagerAccessor;
    }

    public abstract SubCommand fetchCommand(List<String> arguments);

    public abstract void execute(CommandSender sender, List<String> arguments);

    public abstract List<String> getOptions(List<String> arguments);
}
