package at.dalex.varo;

import at.dalex.util.ChatUtil;
import at.dalex.varo.commands.Commands;
import at.dalex.varo.listener.BreakBlockListener;
import at.dalex.varo.team.TeamManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static String prefix = "§8[§9Varo§8]§7 ";

    private static Main instance;
    private TeamManager teamManager;
    private SpawnManager spawnManager;
    private boolean isRunning = false;
    private boolean isProtectionActive = false;

    @Override
    public void onEnable() {
        instance = this;

        getCommand("varo").setExecutor(new Commands());

        this.teamManager = new TeamManager();
        this.spawnManager = new SpawnManager();

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new BreakBlockListener(), this);

        ChatUtil.sendConsoleMessage(prefix + "§aPlugin geladen");
    }

    @Override
    public void onDisable() {

        ChatUtil.sendConsoleMessage(prefix + "§7Plugin deaktiviert.");
    }

    public boolean isGameRunning() {
        return this.isRunning;
    }

    public void setGameRunning(boolean running) {
        this.isRunning = running;
    }

    public TeamManager getTeamManager() {
        return this.teamManager;
    }

    public boolean isProtectionActive() {
        return this.isProtectionActive;
    }

    public void setProtectionActive(boolean isProtectionActive) {
        this.isProtectionActive = isProtectionActive;
    }

    public SpawnManager getSpawnManager() {
        return this.spawnManager;
    }

    public static Main getInstance() {
        return instance;
    }

}
