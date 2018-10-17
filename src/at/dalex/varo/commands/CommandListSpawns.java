package at.dalex.varo.commands;

import at.dalex.varo.Main;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class CommandListSpawns {

    public static void invoke(Player p) {
        p.sendMessage("");
        p.sendMessage("§8 ---= §cSpawns §8=---\n");
        ArrayList<Vector> spawns = Main.getInstance().getSpawnManager().getSpawnPositions();
        for (int i = 0; i < spawns.size(); i++) {
            Vector current = spawns.get(i);
            p.sendMessage("§7#" + (i + 1) + ": X: §e"
                    + current.getX() + " §7Y: §e"
                    + current.getY() + " §7Z:§e "
                    + current.getZ());
        }
    }
}
