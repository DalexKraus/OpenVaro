package at.dalex.varo.scheduler;

import at.dalex.util.TitleUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class StartGameRunnable implements Runnable {

    @Override
    public void run() {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            TitleUtil.sendTitle(p, 5, 120, 5, "§eSchutzzeit", "§7Bleibt für 30 Sekunden aktiv!");
        }
    }
}
