package at.dalex.varo.listener;

import at.dalex.varo.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakBlockListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (!Main.getInstance().isGameRunning()) {
            e.setCancelled(true);
        }
    }
}
