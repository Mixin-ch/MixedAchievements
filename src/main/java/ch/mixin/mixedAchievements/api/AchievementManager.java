package ch.mixin.mixedAchievements.api;

import ch.mixin.mixedAchievements.blueprint.AchievementBlueprintElement;
import ch.mixin.mixedAchievements.blueprint.AchievementBlueprintFolderElement;
import ch.mixin.mixedAchievements.blueprint.AchievementBlueprintLeafElement;
import ch.mixin.mixedAchievements.blueprint.AchievementSetBlueprint;
import ch.mixin.mixedAchievements.inventory.AchievementInventoryFolderElement;
import ch.mixin.mixedAchievements.inventory.AchievementInventoryLeafElement;
import ch.mixin.mixedAchievements.inventory.AchievementInventoryManager;
import ch.mixin.mixedAchievements.inventory.AchievementRootInventory;
import ch.mixin.mixedAchievements.main.MixedAchievementsManagerAccessor;
import ch.mixin.mixedAchievements.metaData.AchievementData;
import ch.mixin.mixedAchievements.metaData.AchievementDataRoot;
import ch.mixin.mixedAchievements.metaData.AchievementSetData;
import ch.mixin.mixedAchievements.metaData.PlayerAchievementData;
import org.bukkit.entity.Player;

import java.util.TreeMap;
import java.util.UUID;

public class AchievementManager {
    private final MixedAchievementsManagerAccessor mixedAchievementsManagerAccessor;

    private final TreeMap<String, AchievementSetInfo> achievementSetInfoMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public AchievementManager(MixedAchievementsManagerAccessor mixedAchievementsManagerAccessor) {
        this.mixedAchievementsManagerAccessor = mixedAchievementsManagerAccessor;
    }


    public void makeAchievementSet(AchievementSetBlueprint achievementSetBlueprint) {
        AchievementInventoryManager achievementInventoryManager = mixedAchievementsManagerAccessor.getAchievementInventoryManager();
        String setName = achievementSetBlueprint.getSetName();

        if (achievementSetInfoMap.containsKey(setName))
            return;

        AchievementSetInfo achievementSetInfo = new AchievementSetInfo();
        achievementSetInfoMap.put(setName, achievementSetInfo);

        AchievementRootInventory achievementRootInventory = achievementInventoryManager.getAchievementRootInventory();
        AchievementInventoryFolderElement achievementInventoryFolderElement = new AchievementInventoryFolderElement(mixedAchievementsManagerAccessor, achievementRootInventory, achievementSetBlueprint.getAchievementItemSetup(), achievementSetBlueprint.getSetName());
        achievementRootInventory.getAchievementSetInventoryMap().put(setName, achievementInventoryFolderElement);

        makeAchievementSetFromFolder(achievementSetBlueprint, achievementSetInfo, achievementInventoryFolderElement, setName);
        achievementInventoryManager.reload();
    }

    private void makeAchievementSetFromFolder(AchievementBlueprintFolderElement achievementBlueprintFolderElement, AchievementSetInfo achievementSetInfo, AchievementInventoryFolderElement achievementInventoryFolderElement, String setName) {
        for (int slot : achievementBlueprintFolderElement.getSubAchievementBlueprintElementMap().keySet()) {
            AchievementBlueprintElement blueprintElement = achievementBlueprintFolderElement.getSubAchievementBlueprintElementMap().get(slot);

            if (blueprintElement instanceof AchievementBlueprintFolderElement) {
                AchievementBlueprintFolderElement subBlueprintFolder = (AchievementBlueprintFolderElement) blueprintElement;
                AchievementInventoryFolderElement subInventoryFolder = new AchievementInventoryFolderElement(mixedAchievementsManagerAccessor, achievementInventoryFolderElement, blueprintElement.getAchievementItemSetup(), subBlueprintFolder.getInventoryName());
                achievementInventoryFolderElement.getSubAchievementInventoryElementMap().put(slot, subInventoryFolder);
                makeAchievementSetFromFolder(subBlueprintFolder, achievementSetInfo, subInventoryFolder, setName);
            } else {
                AchievementBlueprintLeafElement blueprintLeaf = (AchievementBlueprintLeafElement) blueprintElement;
                AchievementInventoryLeafElement inventoryLeaf = new AchievementInventoryLeafElement(mixedAchievementsManagerAccessor, achievementInventoryFolderElement, blueprintElement.getAchievementItemSetup());
                achievementInventoryFolderElement.getSubAchievementInventoryElementMap().put(slot, inventoryLeaf);
                makeAchievementSetFromLeaf(blueprintLeaf, achievementSetInfo, inventoryLeaf, setName);
            }
        }
    }

    private void makeAchievementSetFromLeaf(AchievementBlueprintLeafElement achievementBlueprintLeafElement, AchievementSetInfo achievementSetInfo, AchievementInventoryLeafElement achievementInventoryLeafElement, String setName) {
        String achievementId = achievementBlueprintLeafElement.getAchievementId();
        AchievementDataRoot achievementDataRoot = mixedAchievementsManagerAccessor.getAchievementDataManager().getAchievementDataRoot();
        AchievementSetData achievementSetData = achievementDataRoot.getAchievementSetDataMap().get(setName);

        if (achievementSetData == null) {
            achievementSetData = new AchievementSetData();
            achievementDataRoot.getAchievementSetDataMap().put(setName, achievementSetData);
        }

        AchievementData achievementData = achievementSetData.getAchievementDataMap().get(achievementId);

        if (achievementData == null) {
            achievementData = new AchievementData(achievementSetData);
            achievementSetData.getAchievementDataMap().put(achievementId, achievementData);
        }

        AchievementInfo achievementInfo = achievementSetInfo.getAchievementInfoMap().get(achievementId);

        if (achievementInfo == null) {
            achievementInfo = new AchievementInfo(achievementData, achievementBlueprintLeafElement.getAchievementName(), Math.max(0, achievementBlueprintLeafElement.getMaxPoints()));
            achievementSetInfo.getAchievementInfoMap().put(achievementId, achievementInfo);
        }

        achievementInventoryLeafElement.setAchievementInfo(achievementInfo);
    }

    public void setAchieved(String setName, String achievementId, UUID playerId, boolean achieved) {
        PlayerAchievementData playerAchievementData = fetchPlayerAchievementData(setName, achievementId, playerId);

        if (!achieved) {
            playerAchievementData.setAchieved(false);
            return;
        }

        if (playerAchievementData.isAchieved())
            return;

        playerAchievementData.setAchieved(true);
        achievementUnlocked(fetchAchievementInfo(setName, achievementId), playerId);
    }

    public boolean getAchieved(String setName, String achievementId, UUID playerId) {
        PlayerAchievementData playerAchievementData = fetchPlayerAchievementData(setName, achievementId, playerId);
        return playerAchievementData.isAchieved();
    }


    public void setPoints(String setName, String achievementId, UUID playerId, int value) {
        PlayerAchievementData playerAchievementData = fetchPlayerAchievementData(setName, achievementId, playerId);
        AchievementInfo achievementInfo = fetchAchievementInfo(setName, achievementId);
        int maxPoints = achievementInfo.getMaxPoints();
        int points = Math.min(maxPoints, value);
        playerAchievementData.setPoints(points);
        checkPointsAchieved(setName, achievementId, playerId);
    }

    public void addPoints(String setName, String achievementId, UUID playerId, int value) {
        PlayerAchievementData playerAchievementData = fetchPlayerAchievementData(setName, achievementId, playerId);
        AchievementInfo achievementInfo = fetchAchievementInfo(setName, achievementId);
        int maxPoints = achievementInfo.getMaxPoints();
        int points = Math.min(maxPoints, playerAchievementData.getPoints() + value);
        playerAchievementData.setPoints(points);
        checkPointsAchieved(setName, achievementId, playerId);
    }

    public void revalueAchieved(String setName, String achievementId, UUID playerId) {
        PlayerAchievementData playerAchievementData = fetchPlayerAchievementData(setName, achievementId, playerId);
        AchievementInfo achievementInfo = fetchAchievementInfo(setName, achievementId);
        int maxPoints = achievementInfo.getMaxPoints();

        if (maxPoints <= 0)
            return;

        setAchieved(setName, achievementId, playerId, playerAchievementData.getPoints() < maxPoints);
    }

    private void checkPointsAchieved(String setName, String achievementId, UUID playerId) {
        PlayerAchievementData playerAchievementData = fetchPlayerAchievementData(setName, achievementId, playerId);

        if (playerAchievementData.isAchieved())
            return;

        revalueAchieved(setName, achievementId, playerId);
    }

    private AchievementInfo fetchAchievementInfo(String setName, String achievementId) {
        AchievementSetInfo achievementSetInfo = achievementSetInfoMap.get(setName);

        if (achievementSetInfo == null)
            throw new IllegalArgumentException("Set key not found.");

        AchievementInfo achievementInfo = achievementSetInfo.getAchievementInfoMap().get(achievementId);

        if (achievementInfo == null)
            throw new IllegalArgumentException("Achievement key not found.");

        return achievementInfo;
    }

    public PlayerAchievementData fetchPlayerAchievementData(String setName, String achievementId, UUID playerId) throws IllegalArgumentException {
        AchievementInfo achievementInfo = fetchAchievementInfo(setName, achievementId);
        AchievementData achievementData = achievementInfo.getAchievementData();
        PlayerAchievementData playerAchievementData = achievementData.getPlayerAchievementDataMap().get(playerId.toString());

        if (playerAchievementData == null) {
            playerAchievementData = new PlayerAchievementData();
            achievementData.getPlayerAchievementDataMap().put(playerId.toString(), playerAchievementData);
        }

        return playerAchievementData;
    }

    private void achievementUnlocked(AchievementInfo achievementInfo, UUID playerId) {
        Player player = mixedAchievementsManagerAccessor.getPlugin().getServer().getPlayer(playerId);

        if (player == null)
            return;

        player.sendMessage("Achievement unlocked: " + achievementInfo.getAchievementName());
    }
}
