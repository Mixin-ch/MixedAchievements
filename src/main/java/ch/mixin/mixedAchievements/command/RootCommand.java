package ch.mixin.mixedAchievements.command;

import ch.mixin.mixedAchievements.main.MixedAchievementsData;
import ch.mixin.mixedAchievements.main.MixedAchievementsPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public abstract class RootCommand extends SubCommand implements CommandExecutor {
    protected final String commandName;
    protected final HashMap<String, SubCommand> subCommandMap;

    public RootCommand(MixedAchievementsData mixedAchievementsData, String commandName, HashMap<String, SubCommand> subCommandMap) {
        super(mixedAchievementsData);
        this.commandName = commandName;
        this.subCommandMap = subCommandMap;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        MixedAchievementsPlugin plugin = mixedAchievementsData.getPlugin();

        if (!plugin.PluginFlawless) {
            sender.sendMessage(ChatColor.RED + plugin.getPluginName() + " Plugin has Problems.");
            return true;
        }

        if (!command.getName().equalsIgnoreCase(commandName))
            return true;

        List<String> arguments = new ArrayList<>(Arrays.asList(args));
        fetchCommand(arguments).execute(sender, arguments);
        return true;
    }

    @Override
    public SubCommand fetchCommand(List<String> arguments) {
        if (arguments.size() == 0)
            return this;

        SubCommand subCommand = subCommandMap.get(arguments.get(0));

        if (subCommand == null)
            return this;

        arguments.remove(0);
        return subCommand.fetchCommand(arguments);
    }

    @Override
    public void execute(CommandSender sender, List<String> arguments) {
        sender.sendMessage(ChatColor.RED + mixedAchievementsData.getPlugin().getPluginName() + " Command not found.");
    }

    @Override
    public List<String> getOptions(List<String> arguments) {
        return new ArrayList<>(subCommandMap.keySet());
    }

    public String getCommandName() {
        return commandName;
    }
}
