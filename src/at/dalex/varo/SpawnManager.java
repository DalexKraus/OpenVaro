package at.dalex.varo;

import at.dalex.util.ChatUtil;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class SpawnManager {

    private World varoWorld;
    private Vector middleSpawn;
    private ArrayList<Vector> spawnPositions = new ArrayList<>();

    public World getVaroWorld() {
        return this.varoWorld;
    }

    public void setVaroWorld(World varoWorld) {
        this.varoWorld = varoWorld;
    }

    public void setMiddleSpawn(Vector position) {
        this.middleSpawn = position;
    }

    public void addSpawnLocation(Vector location) {
        this.spawnPositions.add(location);
    }

    public boolean removeSpawnPoint(Vector location) {
        boolean removed = false;
        Vector targetLocation = null;
        for (Vector spawn : spawnPositions) {
            if (spawn.equals(location)) {
                targetLocation = spawn;
                removed = true;
                break;
            }
        }
        spawnPositions.remove(targetLocation);
        return removed;
    }

    public ArrayList<Vector> getSpawnPositions() {
        return this.spawnPositions;
    }

    public void teleportPlayers(List<Player> players) {
        for (Player p : players) {
            if (middleSpawn == null && p.isOp()) {
                ChatUtil.error(p, "Mitte wurde noch nicht gesetzt!");
            }

            //Face player to middle
            Location pLoc = p.getLocation();
            Vector playerWorldSpace = new Vector(pLoc.getBlockX(), pLoc.getBlockY(), pLoc.getBlockZ());
            Vector directionVector = middleSpawn.subtract(playerWorldSpace);
            p.getLocation().setDirection(directionVector.normalize());
        }
    }
}
