package at.dalex.varo.commands;

import at.dalex.util.ChatUtil;
import at.dalex.varo.Main;
import at.dalex.varo.team.Team;
import org.bukkit.entity.Player;

public class CommandTeamCreate {

    public static void invoke(Player p, String[] args) {

        if (Main.getInstance().isGameRunning()) {
            ChatUtil.error(p, "§cDu kannst kein neues Team erstellen, während das Spiel läuft!");
            return;
        }

        if (args.length == 1) {
            ChatUtil.error(p, Main.prefix + "§4ERROR: §cDu musst einen Kürzel für dein Team angeben!");
            return;
        }
        else {
            if (args[1].length() > 3) {

                if (Main.getInstance().getTeamManager().getTeam(args[1]) == null) {
                    //Create team
                    Team team = new Team(args[1]);
                    ChatUtil.success(p, Main.prefix + "§aDu hast ein Team mit dem Kürzel §e" + args[1] + " §aerstellt!");

                    //Join team
                    team.addMember(p.getUniqueId());
                    p.sendMessage(Main.prefix + "§eDu bist dem Team §6#" + args[1] + "§e beigetreten");

                    //Register team
                    Main.getInstance().getTeamManager().addTeam(team);
                } else ChatUtil.error(p, Main.prefix + "§cEs existiert bereits ein Team mit dem Kürzel §e" + args[1] + "§c!");
            } else ChatUtil.error(p, Main.prefix + "§cKürzel muss länger als 3 Zeichen sein.");
        }
    }
}
