package ch.mixin.mixedAchievements.metaData;

public class PlayerAchievementData {
    private boolean achieved = false;
    private int points = 0;

    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
