package ch.mixin.mixedAchievements.command;

import ch.mixin.mixedAchievements.command.mxCommand.AchievementsCommand;
import ch.mixin.mixedAchievements.inventory.AchievementInventoryManager;
import ch.mixin.mixedAchievements.main.MixedAchievementsPlugin;

import java.util.HashMap;

public class MxCommand extends RootCommand {
    public MxCommand(MixedAchievementsPlugin plugin, AchievementInventoryManager achievementInventoryManager) {
        super(plugin, achievementInventoryManager, "mx", new HashMap<>());
        subCommandMap.put("achievements", new AchievementsCommand(plugin, achievementInventoryManager));
    }
}
