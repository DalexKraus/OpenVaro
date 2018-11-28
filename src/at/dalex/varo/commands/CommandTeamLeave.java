package at.dalex.varo.commands;

import at.dalex.util.ChatUtil;
import at.dalex.varo.Main;
import at.dalex.varo.team.Team;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CommandTeamLeave {

    public static void invoke(Player p, String[] args) {
        if (!Main.getInstance().isGameRunning()) {
            if (Main.getInstance().getTeamManager().getTeam(p.getUniqueId()) != null) {
                if (args.length == 1) {
                    TextComponent messageComponent = new TextComponent();
                    TextComponent approveComponent = new TextComponent();
                    messageComponent.setText("§cBestätige, dass du dein Team wirklich verlassen möchtest: ");
                    approveComponent.setText("§7[§4TEAM VERLASSEN§7]");
                    approveComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§4Klicke, um dein Team zu verlassen").create()));
                    approveComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/varo leaveteam approve"));
                    p.spigot().sendMessage(messageComponent);
                    p.spigot().sendMessage(approveComponent);
                } else {
                    if (args[1].equalsIgnoreCase("approve")) {
                        //Save team for later processing
                        Team team = Main.getInstance().getTeamManager().getTeam(p.getUniqueId());
                        //Remove player from team
                        Main.getInstance().getTeamManager().getTeam(p.getUniqueId()).removeMember(p.getUniqueId());
                        p.sendMessage(Main.prefix + "§7Du hast dein Team verlassen.");

                        if (team.getMembers().size() == 0) {
                            p.sendMessage(Main.prefix + "§7Das Team §e" + team.getContraction() + " §7wurde aufgelöst.");
                            Main.getInstance().getTeamManager().removeTeam(team);
                        } else {
                            for (UUID memberID : Main.getInstance().getTeamManager().getTeam(p.getUniqueId()).getMembers()) {
                                Player member = Bukkit.getPlayer(memberID);
                                if (member.isOnline()) {
                                    member.sendMessage(Main.prefix + "§8" + p.getName() + " §7hat das Team verlassen.");
                                }
                            }
                        }
                    }
                }
            } else p.sendMessage(Main.prefix + "§4ERROR: §cDu bist momentan in keinem Team!");
        } else ChatUtil.error(p, "§cDu kannst dein Team nicht verlassen, während das Spiel läuft!");
    }
}
