package qtv.qwhitelist;

import org.bukkit.plugin.java.JavaPlugin;
import qtv.qwhitelist.commands.Commands;
import qtv.qwhitelist.utils.Db;

public final class QWhitelist extends JavaPlugin {

    static QWhitelist instance;
    Db db;

    @Override
    public void onEnable() {
        db = new Db(this);
        instance = this;
        new Commands();
    }

    @Override
    public void onDisable() {
        db.closeDatabase();
    }

    public static QWhitelist getInstance() {
        return instance;
    }
}
