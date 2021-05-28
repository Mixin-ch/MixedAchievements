package ch.mixin.mixedAchievements.blueprint;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class AchievementItemSetup {
    private Material material;
    private String name;
    private int amount;
    private List<String> loreList;

    public AchievementItemSetup(Material material, String name, int amount, List<String> loreList) {
        this.material = material;
        this.name = name;
        this.amount = amount;
        this.loreList = loreList;
    }

    public AchievementItemSetup() {
        this.material = Material.AIR;
        this.name = "";
        this.amount = 0;
        loreList = new ArrayList<>();
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

    public List<String> getLoreList() {
        return loreList;
    }

    public void setLoreList(List<String> loreList) {
        this.loreList = loreList;
    }
}
