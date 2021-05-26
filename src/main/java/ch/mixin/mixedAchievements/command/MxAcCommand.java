package ch.mixin.mixedAchievements.command;

import ch.mixin.mixedAchievements.command.mxAcCommand.AchievementsCommand;
import ch.mixin.mixedAchievements.inventory.AchievementInventoryManager;
import ch.mixin.mixedAchievements.main.MixedAchievementsPlugin;

import java.util.HashMap;

public class MxAcCommand extends RootCommand {
    public MxAcCommand(MixedAchievementsPlugin plugin, AchievementInventoryManager achievementInventoryManager) {
        super(plugin, achievementInventoryManager, "mx-ac", new HashMap<>());
        subCommandMap.put("achievements", new AchievementsCommand(plugin, achievementInventoryManager));
    }
}
