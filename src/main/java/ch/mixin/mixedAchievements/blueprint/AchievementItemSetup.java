package ch.mixin.mixedAchievements.blueprint;

import org.bukkit.Material;

public class AchievementItemSetup {
    private Material material;
    private String name;
    private int amount;

    public AchievementItemSetup(Material material, String name, int amount) {
        this.material = material;
        this.name = name;
        this.amount = amount;
    }

    public AchievementItemSetup() {
        this.material = Material.AIR;
        this.name = "";
        this.amount = 0;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
