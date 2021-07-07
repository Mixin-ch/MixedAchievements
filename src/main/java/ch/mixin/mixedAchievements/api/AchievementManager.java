package ch.mixin.mixedAchievements.api;

import ch.mixin.mixedAchievements.blueprint.*;
import ch.mixin.mixedAchievements.data.DataAchievement;
import ch.mixin.mixedAchievements.data.DataAchievementRoot;
import ch.mixin.mixedAchievements.data.DataAchievementSet;
import ch.mixin.mixedAchievements.data.DataPlayerAchievement;
import ch.mixin.mixedAchievements.event.AchievementCompletedEvent;
import ch.mixin.mixedAchievements.inventory.*;
import ch.mixin.mixedAchievements.main.MixedAchievementsData;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

public class AchievementManager {
    private final MixedAchievementsData mixedAchievementsData;

    private final TreeMap<String, InfoAchievementSet> infoAchievementSetMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public AchievementManager(MixedAchievementsData mixedAchievementsData) {
        this.mixedAchievementsData = mixedAchievementsData;
    }

    public void integrateAchievementSet(BlueprintAchievementSet blueprintAchievementSet) {
        InventoryAchievementManager inventoryAchievementManager = mixedAchievementsData.getInventoryAchievementManager();
        String setId = blueprintAchievementSet.getSetId();

        if (infoAchievementSetMap.containsKey(setId))
            return;

        InfoAchievementSet infoAchievementSet = new InfoAchievementSet();
        infoAchievementSetMap.put(setId, infoAchievementSet);

        InventoryAchievementRoot inventoryAchievementRoot = inventoryAchievementManager.getAchievementRootInventory();
        InventoryAchievementSet inventoryAchievementSet = new InventoryAchievementSet(mixedAchievementsData, inventoryAchievementRoot, setId, blueprintAchievementSet.getInventoryName(), blueprintAchievementSet.getAchievementItemSetup());
        inventoryAchievementRoot.getInventoryAchievementSetMap().put(setId, inventoryAchievementSet);

        integrateAchievementCategory(blueprintAchievementSet, infoAchievementSet, inventoryAchievementSet, setId);
        inventoryAchievementManager.reload();
    }

    private void integrateAchievementCategory(BlueprintAchievementCategory blueprintAchievementCategory, InfoAchievementSet infoAchievementSet, InventoryAchievementCategory inventoryAchievementCategory, String setId) {
        for (int slot : blueprintAchievementCategory.getBlueprintAchievementElementMap().keySet()) {
            BlueprintAchievementElement blueprintElement = blueprintAchievementCategory.getBlueprintAchievementElementMap().get(slot);

            if (blueprintElement instanceof BlueprintAchievementCategory) {
                BlueprintAchievementCategory subBlueprintFolder = (BlueprintAchievementCategory) blueprintElement;
                InventoryAchievementCategory subInventoryFolder = new InventoryAchievementCategory(mixedAchievementsData, inventoryAchievementCategory, subBlueprintFolder.getInventoryName(), subBlueprintFolder.getAchievementItemSetup());
                inventoryAchievementCategory.getInventoryAchievementElementMap().put(slot, subInventoryFolder);
                integrateAchievementCategory(subBlueprintFolder, infoAchievementSet, subInventoryFolder, setId);
            } else {
                integrateAchievementLeaf(inventoryAchievementCategory, (BlueprintAchievementLeaf) blueprintElement, infoAchievementSet, setId, slot);
            }
        }
    }

    private void integrateAchievementLeaf(InventoryAchievementCategory inventoryAchievementCategory, BlueprintAchievementLeaf blueprintAchievementLeaf, InfoAchievementSet infoAchievementSet, String setId, int slot) {
        String achievementId = blueprintAchievementLeaf.getAchievementId();
        DataAchievementRoot dataAchievementRoot = mixedAchievementsData.getDataAchievementManager().getAchievementDataRoot();
        DataAchievementSet dataAchievementSet = dataAchievementRoot.getDataAchievementSetMap().get(setId);

        if (dataAchievementSet == null) {
            dataAchievementSet = new DataAchievementSet(dataAchievementRoot, setId);
            dataAchievementRoot.getDataAchievementSetMap().put(setId, dataAchievementSet);
        }

        DataAchievement dataAchievement = dataAchievementSet.getDataAchievementMap().get(achievementId);

        if (dataAchievement == null) {
            dataAchievement = new DataAchievement(dataAchievementSet, achievementId);
            dataAchievementSet.getDataAchievementMap().put(achievementId, dataAchievement);
        }

        InfoAchievement infoAchievement = infoAchievementSet.getInfoAchievementMap().get(achievementId);

        if (infoAchievement == null) {
            infoAchievement = new InfoAchievement(dataAchievement, setId, blueprintAchievementLeaf.getAchievementId());
            List<InfoAchievementStage> infoAchievementStageList = new ArrayList<>();
            int maxPoints = 0;

            for (int i = 0; i < blueprintAchievementLeaf.getBlueprintAchievementStageList().size(); i++) {
                BlueprintAchievementStage blueprintAchievementStage = blueprintAchievementLeaf.getBlueprintAchievementStageList().get(i);
                maxPoints = Math.max(maxPoints, blueprintAchievementStage.getMaxPoints());
                infoAchievementStageList.add(new InfoAchievementStage(infoAchievement, i, maxPoints));
            }

            infoAchievement.setInfoAchievementStageList(infoAchievementStageList);
            infoAchievement.setUsesPoints(maxPoints > 0);
            infoAchievementSet.getInfoAchievementMap().put(achievementId, infoAchievement);

            List<AchievementItemSetup> achievementItemSetupList = new ArrayList<>();

            for (BlueprintAchievementStage blueprintAchievementStage : blueprintAchievementLeaf.getBlueprintAchievementStageList()) {
                achievementItemSetupList.add(blueprintAchievementStage.getAchievementItemSetup());
            }

            InventoryAchievementLeaf inventoryAchievementLeaf = new InventoryAchievementLeaf(mixedAchievementsData, inventoryAchievementCategory, infoAchievement, achievementItemSetupList);
            infoAchievement.setInventoryAchievementLeaf(inventoryAchievementLeaf);
        }

        inventoryAchievementCategory.getInventoryAchievementElementMap().put(slot, infoAchievement.getInventoryAchievementLeaf());
    }

    public void completeStage(String setId, String achievementId, String playerId) {
        DataPlayerAchievement dataPlayerAchievement = fetchDataPlayerAchievement(setId, achievementId, playerId);
        InfoAchievement infoAchievement = fetchInfoAchievement(setId, achievementId);

        if (dataPlayerAchievement.getStage() >= infoAchievement.getStageNumber())
            return;

        dataPlayerAchievement.setStage(1 + dataPlayerAchievement.getStage());
        achievementCompleted(infoAchievement, playerId);
    }

    public void completeAbsolut(String setId, String achievementId, String playerId) {
        DataPlayerAchievement dataPlayerAchievement = fetchDataPlayerAchievement(setId, achievementId, playerId);
        InfoAchievement infoAchievement = fetchInfoAchievement(setId, achievementId);

        while (dataPlayerAchievement.getStage() < infoAchievement.getStageNumber()) {
            completeStage(setId, achievementId, playerId);
        }
    }

    public boolean isAbsolutCompleted(String setId, String achievementId, String playerId) {
        DataPlayerAchievement dataPlayerAchievement = fetchDataPlayerAchievement(setId, achievementId, playerId);
        InfoAchievement infoAchievement = fetchInfoAchievement(setId, achievementId);
        return dataPlayerAchievement.getStage() >= infoAchievement.getStageNumber();
    }

    public void setStage(String setId, String achievementId, String playerId, int value) {
        DataPlayerAchievement dataPlayerAchievement = fetchDataPlayerAchievement(setId, achievementId, playerId);
        InfoAchievement infoAchievement = fetchInfoAchievement(setId, achievementId);
        int stageNumber = infoAchievement.getStageNumber();
        value = Math.min(stageNumber, value);

        while (dataPlayerAchievement.getStage() < value) {
            completeStage(setId, achievementId, playerId);
        }

        dataPlayerAchievement.setStage(value);
    }

    public void addStage(String setId, String achievementId, String playerId, int value) {
        DataPlayerAchievement dataPlayerAchievement = fetchDataPlayerAchievement(setId, achievementId, playerId);
        InfoAchievement infoAchievement = fetchInfoAchievement(setId, achievementId);
        int stageNumber = infoAchievement.getStageNumber();
        value = Math.min(stageNumber, value + dataPlayerAchievement.getStage());

        while (dataPlayerAchievement.getStage() < value) {
            completeStage(setId, achievementId, playerId);
        }

        dataPlayerAchievement.setStage(value);
    }

    public int getStage(String setId, String achievementId, String playerId) {
        return fetchDataPlayerAchievement(setId, achievementId, playerId).getStage();
    }

    public void setPoints(String setId, String achievementId, String playerId, int value) {
        if (!fetchInfoAchievement(setId, achievementId).usesPoints())
            return;

        DataPlayerAchievement dataPlayerAchievement = fetchDataPlayerAchievement(setId, achievementId, playerId);
        dataPlayerAchievement.setPoints(value);
        checkPointCompletion(setId, achievementId, playerId);
    }

    public void addPoints(String setId, String achievementId, String playerId, int value) {
        if (!fetchInfoAchievement(setId, achievementId).usesPoints())
            return;

        DataPlayerAchievement dataPlayerAchievement = fetchDataPlayerAchievement(setId, achievementId, playerId);
        dataPlayerAchievement.setPoints(value + dataPlayerAchievement.getPoints());
        checkPointCompletion(setId, achievementId, playerId);
    }

    public int getPoints(String setId, String achievementId, String playerId) {
        return fetchDataPlayerAchievement(setId, achievementId, playerId).getPoints();
    }

    public void revaluePointCompletion(String setId, String achievementId, String playerId) {
        InfoAchievement infoAchievement = fetchInfoAchievement(setId, achievementId);

        if (!infoAchievement.usesPoints())
            return;

        checkPointCompletion(setId, achievementId, playerId);
        DataPlayerAchievement dataPlayerAchievement = fetchDataPlayerAchievement(setId, achievementId, playerId);
        InfoAchievementStage infoAchievementStage = infoAchievement.getInfoAchievementStageList().get(dataPlayerAchievement.getStage());

        if (dataPlayerAchievement.getPoints() >= infoAchievementStage.getMaxPoints()) {
            if (dataPlayerAchievement.getStage() >= infoAchievement.getStageNumber())
                return;

            completeStage(setId, achievementId, playerId);
        } else {

            dataPlayerAchievement.setStage(dataPlayerAchievement.getStage() - 1);
        }

        revaluePointCompletion(setId, achievementId, playerId);
    }

    private void checkPointCompletion(String setId, String achievementId, String playerId) {
        InfoAchievement infoAchievement = fetchInfoAchievement(setId, achievementId);

        if (!infoAchievement.usesPoints())
            return;

        DataPlayerAchievement dataPlayerAchievement = fetchDataPlayerAchievement(setId, achievementId, playerId);

        if (dataPlayerAchievement.getStage() >= infoAchievement.getStageNumber())
            return;

        InfoAchievementStage infoAchievementStage = infoAchievement.getInfoAchievementStageList().get(dataPlayerAchievement.getStage());

        if (dataPlayerAchievement.getPoints() < infoAchievementStage.getMaxPoints())
            return;

        completeStage(setId, achievementId, playerId);
        checkPointCompletion(setId, achievementId, playerId);
    }

    private InfoAchievement fetchInfoAchievement(String setId, String achievementId) {
        InfoAchievementSet infoAchievementSet = infoAchievementSetMap.get(setId);

        if (infoAchievementSet == null)
            throw new IllegalArgumentException("Set key not found.");

        InfoAchievement infoAchievement = infoAchievementSet.getInfoAchievementMap().get(achievementId);

        if (infoAchievement == null)
            throw new IllegalArgumentException("Achievement key not found.");

        return infoAchievement;
    }

    public DataPlayerAchievement fetchDataPlayerAchievement(String setId, String achievementId, String playerId) throws IllegalArgumentException {
        InfoAchievement infoAchievement = fetchInfoAchievement(setId, achievementId);
        DataAchievement dataAchievement = infoAchievement.getDataAchievement();
        DataPlayerAchievement dataPlayerAchievement = dataAchievement.getDataPlayerAchievementMap().get(playerId);

        if (dataPlayerAchievement == null) {
            dataPlayerAchievement = new DataPlayerAchievement(dataAchievement, playerId);
            dataAchievement.getDataPlayerAchievementMap().put(playerId, dataPlayerAchievement);
        }

        return dataPlayerAchievement;
    }

    private void achievementCompleted(InfoAchievement infoAchievement, String playerId) {
        Player player = mixedAchievementsData.getPlugin().getServer().getPlayer(UUID.fromString(playerId));

        if (player == null)
            return;

        int stage = -1 + infoAchievement.getDataAchievement().getDataPlayerAchievementMap().get(playerId).getStage();
        String name = infoAchievement.getInventoryAchievementLeaf().getAchievementItemSetupList().get(stage).getName();
        String message = "Achievement completed: " + name;

        AchievementCompletedEvent event = new AchievementCompletedEvent(player, infoAchievement.getSetId(), infoAchievement.getAchievementId(), message);
        mixedAchievementsData.getAchievementEventManager().callAchievementCompletedEvent(event);
    }
}
