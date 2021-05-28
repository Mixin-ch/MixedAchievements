package ch.mixin.mixedAchievements.command;

import ch.mixin.mixedAchievements.command.mxAcCommand.AchievementsCommand;
import ch.mixin.mixedAchievements.main.MixedAchievementsManagerAccessor;
import ch.mixin.mixedAchievements.main.MixedAchievementsPlugin;

import java.util.HashMap;

public class MxAcCommand extends RootCommand {
    public MxAcCommand(MixedAchievementsManagerAccessor mixedAchievementsManagerAccessor) {
        super(mixedAchievementsManagerAccessor, "mx-ac", new HashMap<>());
        subCommandMap.put("achievements", new AchievementsCommand(mixedAchievementsManagerAccessor));
    }
}
