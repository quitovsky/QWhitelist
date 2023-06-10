package qtv.qwhitelist.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class EventListener implements Listener {

    public EventListener() {}

    @EventHandler
    public void onJoin(AsyncPlayerPreLoginEvent e) {
        JoinEvent.execute(e);
    }

}
