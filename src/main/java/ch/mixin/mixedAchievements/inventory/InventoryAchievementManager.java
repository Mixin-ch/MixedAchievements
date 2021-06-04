package ch.mixin.mixedAchievements.inventory;

import ch.mixin.mixedAchievements.blueprint.AchievementItemSetup;
import ch.mixin.mixedAchievements.main.MixedAchievementsManagerAccessor;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class InventoryAchievementManager {
    public static int CancelSlot = 8;
    public static AchievementItemSetup CancelItem = new AchievementItemSetup();
    public static ChatColor CategoryColor = ChatColor.of("#7F7FFF");
    public static ChatColor CompletedColor = ChatColor.of("#FFBF00");
    public static ChatColor IncompletedColor = ChatColor.of("#BFBFBF");
    public static ChatColor StageColor = ChatColor.of("#FFFFFF");

    static {
        CancelItem.setMaterial(Material.BARRIER);
        CancelItem.setName(ChatColor.of("#FF7F7F") + "Back");
        CancelItem.setAmount(1);
    }

    private final MixedAchievementsManagerAccessor mixedAchievementsManagerAccessor;
    private final InventoryAchievementRoot inventoryAchievementRoot;
    private final HashMap<String, ActiveInventoryAchievement> playerActiveInventoryMap = new HashMap<>();

    public InventoryAchievementManager(MixedAchievementsManagerAccessor mixedAchievementsManagerAccessor) {
        this.mixedAchievementsManagerAccessor = mixedAchievementsManagerAccessor;
        inventoryAchievementRoot = new InventoryAchievementRoot(mixedAchievementsManagerAccessor);
        reload();
    }

    public void reload() {
        for (ActiveInventoryAchievement aai : playerActiveInventoryMap.values()) {
            for (HumanEntity humanEntity : aai.getInventory().getViewers()) {
                if (humanEntity.getInventory() == aai.getInventory())
                    humanEntity.closeInventory();
            }
        }
    }

    public void open(Player player) {
        InventoryAchievementCategory iac = inventoryAchievementRoot;

        if (inventoryAchievementRoot.getInventoryAchievementSetMap().size() == 1) {
            iac = (InventoryAchievementCategory) inventoryAchievementRoot.getInventoryAchievementSetMap().values().toArray(new InventoryAchievementElement[1])[0];
        }

        Inventory inventory = iac.generateInventory(player);
        player.openInventory(inventory);
        playerActiveInventoryMap.put(player.getUniqueId().toString(), new ActiveInventoryAchievement(iac, inventory));
    }

    public void click(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ActiveInventoryAchievement aia = playerActiveInventoryMap.get(player.getUniqueId().toString());

        if (aia == null)
            return;

        Inventory inventory = event.getInventory();

        if (inventory != aia.getInventory())
            return;

        int slot = event.getRawSlot();

        if (slot >= inventory.getSize())
            return;

        event.setCancelled(true);
        InventoryAchievementCategory aic = aia.getAchievementInventoryFolderElement();
        InventoryAchievementCategory newAic;

        if (slot == CancelSlot) {
            if (aic.getParent() == null) {
                player.closeInventory();
                return;
            }

            newAic = aic.getParent();
        } else {
            InventoryAchievementElement aie = aic.getInventoryAchievementElementMap().get(slot);

            if (aie == null)
                return;

            if (!(aie instanceof InventoryAchievementCategory))
                return;

            newAic = (InventoryAchievementCategory) aie;
        }

        Inventory newInventory = newAic.generateInventory(player);
        player.openInventory(newInventory);
        playerActiveInventoryMap.put(player.getUniqueId().toString(), new ActiveInventoryAchievement(newAic, newInventory));
    }

    public void drag(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();
        ActiveInventoryAchievement aai = playerActiveInventoryMap.get(player.getUniqueId());

        if (aai == null)
            return;

        Inventory inventory = event.getInventory();

        if (inventory != aai.getInventory())
            return;

        for (int slot : event.getRawSlots()) {
            if (slot < aai.getInventory().getSize()) {
                event.setCancelled(true);
                break;
            }
        }
    }

    public InventoryAchievementRoot getAchievementRootInventory() {
        return inventoryAchievementRoot;
    }
}
