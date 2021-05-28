package ch.mixin.mixedAchievements.inventory;

import ch.mixin.mixedAchievements.api.AchievementInfo;
import ch.mixin.mixedAchievements.blueprint.AchievementItemSetup;
import ch.mixin.mixedAchievements.main.MixedAchievementsManagerAccessor;
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

    public AchievementInventoryFolderElement(MixedAchievementsManagerAccessor mixedAchievementsManagerAccessor, AchievementInventoryFolderElement parent, AchievementItemSetup achievementItemSetup, String inventoryName) {
        super(mixedAchievementsManagerAccessor, parent, achievementItemSetup);
        this.inventoryName = inventoryName;
        subAchievementInventoryElementMap = new HashMap<>();
    }

    protected String getSetName() {
        return parent.getSetName(this);
    }

    protected String getSetName(AchievementInventoryFolderElement achievementInventoryFolderElement) {
        return parent.getSetName(this);
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
        String setName = getSetName();
        String achievementId = ai.getAchievementData().getAchievementId();
        PlayerAchievementData pad = mixedAchievementsManagerAccessor.getAchievementManager().fetchPlayerAchievementData(setName, achievementId, player.getUniqueId());

        ItemStack item = new ItemStack(ais.getMaterial(), ais.getAmount());
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ais.getName());
            ArrayList<String> Lore = new ArrayList<>();

            if (pad.isAchieved()) {
                meta.addEnchant(Enchantment.LOYALTY, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                Lore.add(AchievementInventoryManager.UnlockedColor + "" + ChatColor.BOLD + ChatColor.ITALIC + "Unlocked");

                if (ai.getMaxPoints() > 0) {
                    Lore.add(AchievementInventoryManager.UnlockedColor + "" + ChatColor.BOLD + ChatColor.ITALIC + pad.getPoints() + "/" + ai.getMaxPoints());
                }
            } else {
                Lore.add(AchievementInventoryManager.LockedColor + "" + ChatColor.BOLD + ChatColor.ITALIC + "Locked");

                if (ai.getMaxPoints() > 0) {
                    Lore.add(AchievementInventoryManager.LockedColor + "" + ChatColor.BOLD + ChatColor.ITALIC + pad.getPoints() + "/" + ai.getMaxPoints());
                }
            }

            Lore.addAll(ais.getLoreList());
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
            Lore.add(AchievementInventoryManager.CategoryColor + "" + ChatColor.BOLD + ChatColor.ITALIC + "Category");
            Lore.addAll(ais.getLoreList());
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
