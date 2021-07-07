package ch.mixin.mixedAchievements.command.mxAcCommand;

import ch.mixin.mixedAchievements.command.SubCommand;
import ch.mixin.mixedAchievements.main.MixedAchievementsData;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class ReloadCommand extends SubCommand {
    public ReloadCommand(MixedAchievementsData mixedAchievementsData) {
        super(mixedAchievementsData);
    }

    @Override
    public SubCommand fetchCommand(List<String> arguments) {
        return this;
    }

    @Override
    public void execute(CommandSender sender, List<String> arguments) {
        if (!sender.hasPermission("mixedAchievements.reload")) {
            sender.sendMessage(net.md_5.bungee.api.ChatColor.RED + "You lack Permission.");
            return;
        }

        if (arguments.size() != 0) {
            sender.sendMessage(net.md_5.bungee.api.ChatColor.RED + "Invalid Argument Number.");
            return;
        }

        mixedAchievementsData.getPlugin().reload();
        sender.sendMessage(ChatColor.GREEN + "Successfully reloaded MixedAchievements.");
    }

    @Override
    public List<String> getOptions(List<String> arguments) {
        return new ArrayList<>();
    }
}
