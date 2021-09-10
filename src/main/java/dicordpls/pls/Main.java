package dicordpls.pls;

import java.io.File;
import java.sql.Connection;

import javax.security.auth.login.LoginException;

import org.bukkit.plugin.java.JavaPlugin;

import dicordpls.pls.db.SQL;
import dicordpls.pls.db.dbmanager;
import dicordpls.pls.manager.EventsCommand;
import dicordpls.pls.manager.SystemCache;
import dicordpls.pls.manager.UtilsWarn;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;

public class Main extends JavaPlugin {
	public static JDA jda;
	public static String tabela;
	public static Connection con;

	@Override
	public void onEnable() {
		if (!new File(getDataFolder(), "config.yml").exists()) {
			saveDefaultConfig();
		}
		try {
			jda = JDABuilder.createDefault("ODgyMzczNzM2Mzk1ODMzMzk0.YS6clA.AEmNF3AZu-fuJroJ9JrF6S4eb1A").build();
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		jda.getPresence().setStatus(OnlineStatus.ONLINE);
		getServer().getPluginManager().registerEvents(new EventsCommand(), this);
		getServer().getPluginManager().registerEvents(Cache(), this);
		tabela = "gp";
		SQL.setup();
	}
	
	@Override
	public void onDisable() {
		for (String users : Cache().cachedUsers.keySet()) {
			String[] deserializer = Cache().cachedUsers.get(users).split(":");
			String id = deserializer[0];
			String fac = deserializer[1];
			if (managerBD().contains(users)) {
				managerBD().UpdateId(users, id);
				managerBD().UpdateFac(users, fac);
			} else {
				managerBD().setDados(users, id, fac);
			}
		}
	}

	public dbmanager mn = new dbmanager(this);

	public dbmanager managerBD() {
		return mn;
	}

	public SystemCache cache = new SystemCache(this);

	public SystemCache Cache() {
		return cache;
	}

	public UtilsWarn utils = new UtilsWarn(this);

	public UtilsWarn Utils() {
		return utils;
	}

	public static Main getInstance() {
		return getPlugin(Main.class);
	}

}
