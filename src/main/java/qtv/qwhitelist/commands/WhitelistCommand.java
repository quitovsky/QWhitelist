package qtv.qwhitelist.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
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
                            ChatColor.GREEN + "Игрок " + playerName + " добавлен в вайтлист."
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                    sender.sendMessage(ChatColor.RED + "Игрок уже находится в вайтлисте.");
                }
            } else if (subCommand.equalsIgnoreCase("remove")) {
                try {
                    Statement statement = Db.getConnection().createStatement();
                    statement.executeUpdate("DELETE FROM players WHERE user = '" + playerName + "'");
                    statement.close();
                    sender.sendMessage(
                            ChatColor.GREEN + "Игрок " + playerName + " удален из вайтлиста."
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                    sender.sendMessage(ChatColor.RED + "Что-то пошло не так.");
                }
            }
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("clear")) {
                try {
                    Statement statement = Db.getConnection().createStatement();
                    statement.executeUpdate("DROP TABLE players");
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    sender.sendMessage(ChatColor.RED + "Что-то пошло не так...");
                }
                Db.initTable();
                sender.sendMessage(
                        ChatColor.GREEN + "Таблица players пересоздана."
                );
            }
        } else {
            sender.sendMessage(
                    "§cНеправильное использование. /wl <add/remove>"
            );
        }
    }
}
