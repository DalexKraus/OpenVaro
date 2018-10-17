package at.dalex.varo.commands;

import at.dalex.util.ChatUtil;
import at.dalex.varo.Main;
import at.dalex.varo.team.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class CommandAccept {

    public static void invoke(Player p, String[] args) {

        TeamManager teamManager = Main.getInstance().getTeamManager();

        for (String invitation : teamManager.getTeamInvitations(p.getUniqueId())) {
            if (invitation.equalsIgnoreCase(args[1])) {

                teamManager.removeInvitation(p.getUniqueId(), args[1]);
                teamManager.getTeam(args[1]).addMember(p.getUniqueId());
                ChatUtil.success(p, Main.prefix + "§aDu bist dem Team §e" + args[1] + "§a beigetreten!");

                for (UUID member : teamManager.getTeam(args[1]).getMembers()) {
                    Player pMember = Bukkit.getServer().getPlayer(member);
                    if (pMember.isOnline()) {
                        pMember.sendMessage(Main.prefix + "§e" + p.getName() + "§7 ist deinem Team beigetreten");
                    }
                }
                return;
            }
        }

        ChatUtil.error(p, Main.prefix + "§4ERROR: §cDu hast keine Anfrage von diesem Team bekommen!");
    }
}
