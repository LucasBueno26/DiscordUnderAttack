package dicordpls.pls.manager;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import dicordpls.pls.Main;

public class SystemCache implements Listener {
	
	private Main plugin;

	public SystemCache(Main plugin) {
		this.plugin = plugin;
	}
	
	public HashMap<String, String> cachedUsers = new HashMap<>();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (!cachedUsers.containsKey(p.getName()) && Main.getInstance().mn.contains(p.getName())) {
			
			String id = Main.getInstance().mn.getId(p.getName());
			String fac = Main.getInstance().mn.getFac(p.getName());
			String serializer = id + ":" + fac;
			cachedUsers.put(p.getName(), serializer);
		}
	}

}
