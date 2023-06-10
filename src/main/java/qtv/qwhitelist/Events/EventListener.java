package qtv.qwhitelist.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class EventListener implements Listener {

    public EventListener() {}

    @EventHandler
    public void onJoin(AsyncPlayerPreLoginEvent e) {
        PreJoinEvent.execute(e);
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        LoginEvent.execute(e);
    }

}
