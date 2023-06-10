package qtv.qwhitelist.utils;

import org.bukkit.configuration.file.FileConfiguration;
import qtv.qwhitelist.QWhitelist;

import java.util.Objects;

public class Config {

    static FileConfiguration config;
    QWhitelist main;

    public Config(QWhitelist main) {
        main.saveDefaultConfig();
        config = main.getConfig();
        this.main = main;
    }

    public static String getMessage(String messageName) {
        return Objects.requireNonNull(config.getString("messages." + messageName)).replace('&', '§');
    }

    public static boolean getBoolean(String path) {
        return config.getBoolean(path);
    }

    public static void set(String path, Object value) {
        config.set(path, value);
    }

    public static void reload() {
        QWhitelist.getInstance().reloadConfig();
        config = QWhitelist.getInstance().getConfig();
    }

}
