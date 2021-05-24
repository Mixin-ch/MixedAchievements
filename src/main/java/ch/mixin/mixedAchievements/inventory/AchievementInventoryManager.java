package ch.mixin.mixedAchievements.inventory;

import ch.mixin.mixedAchievements.blueprint.AchievementItemSetup;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.UUID;

public class AchievementInventoryManager {
    public static int CancelSlot = 8;
    public static AchievementItemSetup CancelItem = new AchievementItemSetup();

    static {
        CancelItem.setMaterial(Material.BARRIER);
        CancelItem.setName("Back");
        CancelItem.setAmount(1);
    }

    private final AchievementRootInventory achievementRootInventory = new AchievementRootInventory();
    private final HashMap<UUID, ActiveAchievementInventory> playerActiveInventoryMap = new HashMap<>();


    public AchievementInventoryManager() {
        reload();
    }

    public AchievementRootInventory getAchievementRootInventory() {
        return achievementRootInventory;
    }

    public void reload() {
        for (ActiveAchievementInventory aai : playerActiveInventoryMap.values()) {
            for (HumanEntity humanEntity : aai.getInventory().getViewers()) {
                if (humanEntity.getInventory() == aai.getInventory())
                    humanEntity.closeInventory();
            }
        }
    }

    public void open(Player player) {
        AchievementInventoryFolderElement aife = achievementRootInventory;

        if (achievementRootInventory.getSubAchievementInventoryElementMap().size() == 1) {
            aife = (AchievementInventoryFolderElement) achievementRootInventory.getSubAchievementInventoryElementMap().values().toArray(new AchievementInventoryElement[1])[0];
        }

        Inventory inventory = aife.generateInventory(player);
        player.openInventory(inventory);
        playerActiveInventoryMap.put(player.getUniqueId(), new ActiveAchievementInventory(aife, inventory));
    }

    public void click(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ActiveAchievementInventory aai = playerActiveInventoryMap.get(player.getUniqueId());

        if (aai == null)
            return;

        Inventory inventory = event.getInventory();

        if (inventory != aai.getInventory())
            return;

        int slot = event.getRawSlot();

        if (slot >= inventory.getSize())
            return;

        event.setCancelled(true);
        AchievementInventoryFolderElement aife = aai.getAchievementInventoryFolderElement();
        AchievementInventoryFolderElement newAife;

        if (slot == CancelSlot) {
            if (aife.getParent() == null)
                return;

            newAife = (AchievementInventoryFolderElement) aife.getParent();
        } else {
            AchievementInventoryElement aie = aife.getSubAchievementInventoryElementMap().get(slot);

            if (aie == null)
                return;

            if (!(aie instanceof AchievementInventoryFolderElement))
                return;

            newAife = (AchievementInventoryFolderElement) aie;
        }

        Inventory newInventory = newAife.generateInventory(player);
        player.openInventory(newInventory);
        playerActiveInventoryMap.put(player.getUniqueId(), new ActiveAchievementInventory(newAife, newInventory));
    }

    public void drag(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();
        ActiveAchievementInventory aai = playerActiveInventoryMap.get(player.getUniqueId());

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
}
