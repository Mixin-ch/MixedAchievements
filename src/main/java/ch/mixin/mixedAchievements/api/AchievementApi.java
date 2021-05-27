package ch.mixin.mixedAchievements.api;

import java.util.UUID;

/**
 * Used to communicate with the MixedAchievement Plugin.
 */
public class AchievementApi {
    private final String setName;
    private final AchievementManager achievementManager;

    /**
     * @param setName
     * @param achievementManager
     */
    public AchievementApi(String setName, AchievementManager achievementManager) {
        this.setName = setName;
        this.achievementManager = achievementManager;
    }

    /**
     * Set an achievement to achieved or not.
     *
     * @param achievementId
     * @param playerId
     * @param achieved
     * @throws IllegalArgumentException
     */
    public void setAchieved(String achievementId, UUID playerId, boolean achieved) throws IllegalArgumentException {
        achievementManager.setAchieved(setName, achievementId, playerId, achieved);
    }

    /**
     * Checks if the achievement is achieved.
     *
     * @param achievementId
     * @param playerId
     * @return Is the achievement is achieved?
     * @throws IllegalArgumentException
     */
    public boolean getAchieved(String achievementId, UUID playerId) throws IllegalArgumentException {
        return achievementManager.getAchieved(setName, achievementId, playerId);
    }

    /**
     * Sets the points value to a max of defined maxPoints.
     * Sets achieved to true, if points >= maxPoints and maxPoints > 0.
     *
     * @param achievementId
     * @param playerId
     * @param value
     * @throws IllegalArgumentException
     */
    public void setPoints(String achievementId, UUID playerId, int value) throws IllegalArgumentException {
        achievementManager.setPoints(setName, achievementId, playerId, value);
    }

    /**
     * Adds specified amount to points value to a max of defined maxPoints.
     * Sets achieved to true, if points >= maxPoints and maxPoints > 0.
     *
     * @param achievementId
     * @param playerId
     * @param value
     * @throws IllegalArgumentException
     */
    public void addPoints(String achievementId, UUID playerId, int value) throws IllegalArgumentException {
        achievementManager.addPoints(setName, achievementId, playerId, value);
    }

    /**
     * Sets achieved to true if points >= maxPoints.
     * Otherwise to false.
     * If maxPoints <= 0, nothing changes.
     *
     * @param achievementId
     * @param playerId
     * @throws IllegalArgumentException
     */
    public void revalueAchieved(String achievementId, UUID playerId) throws IllegalArgumentException {
        achievementManager.revalueAchieved(setName, achievementId, playerId);
    }
}
