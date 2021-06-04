package ch.mixin.mixedAchievements.api;

/**
 * Used to communicate with the MixedAchievement Plugin.
 */
public class AchievementApi {
    private final String setId;
    private final AchievementManager achievementManager;

    /**
     * @param setId
     * @param achievementManager
     */
    public AchievementApi(String setId, AchievementManager achievementManager) {
        this.setId = setId;
        this.achievementManager = achievementManager;
    }

    /**
     * Complete achievement stage.
     * Does nothing, if all achievement stage is on max value.
     *
     * @param achievementId
     * @param playerId
     * @throws IllegalArgumentException
     */
    public void completeStage(String achievementId, String playerId) throws IllegalArgumentException {
        achievementManager.completeStage(setId, achievementId, playerId);
    }

    /**
     * Complete all achievement stages.
     * Does nothing, if all achievement stage is on max value.
     *
     * @param achievementId
     * @param playerId
     * @throws IllegalArgumentException
     */
    public void completeAbsolut(String achievementId, String playerId) throws IllegalArgumentException {
        achievementManager.completeAbsolut(setId, achievementId, playerId);
    }

    /**
     * Returns true, if all achievement stage is on max value.
     *
     * @param achievementId
     * @param playerId
     * @throws IllegalArgumentException
     */
    public boolean isAbsolutCompleted(String achievementId, String playerId) throws IllegalArgumentException {
        return achievementManager.isAbsolutCompleted(setId, achievementId, playerId);
    }

    /**
     * Sets the stage to specified value, from 0 to the max stage value.
     *
     * @param achievementId
     * @param playerId
     * @param value
     * @throws IllegalArgumentException
     */
    public void setStage(String achievementId, String playerId, int value) throws IllegalArgumentException {
        achievementManager.setStage(setId, achievementId, playerId, value);
    }

    /**
     * Adds specified value to the stage, from 0 to the max stage value.
     *
     * @param achievementId
     * @param playerId
     * @param value
     * @throws IllegalArgumentException
     */
    public void addStage(String achievementId, String playerId, int value) throws IllegalArgumentException {
        achievementManager.addStage(setId, achievementId, playerId, value);
    }

    /**
     * Returns the stage value.
     *
     * @param achievementId
     * @param playerId
     * @throws IllegalArgumentException
     */
    public int getStage(String achievementId, String playerId) throws IllegalArgumentException {
        return achievementManager.getStage(setId, achievementId, playerId);
    }

    /**
     * Sets points to specified value.
     * Evaluates if new stage is completed.
     *
     * @param achievementId
     * @param playerId
     * @param value
     * @throws IllegalArgumentException
     */
    public void setPoints(String achievementId, String playerId, int value) throws IllegalArgumentException {
        achievementManager.setPoints(setId, achievementId, playerId, value);
    }

    /**
     * Adds specified value to points.
     * Checks if new stage is completed.
     *
     * @param achievementId
     * @param playerId
     * @param value
     * @throws IllegalArgumentException
     */
    public void addPoints(String achievementId, String playerId, int value) throws IllegalArgumentException {
        achievementManager.addPoints(setId, achievementId, playerId, value);
    }

    /**
     * Returns the point value.
     *
     * @param achievementId
     * @param playerId
     * @throws IllegalArgumentException
     */
    public int getPoints(String achievementId, String playerId) throws IllegalArgumentException {
        return achievementManager.getPoints(setId, achievementId, playerId);
    }

    /**
     * Reevaluates the points, and sets the stage value appropriately.
     *
     * @param achievementId
     * @param playerId
     * @throws IllegalArgumentException
     */
    public void revaluePointCompletion(String achievementId, String playerId) throws IllegalArgumentException {
        achievementManager.revaluePointCompletion(setId, achievementId, playerId);
    }
}
