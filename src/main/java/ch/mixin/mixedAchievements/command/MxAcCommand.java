package ch.mixin.mixedAchievements.command;

import ch.mixin.mixedAchievements.command.mxAcCommand.AchievementsCommand;
import ch.mixin.mixedAchievements.main.MixedAchievementsData;

import java.util.HashMap;

public class MxAcCommand extends RootCommand {
    public MxAcCommand(MixedAchievementsData mixedAchievementsData) {
        super(mixedAchievementsData, "mx-ac", new HashMap<>());
        subCommandMap.put("achievements", new AchievementsCommand(mixedAchievementsData));
        subCommandMap.put("reload", new AchievementsCommand(mixedAchievementsData));
    }
}
