package ch.mixin.mixedAchievements.inventory;

import ch.mixin.mixedAchievements.api.AchievementInfo;
import ch.mixin.mixedAchievements.blueprint.AchievementBlueprintFolderElement;
import ch.mixin.mixedAchievements.blueprint.AchievementItemSetup;
import ch.mixin.mixedAchievements.metaData.AchievementData;
import ch.mixin.mixedAchievements.metaData.PlayerAchievementData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;

public class AchievementInventoryFolderElement extends AchievementInventoryElement {
    protected HashMap<Integer, AchievementInventoryElement> subAchievementInventoryElementMap;
    protected String inventoryName;

    public AchievementInventoryFolderElement(AchievementInventoryElement parent, AchievementItemSetup achievementItemSetup, String inventoryName) {
        super(parent, achievementItemSetup);
        this.inventoryName = inventoryName;
        subAchievementInventoryElementMap = new HashMap<>();
    }

    public Inventory generateInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 6 * 9, inventoryName);

        for (int slot : subAchievementInventoryElementMap.keySet()) {
            AchievementInventoryElement aie = subAchievementInventoryElementMap.get(slot);
            inventory.setItem(slot, makeItemSlot(aie, player));
        }

        inventory.setItem(AchievementInventoryManager.CancelSlot, makeItemBasicSlot(AchievementInventoryManager.CancelItem));
        return inventory;
    }

    private ItemStack makeItemSlot(AchievementInventoryElement aile, Player player) {
        if (aile instanceof AchievementInventoryLeafElement) {
            return makeItemLeafSlot((AchievementInventoryLeafElement) aile, player);
        } else {
            return makeItemFolderSlot((AchievementInventoryFolderElement) aile);
        }
    }

    private ItemStack makeItemBasicSlot(AchievementItemSetup ais) {
        ItemStack item = new ItemStack(ais.getMaterial(), ais.getAmount());
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ais.getName());
            item.setItemMeta(meta);
        }

        return item;
    }

    private ItemStack makeItemLeafSlot(AchievementInventoryLeafElement aile, Player player) {
        AchievementItemSetup ais = aile.getAchievementItemSetup();
        AchievementInfo ai = aile.getAchievementInfo();
        PlayerAchievementData pad = ai.getAchievementData().getPlayerAchievementDataMap().get(player.getUniqueId().toString());

        ItemStack item = new ItemStack(ais.getMaterial(), ais.getAmount());
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ais.getName());
            ArrayList<String> Lore = new ArrayList<>();

            if (pad.isAchieved()) {
                meta.addEnchant(Enchantment.LOYALTY, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                Lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + ChatColor.ITALIC + "Unlocked");

                if (ai.getMaxPoints() > 0) {
                    Lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + ChatColor.ITALIC + pad.getPoints() + "/" + ai.getMaxPoints());
                }
            } else {
                Lore.add(ChatColor.GRAY + "" + ChatColor.BOLD + ChatColor.ITALIC + "Locked");

                if (ai.getMaxPoints() > 0) {
                    Lore.add(ChatColor.GRAY + "" + ChatColor.BOLD + ChatColor.ITALIC + pad.getPoints() + "/" + ai.getMaxPoints());
                }
            }

            meta.setLore(Lore);
            item.setItemMeta(meta);
        }

        return item;
    }

    private ItemStack makeItemFolderSlot(AchievementInventoryFolderElement aife) {
        AchievementItemSetup ais = aife.getAchievementItemSetup();
        ItemStack item = new ItemStack(ais.getMaterial(), ais.getAmount());
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ais.getName());
            ArrayList<String> Lore = new ArrayList<>();
            Lore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + ChatColor.ITALIC + "Category");
            meta.setLore(Lore);
            item.setItemMeta(meta);
        }

        return item;
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
}
