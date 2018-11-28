package at.dalex.varo.commands;

import at.dalex.util.ChatUtil;
import at.dalex.varo.Main;
import at.dalex.varo.scheduler.StartCountdownScheduler;
import at.dalex.varo.scheduler.StartGameRunnable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;

public class CommandStart implements Runnable {

    private static StartCountdownScheduler cdScheduler;

    public static void invoke(Player p, String[] args) {
        if (p.hasPermission("varo.start")) {

            if (args.length == 1) {
                ChatUtil.error(p, Main.prefix + "§4ERROR: §cDu musst den Countdown in Sekunden angeben!");
                return;
            }
            else {
                try {
                    int countDown = Integer.parseInt(args[1]);
                    cdScheduler = new StartCountdownScheduler(countDown, new StartGameRunnable());

                } catch (NumberFormatException e) {
                    ChatUtil.error(p, "§4ERROR: §cDer Countdown muss eine Ganzzahl sein!");
                }
            }
        } else ChatUtil.error(p, Main.prefix + "§4ERROR: §cDu hast nicht die benötigten Rechte, " +
                "um das Spiel zu starten!");
    }

    @Override
    public void run() {
        Location nullLocation = new Location(Main.getInstance().getSpawnManager().getVaroWorld(),
                0, 0, 0, 0.0f, 0.0f);

        ArrayList<Player> teleportedPlayers = new ArrayList<>();
        ArrayList<Vector> spawnPositions = Main.getInstance().getSpawnManager().getSpawnPositions();


        //Teleport all Players
        for (int i = 0; i < spawnPositions.size(); i++) {
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                if (!teleportedPlayers.contains(player)) {
                    //Create spawn location
                    Vector pos = spawnPositions.get(i);
                    Location spawnLocation = new Location(
                            Main.getInstance().getSpawnManager().getVaroWorld(),
                            pos.getX(), pos.getY(), pos.getZ());

                    player.teleport(spawnLocation);
                    teleportedPlayers.add(player);

                    //Set target direction
                    //Vector lookDir = Main.getInstance().getSpawnManager().
                }
            }
        }
    }
}
