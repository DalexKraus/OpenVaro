package at.dalex.varo.commands;

import at.dalex.util.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length == 0) {
                ChatUtil.error(p, "Zu wenig parameter!");
                return true;
            }
            else {
                if (args[0].equalsIgnoreCase("addSpawn")) {
                    CommandAddSpawn.invoke(p);
                    return true;
                }
                else if (args[0].equalsIgnoreCase("listSpawns")) {
                    CommandListSpawns.invoke(p);
                }
                else if (args[0].equalsIgnoreCase("createTeam")) {
                    CommandTeamCreate.invoke(p, args);
                }
                else if (args[0].equalsIgnoreCase("invite")) {
                    CommandInvite.invoke(p, args);
                }
                else if (args[0].equalsIgnoreCase("accept")) {
                    CommandAccept.invoke(p, args);
                }
                else if (args[0].equalsIgnoreCase("start")) {
                    CommandStart.invoke(p, args);
                }
            }

        }
        return true;
    }
}
