package dicordpls.pls.db;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dicordpls.pls.Main;


public class SQL {

	public static void setup() {
		File bansdb = new File(Main.getInstance().getDataFolder(), "discord.db");
		if (!bansdb.exists()) {
			try {
				bansdb.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Main.con = DriverManager
					.getConnection("jdbc:sqlite:" + Main.getInstance().getDataFolder() + File.separator + "discord.db");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			PreparedStatement ps = Main.con
					.prepareStatement("CREATE TABLE IF NOT EXISTS gp(nick varchar(24), id varchar(50), fac varchar(24))");
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
