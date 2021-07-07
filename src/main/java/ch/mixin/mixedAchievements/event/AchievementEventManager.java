package ch.mixin.mixedAchievements.event;

import ch.mixin.mixedAchievements.main.MixedAchievementsData;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AchievementEventManager {
    private final MixedAchievementsData mixedAchievementsData;

    public AchievementEventManager(MixedAchievementsData mixedAchievementsData) {
        this.mixedAchievementsData = mixedAchievementsData;
    }

    public void callAchievementCompletedEvent(AchievementCompletedEvent event) {
        Bukkit.getPluginManager().callEvent(event);

        if (event.isCancelled())
            return;

        Player player = event.getPlayer();
        String message = event.getMessage();
        int borderLength = ChatColor.stripColor(message).length();
        borderLength = Math.max(0, borderLength - 2);
        String border = new String(new char[borderLength]).replace("\0", "=") + ChatColor.MAGIC + "x";
        player.sendMessage("");
        player.sendMessage(border);
        player.sendMessage(message);
        player.sendMessage(border);
        player.sendMessage("");
    }
}
