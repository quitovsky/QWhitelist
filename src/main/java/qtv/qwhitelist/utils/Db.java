package qtv.qwhitelist.utils;

import org.bukkit.Bukkit;
import qtv.qwhitelist.QWhitelist;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Db {

    static QWhitelist main;
    static Connection connection;
    File dbFile;

    public Db(QWhitelist main) {
        Db.main = main;

        dbFile = new File(main.getDataFolder() + File.separator + "data.db");
        if(!dbFile.exists()) {
            main.getLogger().info("Database not found, creating one...");
            main.saveResource("data.db", false);
        }

        try {
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:" + main.getDataFolder() + File.separator + "data.db"
            );
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.getServer().getLogger().severe(
                    "Error while connecting to data.db, check stacktrace above. (shutting down)"
            );
            Bukkit.getServer().getPluginManager().disablePlugin(main);
        }

        initTable();
    }

    public static void initTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS players (user TEXT, unique(user))");
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.getServer().getLogger().severe(
                    "Error while creating players table, check stacktrace above. (shutting down)"
            );
            Bukkit.getServer().getPluginManager().disablePlugin(main);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public void closeDatabase() {
        try {
            connection.close();
            main.getLogger().info("Database closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
