package net.prolinesystems.plafan.data.amazon.itemsearch;

import java.sql.DriverManager;

import net.prolinesystems.plafan.dao.AbstractDao;

public class SearchInfoDao extends AbstractDao {

	public Integer getLastYmd10() {

		Integer lastYmd10 = 0;
		try {
			// initに持っていく？
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:plafan.db");

			String sql = "SELECT max(ymd10) FROM SearchInfo";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				lastYmd10 = rs.getInt(1);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		return lastYmd10;
	}
}
