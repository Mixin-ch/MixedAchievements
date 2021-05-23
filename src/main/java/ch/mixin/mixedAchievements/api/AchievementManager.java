package ch.mixin.mixedAchievements.api;

import ch.mixin.mixedAchievements.blueprint.AchievementLeafElement;
import ch.mixin.mixedAchievements.blueprint.AchievementSetBlueprint;
import ch.mixin.mixedAchievements.blueprint.AchievementElement;
import ch.mixin.mixedAchievements.blueprint.AchievementFolderElement;
import ch.mixin.mixedAchievements.metaData.AchievementData;
import ch.mixin.mixedAchievements.metaData.AchievementMetaData;
import ch.mixin.mixedAchievements.main.MixedAchievements;
import ch.mixin.mixedAchievements.metaData.PlayerAchievementData;
import org.bukkit.entity.Player;

import java.util.TreeMap;
import java.util.UUID;

public class AchievementManager {
    private final MixedAchievements plugin;
    private final AchievementMetaData achievementMetaData;

    private final TreeMap<String, AchievementInfo> achievementInfoMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public AchievementManager(MixedAchievements plugin, AchievementMetaData achievementMetaData) {
        this.plugin = plugin;
        this.achievementMetaData = achievementMetaData;
    }

    public void makeAchievementSet(AchievementSetBlueprint achievementSetBlueprint) {
        makeAchievementSet(achievementSetBlueprint, achievementSetBlueprint.getSetName());
    }

    private void makeAchievementSet(AchievementFolderElement achievementFolderElement, String setName) {
        for (AchievementElement achievementElement : achievementFolderElement.getSubElementList()) {
            if (achievementElement instanceof AchievementFolderElement) {
                makeAchievementSet((AchievementFolderElement) achievementElement, setName);
            } else {
                makeAchievementSet((AchievementLeafElement) achievementElement, setName);
            }
        }
    }

    private void makeAchievementSet(AchievementLeafElement achievementLeafElement, String setName) {
        String achievementId = setName + "." + achievementLeafElement.getAchievementId();

        if (achievementInfoMap.containsKey(achievementId))
            return;

        AchievementData achievementData = achievementMetaData.getAchievementDataMap().get(achievementId);

        if (achievementData == null) {
            achievementData = new AchievementData();
            achievementMetaData.getAchievementDataMap().put(achievementId, achievementData);
        }

        AchievementInfo achievementInfo = new AchievementInfo(achievementData, achievementLeafElement.getMaxPoints());
        achievementInfoMap.put(achievementId, achievementInfo);
    }

    public void setAchieved(String achievement, UUID playerId, boolean achieved) throws IllegalArgumentException {
        PlayerAchievementData playerAchievementData = makePlayerAchievementData(achievement, playerId);

        if (!achieved) {
            playerAchievementData.setAchieved(false);
            return;
        }

        if (playerAchievementData.isAchieved())
            return;

        playerAchievementData.setAchieved(true);
        achievementUnlocked(achievement, playerId);
    }

    public void setPoints(String achievement, UUID playerId, int value) throws IllegalArgumentException {
        PlayerAchievementData playerAchievementData = makePlayerAchievementData(achievement, playerId);
        playerAchievementData.setPoints(value);
        checkPointsAchieved(achievement, playerId);
    }

    public void addPoints(String achievement, UUID playerId, int value) {
        PlayerAchievementData playerAchievementData = makePlayerAchievementData(achievement, playerId);
        playerAchievementData.setPoints(value + playerAchievementData.getPoints());
        checkPointsAchieved(achievement, playerId);
    }

    public void revalueAchieved(String achievement, UUID playerId) {
        PlayerAchievementData playerAchievementData = makePlayerAchievementData(achievement, playerId);
        int max = 0; //TODO: Get real max value.
        setAchieved(achievement, playerId, playerAchievementData.getPoints() < max);
    }

    private void checkPointsAchieved(String achievement, UUID playerId) {
        PlayerAchievementData playerAchievementData = makePlayerAchievementData(achievement, playerId);

        if (playerAchievementData.isAchieved())
            return;

        int max = 0; //TODO: Get real max value.
        setAchieved(achievement, playerId, playerAchievementData.getPoints() < max);
    }

    private PlayerAchievementData makePlayerAchievementData(String achievement, UUID playerId) throws IllegalArgumentException {
        AchievementInfo achievementInfo = achievementInfoMap.get(achievement);

        if (achievementInfo == null)
            throw new IllegalArgumentException("Achievement key not found.");

        AchievementData achievementData = achievementInfo.getAchievementSetData();
        PlayerAchievementData playerAchievementData = achievementData.getPlayerAchievementDataMap().get(playerId.toString());

        if (playerAchievementData == null) {
            playerAchievementData = new PlayerAchievementData();
            achievementData.getPlayerAchievementDataMap().put(playerId.toString(), playerAchievementData);
        }

        return playerAchievementData;
    }

    private void achievementUnlocked(String achievement, UUID playerId) {
        Player player = plugin.getServer().getPlayer(playerId);

        if (player == null)
            return;

        player.sendMessage("Achievement unlocked: " + achievement);
    }
}
