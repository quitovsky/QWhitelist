package qtv.qwhitelist.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import qtv.qwhitelist.QWhitelist;
import qtv.qwhitelist.utils.Config;
import qtv.qwhitelist.utils.Db;

import java.sql.Statement;

public class WhitelistCommand extends AbstractCommand {

    public WhitelistCommand() {
        super("qwhitelist");
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if (args.length == 2) {
            String subCommand = args[0];
            String playerName = args[1].toLowerCase();
            if(subCommand.equalsIgnoreCase("add")) {
                try {
                    Statement statement = Db.getConnection().createStatement();
                    statement.executeUpdate("INSERT INTO players (user) VALUES ('" + playerName + "')");
                    statement.close();
                    sender.sendMessage(
                            Config.getMessage("added").replace("%player", playerName)
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                    sender.sendMessage(
                            Config.getMessage("alreadyInList").replace("%player", playerName)
                    );
                }
            } else if (subCommand.equalsIgnoreCase("remove")) {
                try {
                    Statement statement = Db.getConnection().createStatement();
                    statement.executeUpdate("DELETE FROM players WHERE user = '" + playerName + "'");
                    statement.close();
                    sender.sendMessage(
                            Config.getMessage("removed").replace("%player", playerName)
                    );

                    Bukkit.getServer().dispatchCommand(
                            Bukkit.getConsoleSender(),
                            "kick " + playerName
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                    sender.sendMessage(
                            Config.getMessage("error")
                    );
                }
            }
        } else if (args.length == 1) {
            String subCommand = args[0];
            if (subCommand.equalsIgnoreCase("clear")) {
                try {
                    Statement statement = Db.getConnection().createStatement();
                    statement.executeUpdate("DROP TABLE players");
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    sender.sendMessage(
                            Config.getMessage("error")
                    );
                }
                Db.initTable();
                sender.sendMessage(
                        Config.getMessage("cleared")
                );
            } else if (subCommand.equalsIgnoreCase("reload")) {
                Config.reload();
                sender.sendMessage(
                        Config.getMessage("reloaded")
                );
            }
        } else {
            sender.sendMessage(
                    Config.getMessage("wrongUsage")
            );
        }
    }
}
