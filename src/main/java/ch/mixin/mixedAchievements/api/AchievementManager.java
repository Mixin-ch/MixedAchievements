package ch.mixin.mixedAchievements.api;

import ch.mixin.mixedAchievements.blueprint.AchievementBlueprintElement;
import ch.mixin.mixedAchievements.blueprint.AchievementBlueprintFolderElement;
import ch.mixin.mixedAchievements.blueprint.AchievementBlueprintLeafElement;
import ch.mixin.mixedAchievements.blueprint.AchievementSetBlueprint;
import ch.mixin.mixedAchievements.inventory.AchievementInventoryFolderElement;
import ch.mixin.mixedAchievements.inventory.AchievementInventoryLeafElement;
import ch.mixin.mixedAchievements.inventory.AchievementInventoryManager;
import ch.mixin.mixedAchievements.inventory.AchievementRootInventory;
import ch.mixin.mixedAchievements.main.MixedAchievementsPlugin;
import ch.mixin.mixedAchievements.metaData.AchievementData;
import ch.mixin.mixedAchievements.metaData.AchievementDataManager;
import ch.mixin.mixedAchievements.metaData.AchievementDataRoot;
import ch.mixin.mixedAchievements.metaData.AchievementSetData;

import java.util.TreeMap;

public class AchievementManager {
    private final MixedAchievementsPlugin plugin;
    private final AchievementDataManager achievementDataManager;
    private final AchievementInventoryManager achievementInventoryManager;

    private final TreeMap<String, AchievementSetInfo> achievementSetInfoMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public AchievementManager(MixedAchievementsPlugin plugin, AchievementDataManager achievementDataManager, AchievementInventoryManager achievementInventoryManager) {
        this.plugin = plugin;
        this.achievementDataManager = achievementDataManager;
        this.achievementInventoryManager = achievementInventoryManager;
    }


    public void makeAchievementSet(AchievementSetBlueprint achievementSetBlueprint) {
        String setName = achievementSetBlueprint.getSetName();

        if (achievementSetInfoMap.containsKey(setName))
            return;

        AchievementSetInfo achievementSetInfo = new AchievementSetInfo();
        achievementSetInfoMap.put(setName, achievementSetInfo);

        AchievementRootInventory achievementRootInventory = achievementInventoryManager.getAchievementRootInventory();
        AchievementInventoryFolderElement achievementInventoryFolderElement = new AchievementInventoryFolderElement(achievementRootInventory, achievementSetBlueprint.getAchievementItemSetup(), achievementSetBlueprint.getSetName());
        achievementRootInventory.getAchievementSetInventoryMap().put(setName, achievementInventoryFolderElement);

        makeAchievementSet(achievementSetBlueprint, achievementSetInfo, achievementInventoryFolderElement, setName);
        achievementInventoryManager.reload();
    }

    private void makeAchievementSet(AchievementBlueprintFolderElement achievementBlueprintFolderElement, AchievementSetInfo achievementSetInfo, AchievementInventoryFolderElement achievementInventoryFolderElement, String setName) {
        for (int slot : achievementBlueprintFolderElement.getSubAchievementBlueprintElementMap().keySet()) {
            AchievementBlueprintElement blueprintElement = achievementBlueprintFolderElement.getSubAchievementBlueprintElementMap().get(slot);

            if (blueprintElement instanceof AchievementBlueprintFolderElement) {
                AchievementBlueprintFolderElement subBlueprintFolder = (AchievementBlueprintFolderElement) blueprintElement;
                AchievementInventoryFolderElement subInventoryFolder = new AchievementInventoryFolderElement(achievementInventoryFolderElement, blueprintElement.getAchievementItemSetup(), subBlueprintFolder.getInventoryName());
                achievementInventoryFolderElement.getSubAchievementInventoryElementMap().put(slot, subInventoryFolder);
                makeAchievementSet(subBlueprintFolder, achievementSetInfo, subInventoryFolder, setName);
            } else {
                AchievementBlueprintLeafElement blueprintLeaf = (AchievementBlueprintLeafElement) blueprintElement;
                AchievementInventoryLeafElement inventoryLeaf = new AchievementInventoryLeafElement(achievementInventoryFolderElement, blueprintElement.getAchievementItemSetup());
                achievementInventoryFolderElement.getSubAchievementInventoryElementMap().put(slot, inventoryLeaf);
                makeAchievementSet(blueprintLeaf, achievementSetInfo, inventoryLeaf, setName);
            }
        }
    }

    private void makeAchievementSet(AchievementBlueprintLeafElement achievementBlueprintLeafElement, AchievementSetInfo achievementSetInfo, AchievementInventoryLeafElement achievementInventoryLeafElement, String setName) {
        String achievementId = achievementBlueprintLeafElement.getAchievementId();
        AchievementDataRoot achievementDataRoot = achievementDataManager.getAchievementDataRoot();
        AchievementSetData achievementSetData = achievementDataRoot.getAchievementSetDataMap().get(setName);

        if (achievementSetData == null) {
            achievementSetData = new AchievementSetData();
            achievementDataRoot.getAchievementSetDataMap().put(setName, achievementSetData);
        }

        AchievementData achievementData = achievementSetData.getAchievementDataMap().get(achievementId);

        if (achievementData == null) {
            achievementData = new AchievementData();
            achievementSetData.getAchievementDataMap().put(achievementId, achievementData);
        }

        AchievementInfo achievementInfo = achievementSetInfo.getAchievementInfoMap().get(setName);

        if (achievementInfo == null) {
            achievementInfo = new AchievementInfo(achievementData, achievementBlueprintLeafElement.getMaxPoints());
            achievementSetInfo.getAchievementInfoMap().put(achievementId, achievementInfo);
        }

        achievementInventoryLeafElement.setAchievementInfo(achievementInfo);
    }

//    public void setAchieved(String achievement, UUID playerId, boolean achieved) throws IllegalArgumentException {
//        PlayerAchievementData playerAchievementData = makePlayerAchievementData(achievement, playerId);
//
//        if (!achieved) {
//            playerAchievementData.setAchieved(false);
//            return;
//        }
//
//        if (playerAchievementData.isAchieved())
//            return;
//
//        playerAchievementData.setAchieved(true);
//        achievementUnlocked(achievement, playerId);
//    }
//
//    public void setPoints(String achievement, UUID playerId, int value) throws IllegalArgumentException {
//        PlayerAchievementData playerAchievementData = makePlayerAchievementData(achievement, playerId);
//        playerAchievementData.setPoints(value);
//        checkPointsAchieved(achievement, playerId);
//    }
//
//    public void addPoints(String achievement, UUID playerId, int value) {
//        PlayerAchievementData playerAchievementData = makePlayerAchievementData(achievement, playerId);
//        playerAchievementData.setPoints(value + playerAchievementData.getPoints());
//        checkPointsAchieved(achievement, playerId);
//    }
//
//    public void revalueAchieved(String achievement, UUID playerId) {
//        PlayerAchievementData playerAchievementData = makePlayerAchievementData(achievement, playerId);
//        int max = 0; //TODO: Get real max value.
//        setAchieved(achievement, playerId, playerAchievementData.getPoints() < max);
//    }
//
//    private void checkPointsAchieved(String achievement, UUID playerId) {
//        PlayerAchievementData playerAchievementData = makePlayerAchievementData(achievement, playerId);
//
//        if (playerAchievementData.isAchieved())
//            return;
//
//        int max = 0; //TODO: Get real max value.
//        setAchieved(achievement, playerId, playerAchievementData.getPoints() < max);
//    }
//
//    private PlayerAchievementData makePlayerAchievementData(String achievement, UUID playerId) throws IllegalArgumentException {
//        AchievementInfo achievementInfo = achievementInfoMap.get(achievement);
//
//        if (achievementInfo == null)
//            throw new IllegalArgumentException("Achievement key not found.");
//
//        AchievementData achievementData = achievementInfo.getAchievementSetData();
//        PlayerAchievementData playerAchievementData = achievementData.getPlayerAchievementDataMap().get(playerId.toString());
//
//        if (playerAchievementData == null) {
//            playerAchievementData = new PlayerAchievementData();
//            achievementData.getPlayerAchievementDataMap().put(playerId.toString(), playerAchievementData);
//        }
//
//        return playerAchievementData;
//    }
//
//    private void achievementUnlocked(String achievement, UUID playerId) {
//        Player player = plugin.getServer().getPlayer(playerId);
//
//        if (player == null)
//            return;
//
//        player.sendMessage("Achievement unlocked: " + achievement);
//    }
}
