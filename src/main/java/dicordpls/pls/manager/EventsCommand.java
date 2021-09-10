package dicordpls.pls.manager;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import dicordpls.pls.Main;

public class EventsCommand implements Listener {
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if (e.getMessage().equalsIgnoreCase("f discordm") || e.getMessage().equalsIgnoreCase("/f discordm")) {
			Main.getInstance().Utils().openMenu(p);
		}
	}

}
