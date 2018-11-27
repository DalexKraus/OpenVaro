package at.dalex.varo.commands;

import at.dalex.util.ChatUtil;
import at.dalex.varo.Main;
import at.dalex.varo.team.Team;
import at.dalex.varo.team.TeamManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CommandInvite {

    public static void invoke(Player p, String[] args) {
        if (args.length == 1) {
            ChatUtil.error(p, Main.prefix + "§cDu musst einen Spieler angeben, " +
                    "welchen du in dein Team einladen möchtest!");
        }
        else {
            TeamManager teamManager = Main.getInstance().getTeamManager();
            Player targetPlayer = Bukkit.getServer().getPlayer(args[1]);
            if (targetPlayer.isOnline()) {
                if (teamManager.getTeam(p.getUniqueId()) != null) {
                    if (teamManager.getTeam(targetPlayer.getUniqueId()) == null) {

                        teamManager.getTeam(p.getUniqueId()).addMember(targetPlayer.getUniqueId());
                        p.sendMessage(Main.prefix + "§aDu hast den Spieler §e" + args[1] + "§a in dein Team eingeladen!");
                        p.playSound(p.getLocation(), Sound.BURP, 1.0f, 2.0f);

                        String teamContraction = teamManager.getTeam(p.getUniqueId()).getContraction();
                        targetPlayer.sendMessage("\n" + Main.prefix + "§7Der Spieler §e" + p.getName()
                                + "§7 hat dich in das Team §6#"
                                + teamContraction + "§7 eingeladen.\n" +
                                "§5/Varo accept §d" + teamContraction + " §8um die Anfrage zu akzeptieren");

                        //Send invitation
                        teamManager.invitePlayerToTeam(targetPlayer.getUniqueId(), teamContraction);

                    } else ChatUtil.error(p, Main.prefix + "§4ERROR: §cDer angegebene Spieler befindet sich bereits in einem Team!");
                } else ChatUtil.error(p, Main.prefix + "§4ERROR: §cDu befindest dich momentan in keinem Team!");
            } else ChatUtil.error(p, Main.prefix + "§4ERROR: §cDer angegebene Spieler ist nicht online!");
        }
    }
}
