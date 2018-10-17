package at.dalex.varo.commands;

import at.dalex.util.ChatUtil;
import at.dalex.varo.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class CommandAddSpawn {

    public static void invoke(Player p) {

        Location pLoc = p.getLocation();
        Vector spawnLocation = new Vector(pLoc.getX(), pLoc.getY(), pLoc.getZ());
        Main.getInstance().getSpawnManager().addSpawnLocation(spawnLocation);
        ChatUtil.success(p, "§aSpawn hinzugefügt! §8(§7X: §e"
                + pLoc.getX() + " Y: §e" + pLoc.getY() + " Z: §e" + pLoc.getZ() + "§8)");
    }
}
