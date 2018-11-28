package at.dalex.varo.scheduler;

import at.dalex.util.TitleUtil;
import at.dalex.varo.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class StartCountdownScheduler implements Runnable {

    int schedulerTask;
    private int seconds;
    private Runnable finishRunnable;

    public StartCountdownScheduler(int secondsToCount, Runnable finishRunnable) {
        this.seconds = secondsToCount;
        this.finishRunnable = finishRunnable;

        //Mark game as RUNNING
        Main.getInstance().setGameRunning(true);

        schedulerTask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(),
                this, 0, 20L);
    }

    @Override
    public void run() {

        boolean shouldPrint = false;

        if (seconds >= 10) {
            if (seconds % 5 == 0) shouldPrint = true;
        }
        else if (seconds <= 5) shouldPrint = true;

        if (shouldPrint) {
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                p.sendMessage(Main.prefix + "§e" + seconds + "§7 Sekunden bis zum Start");
                p.playSound(p.getLocation(), Sound.NOTE_STICKS, 1.0f, 1.0f);

                //Send title
                TitleUtil.sendTitle(p, 5, 20, 5, "§4" + seconds + "§c Sekunden", "§7bis zum Start");
            }
        }

        seconds--;
        if (seconds == -1) {
            Thread executionThread = new Thread(finishRunnable);
            executionThread.start();

            Bukkit.getServer().getScheduler().cancelTask(schedulerTask);
        }
    }
}
