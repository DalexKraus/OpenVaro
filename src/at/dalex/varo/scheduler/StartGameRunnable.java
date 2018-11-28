package at.dalex.varo.scheduler;

import at.dalex.util.TitleUtil;
import at.dalex.varo.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class StartGameRunnable implements Runnable {

    @Override
    public void run() {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            TitleUtil.sendTitle(p, 5, 120, 5, "§eSchutzzeit", "§7Bleibt für 30 Sekunden aktiv!");
        }
        //Start protection
        Main.getInstance().setProtectionActive(true);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(),
                new Runnable() {
                    @Override
                    public void run() {
                        Main.getInstance().setProtectionActive(false);
                        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                            TitleUtil.sendTitle(p, 5, 40, 5, "§eSchutzzeit", "§cIst nun §4§ldeaktiviert§c!");
                            p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1.0f, 0.5f);
                        }
                    }
                }, 30 * 20L);

    }
}
