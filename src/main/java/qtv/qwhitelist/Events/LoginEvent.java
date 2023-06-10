package qtv.qwhitelist.Events;

import org.bukkit.event.player.PlayerLoginEvent;
import qtv.qwhitelist.utils.Config;

public class LoginEvent {

    public static void execute(PlayerLoginEvent event) {
        if(!Config.getBoolean("isRestricted")) return;
        if(!event.getPlayer().hasPermission("qwhitelist.bypass") && !event.getPlayer().isOp()) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, Config.getMessage("kickedRestricted"));
        }
    }

}
