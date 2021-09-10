package dicordpls.pls.manager;

import java.awt.Color;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import dicordpls.pls.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class UtilsWarn {
	
	private Main plugin;

	public UtilsWarn(Main plugin) {
		this.plugin = plugin;
	}

	public void war(String id) {
		EmbedBuilder me = new EmbedBuilder();
		me.setColor(Color.RED);
		me.setTitle("SUA FACÇÃO ENTROU SOB ATAQUE");
		me.setDescription("Uma nova possível invasão foi detectada nos claims da sua facção, verifique as coordenadas"
				+ "do claim abaixo e tente protegê-las o mais rápido possível.");
		me.setFooter("A luta é o melhor caminho");
		me.setAuthor("FowFactions - Warn");
		MessageEmbed ee = me.build();
		Main.jda.retrieveUserById(id).complete().openPrivateChannel().complete().sendMessage(ee).queue();;
		
	}

	public void sucess(Player p, String id) {

		EmbedBuilder me = new EmbedBuilder();
		me.setColor(Color.GREEN);
		me.setTitle("SEU DISCORD FOI VINCULADO\n\n");
		me.setDescription("Você vinculou suas conta " + p.getName()
				+ " com sucesso. Agora você será notificado por discord caso sua facção esteja sob-ataque!"
				+ "\n\nCaso queria desvincular e/ou alterar, realize a operação novamente in-game.");
		me.setFooter("\n\nA luta é o melhor caminho");
		me.setAuthor("FowFactions ~");
		MessageEmbed ee = me.build();
		Main.jda.retrieveUserById(id).complete().openPrivateChannel().complete().sendMessage(ee).complete().addReaction("✔").queue();
	}
	

	public void openMenu(Player p) {

		Inventory inv = Bukkit.createInventory(null, 3 * 9, "§8Vincular Discord");

		ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta itemm = (SkullMeta) item.getItemMeta();
		itemm.setOwner(p.getName());
		ArrayList<String> lore = new ArrayList<>();
		itemm.setDisplayName("§fVincular Discord");
		lore.add("§7Clique para adicionar/alterar seu ID discord");
		lore.add("§7e receba as notificações quando sua");
		lore.add("§7facção estiver sob-ataque.");
		lore.add("");
		if (!Main.getInstance().mn.contains(p.getName())) {
			lore.add("§cNão Vinculado");
		} else {
			String id = Main.getInstance().mn.getId(p.getName());
			lore.add("§fID:§f " + id);
			lore.add("§fNome:§f " + Main.jda.retrieveUserById(id).complete().getName());
			lore.add("§aDiscord Vinculado");
		}
		itemm.setLore(lore);
		item.setItemMeta(itemm);

		inv.setItem(13, item);
		p.openInventory(inv);
	}

}
