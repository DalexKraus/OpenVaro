package at.dalex.util;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ChatUtil {

    public static void sendConsoleMessage(String message) {
        Bukkit.getServer().getConsoleSender().sendMessage(message);
    }

    private static void sendMessageWithSound(Player p, Sound sound, String message) {
        p.sendMessage(message);
        p.playSound(p.getLocation(), sound, 1.0f, 1.0f);
    }

    public static void success(Player p, String message) {
        sendMessageWithSound(p, Sound.LEVEL_UP, message);
    }

    public static void error(Player p, String message) {
        sendMessageWithSound(p, Sound.NOTE_BASS, "§4ERROR: §c" + message);
    }
}
