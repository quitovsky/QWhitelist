package qtv.qwhitelist.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import qtv.qwhitelist.QWhitelist;
import qtv.qwhitelist.utils.Db;

import java.sql.ResultSet;
import java.sql.Statement;

public class JoinEvent implements Listener {

    public static void execute(AsyncPlayerPreLoginEvent e) {
        try {
            Statement statement = Db.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT(user) as count FROM players WHERE user='" + e.getName().toLowerCase() + "'");
            rs.next();
            if(rs.getInt("count") == 0) {
                QWhitelist.getInstance().getLogger().info("Player " + e.getName() + " is not in the database.");
                e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "Тебя нет в вайтлисте.");
            }
            statement.close();
        } catch (Exception exception) {
            exception.printStackTrace();
            QWhitelist.getInstance().getLogger().info("Error in JoinEvent");
        }
    }

}
