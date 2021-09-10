package dicordpls.pls.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dicordpls.pls.Main;

public class dbmanager {
	
	private Main plugin;

	public dbmanager(Main plugin) {
		this.plugin = plugin;
	}
	
	public boolean contains(String player) {
		try (PreparedStatement ps = Main.con
				.prepareStatement("SELECT * FROM gp WHERE nick='" + player.toLowerCase() + "'")) {
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String getId(String player) {
		PreparedStatement stm = null;
		try {
			stm = Main.con.prepareStatement("SELECT * FROM gp WHERE `nick` = ?");
			stm.setString(1, player.toLowerCase());
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				return rs.getString("id");
			}
			return "";
		} catch (SQLException e) {
			return "";
		}
	}

	public String getFac(String player) {
		PreparedStatement stm = null;
		try {
			stm = Main.con.prepareStatement("SELECT * FROM gp WHERE `nick` = ?");
			stm.setString(1, player.toLowerCase());
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				return rs.getString("fac");
			}
			return "";
		} catch (SQLException e) {
			return "";
		}
	}

	public void UpdateId(String player, String id) {
		PreparedStatement stm = null;

		try {
			stm = Main.con.prepareStatement(
					"UPDATE gp SET id ='" + id + "'  WHERE nick='" + player.toLowerCase() + "'");
			stm.execute();
		} catch (SQLException var5) {
			var5.printStackTrace();
		}

	}

	public void setDados(String nome, String id, String fac) {
		try {
			PreparedStatement ps = Main.con.prepareStatement("INSERT INTO `gp` (`nick`, `id`, `fac`) VALUES (?,?,?)");
			ps.setString(1, nome.toLowerCase());
			ps.setString(2, id);
			ps.setString(3, fac);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void DeletePlayer(String player) {
		PreparedStatement stm = null;

		try {
			stm = Main.con.prepareStatement("DELETE FROM " + Main.tabela + " WHERE nick='"
					+ player.toLowerCase() + "'");
			stm.execute();
		} catch (SQLException var5) {
			var5.printStackTrace();
		}
	}
	
	public void UpdateFac(String player, String fac) {
		PreparedStatement stm = null;

		try {
			stm = Main.con.prepareStatement(
					"UPDATE gp SET fac ='" + fac + "'  WHERE nick='" + player.toLowerCase() + "'");
			stm.execute();
		} catch (SQLException var5) {
			var5.printStackTrace();
		}

	}


}
