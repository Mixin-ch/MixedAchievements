package ch.mixin.mixedAchievements.command.mxAcCommand;

import ch.mixin.mixedAchievements.command.SubCommand;
import ch.mixin.mixedAchievements.main.MixedAchievementsData;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AchievementsCommand extends SubCommand {
    public AchievementsCommand(MixedAchievementsData mixedAchievementsData) {
        super(mixedAchievementsData);
    }

    @Override
    public SubCommand fetchCommand(List<String> arguments) {
        return this;
    }

    @Override
    public void execute(CommandSender sender, List<String> arguments) {
        if (arguments.size() != 0) {
            sender.sendMessage(ChatColor.RED + "Invalid Argument Number.");
            return;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You are not a Player.");
            return;
        }

        Player player = (Player) sender;
        mixedAchievementsData.getInventoryAchievementManager().open(player);
    }

    @Override
    public List<String> getOptions(List<String> arguments) {
        return new ArrayList<>();
    }
}
