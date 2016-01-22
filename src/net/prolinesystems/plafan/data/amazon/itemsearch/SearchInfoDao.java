package net.prolinesystems.plafan.data.amazon.itemsearch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

import net.prolinesystems.plafan.dao.AbstractDao;

public class SearchInfoDao extends AbstractDao {

	public SearchInfo getLast() {

		SearchInfo si = new SearchInfo();
		try {
			// initに持っていく？
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:plafan.db");

			String sql = "SELECT * FROM SearchInfo order by search_date desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				si.setYmd10(rs.getInt("ymd10"));
				Timestamp ts1 = rs.getTimestamp("search_date");
				Calendar cal = new GregorianCalendar();
				cal.setTimeInMillis(ts1.getTime());
				si.setSearchDate(cal);
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

		return si;
	}

	public void save(SearchInfo si) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:plafan.db");

			String sql = "INSERT INTO SearchInfo (ymd10, search_date) VALUES(?, ?);";
			pstmt = con.prepareStatement(sql);

			int itemSeq = 1;
			pstmt.setInt(1, si.getYmd10());
			Calendar cal =  si.getSearchDate();
			Timestamp ts = new Timestamp(cal.getTimeInMillis());
			pstmt.setTimestamp(2, ts);
			pstmt.executeUpdate();

			itemSeq++;
		} catch (Exception e) {
			e.printStackTrace();
		} finally

		{
			try {
				pstmt.close();
				con.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

}
