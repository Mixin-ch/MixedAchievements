package ch.mixin.mixedAchievements.inventory;

import ch.mixin.mixedAchievements.blueprint.AchievementItemSetup;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class AchievementInventoryFolderElement extends AchievementInventoryElement {
    protected HashMap<Integer, AchievementInventoryElement> subAchievementInventoryElementMap;
    protected String inventoryName;
    protected Inventory inventory;

    public AchievementInventoryFolderElement(AchievementItemSetup achievementItemSetup, String inventoryName) {
        super(achievementItemSetup);
        this.inventoryName = inventoryName;
    }

    public void generate() {
        Inventory inventory = Bukkit.createInventory(null, 6 * 9, inventoryName);

        for (int slot : subAchievementInventoryElementMap.keySet()) {
            AchievementInventoryElement aie = subAchievementInventoryElementMap.get(slot);
            AchievementItemSetup ais = aie.getAchievementItemSetup();

            ItemStack item = new ItemStack(ais.getMaterial(), ais.getAmount());
            ItemMeta meta = item.getItemMeta();

            if (meta != null) {
                meta.setDisplayName(ais.getName());
                item.setItemMeta(meta);
            }

            inventory.setItem(slot, item);

            if (aie instanceof AchievementInventoryFolderElement)
                ((AchievementInventoryFolderElement) aie).generate();
        }
    }

    public void close() {
        for (AchievementInventoryElement aie : subAchievementInventoryElementMap.values()) {
            if (aie instanceof AchievementInventoryFolderElement)
                ((AchievementInventoryFolderElement) aie).close();
        }

        if (inventory == null)
            return;

        for (HumanEntity humanEntity : inventory.getViewers()) {
            if (humanEntity.getInventory() == inventory)
                humanEntity.closeInventory();
        }
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    public HashMap<Integer, AchievementInventoryElement> getSubAchievementInventoryElementMap() {
        return subAchievementInventoryElementMap;
    }

    public void setSubAchievementInventoryElementMap(HashMap<Integer, AchievementInventoryElement> subAchievementInventoryElementMap) {
        this.subAchievementInventoryElementMap = subAchievementInventoryElementMap;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
