package dicordpls.pls.manager;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.atlasplugins.factions.atlasfactions.AtlasAPI;
import com.atlasplugins.factions.atlasfactions.events.AtlasFactionDeleteEvent;
import com.atlasplugins.factions.atlasfactions.events.AtlasPlayerExitFactionEvent;

import dicordpls.pls.Main;

public class ListenerControl implements Listener {
	
	public static ArrayList<String> delay = new ArrayList<>();

	
	@EventHandler
	public void onQuoit2(AtlasFactionDeleteEvent e) {
		String p = e.getLider();
		if (Main.getInstance().Cache().cachedUsers.containsKey(p)) {
			Main.getInstance().Cache().cachedUsers.remove(p);
			Main.getInstance().managerBD().DeletePlayer(p);
		}
	}

	@EventHandler
	public void onquit(AtlasPlayerExitFactionEvent e) {
		Player p = e.getPlayerExit();
		if (Main.getInstance().Cache().cachedUsers.containsKey(p.getName())) {
			Main.getInstance().Cache().cachedUsers.remove(p.getName());
			Main.getInstance().managerBD().DeletePlayer(p.getName());
		}
	}
	

	@EventHandler
	public void onMessage(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if (delay.contains(p.getName())) {
			e.setCancelled(true);
			String id = e.getMessage();
			if (id.contains("#")) {
				p.sendMessage("§cÉ necessário ser o ID -");
				p.sendMessage("§f");
				p.sendMessage("§e1§f- Entre no seu discord.");
				p.sendMessage("§e2§f- Configurações de Usuário.");
				p.sendMessage("§e3§f- Ao lado da sua TAG, nos 3 pontos (...), clique.");
				p.sendMessage("§7Agora forneça esse ID no chat para vincular seu discord.");
				p.sendMessage("");
				return;
			}
			delay.remove(p.getName());
			Main.getInstance().Cache().cachedUsers.put(p.getName(), id + ":" + AtlasAPI.getAtlasUser(p).getFaction().getFactionName());
			Main.getInstance().Utils().sucess(p, id);
			p.sendMessage("§f");
			p.sendMessage("§aVocê recebeu uma mensagem privada em seu discord! §2Confirme§a.");
			p.sendMessage("§f");
		}
	}

	@EventHandler
	public void onCLick(InventoryClickEvent e) {
		if (e.getInventory().getTitle().equalsIgnoreCase("§8Vincular Discord")) {
			e.setCancelled(true);
			if (e.getSlot() == 13) {
				e.getWhoClicked().sendMessage("§7");
				e.getWhoClicked().sendMessage("§fDigite no chat o §eID§f do seu discord.");
				e.getWhoClicked().sendMessage("§fA operação será cancelada em §e30 segundos§f.");
				e.getWhoClicked().sendMessage("§7");
				e.getWhoClicked().closeInventory();
				delay.add(e.getWhoClicked().getName());

				new BukkitRunnable() {

					@Override
					public void run() {
						if (delay.contains(e.getWhoClicked().getName())) {
							delay.remove(e.getWhoClicked().getName());

							e.getWhoClicked().sendMessage("§7");
							e.getWhoClicked().sendMessage("§cOperação de vinculação cancelada.");
							e.getWhoClicked().sendMessage("§7");
						}
					}
				}.runTaskLater(Main.getInstance(), 20L * 30);
			}
		}
	}


}
