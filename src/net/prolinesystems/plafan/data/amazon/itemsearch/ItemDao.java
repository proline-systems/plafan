package net.prolinesystems.plafan.data.amazon.itemsearch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemDao {

	public void saveItemList(List<Item> itemList) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:plafan.db");

			// String sql = "DELETE FROM ITEM where category = ?";
			// pstmt = con.prepareStatement(sql);
			// pstmt.setString(1, category);
			// pstmt.executeUpdate();
			// pstmt.close();

			String sql = "INSERT INTO ITEM (category, ASIN, TITLE, SALES_RANK, SmallImageURL, MediumImageURL, LargeImageURL, ean, ListPrice, ReleaseDate, Condition, OfferPrice, PercentageSaved, Availability, DetailPageURL, create_ymd10, update_ymd10) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			pstmt = con.prepareStatement(sql);

			int itemSeq = 1;
			for (Item item : itemList) {
				pstmt.setString(1, item.getCategory());
				pstmt.setString(2, item.getAsin());
				pstmt.setString(3, item.getTitle());
				pstmt.setInt(4, item.getSalesRank());
				pstmt.setString(5, item.getSmallImageURL());
				pstmt.setString(6, item.getMediumImageURL());
				pstmt.setString(7, item.getLargeImageURL());
				pstmt.setString(8, item.getEan());
				pstmt.setInt(9, item.getListPrice());
				pstmt.setString(10, item.getReleaseDate());
				pstmt.setString(11, item.getCondition());
				pstmt.setInt(12, item.getOfferPrice());
				pstmt.setInt(13, item.getPercentageSaved());
				pstmt.setString(14, item.getAvailability());
				pstmt.setString(15, item.getDetailPageURL());
				pstmt.setInt(16, item.getCreateYMD10());
				pstmt.setInt(17, item.getUpdateYMD10());
				pstmt.executeUpdate();

				itemSeq++;
			}
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

	public void saveItem(Item item) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:plafan.db");

			// String sql = "DELETE FROM ITEM where category = ?";
			// pstmt = con.prepareStatement(sql);
			// pstmt.setString(1, category);
			// pstmt.executeUpdate();
			// pstmt.close();

			String sql = "INSERT INTO ITEM (category, ASIN, TITLE, SALES_RANK, SmallImageURL, MediumImageURL, LargeImageURL, ean, ListPrice, ReleaseDate, Condition, OfferPrice, PercentageSaved, Availability, DetailPageURL, create_ymd10, update_ymd10) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			pstmt = con.prepareStatement(sql);

			int itemSeq = 1;
			pstmt.setString(1, item.getCategory());
			pstmt.setString(2, item.getAsin());
			pstmt.setString(3, item.getTitle());
			pstmt.setInt(4, item.getSalesRank());
			pstmt.setString(5, item.getSmallImageURL());
			pstmt.setString(6, item.getMediumImageURL());
			pstmt.setString(7, item.getLargeImageURL());
			pstmt.setString(8, item.getEan());
			pstmt.setInt(9, item.getListPrice());
			pstmt.setString(10, item.getReleaseDate());
			pstmt.setString(11, item.getCondition());
			pstmt.setInt(12, item.getOfferPrice());
			pstmt.setInt(13, item.getPercentageSaved());
			pstmt.setString(14, item.getAvailability());
			pstmt.setString(15, item.getDetailPageURL());
			pstmt.setInt(16, item.getCreateYMD10());
			pstmt.setInt(17, item.getUpdateYMD10());
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

	public List<Item> load() {

		List<Item> itemList = new ArrayList<>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:plafan.db");

			String sql = "SELECT _id, category, ASIN, TITLE, SALES_RANK, SmallImageURL, MediumImageURL, LargeImageURL, ean, ListPrice, ReleaseDate, Condition, OfferPrice, PercentageSaved, Availability, DetailPageURL, create_ymd10, update_ymd10 FROM ITEM order by _id";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Item item = new Item();
				item.setId(rs.getInt("_id"));// , DetailPageURL, YMD
				item.setCategory(rs.getString("category"));
				item.setAsin(rs.getString("ASIN"));
				item.setTitle(rs.getString("TITLE"));
				item.setSalesRank(rs.getInt("SALES_RANK"));
				item.setSmallImageURL(rs.getString("SmallImageURL"));
				item.setMediumImageURL(rs.getString("MediumImageURL"));
				item.setLargeImageURL(rs.getString("LargeImageURL"));
				item.setEan(rs.getString("ean"));
				item.setListPrice(rs.getInt("ListPrice"));
				item.setReleaseDate(rs.getString("ReleaseDate"));
				item.setCondition(rs.getString("Condition"));
				item.setOfferPrice(rs.getInt("OfferPrice"));
				item.setPercentageSaved(rs.getInt("PercentageSaved"));
				item.setAvailability(rs.getString("Availability"));
				item.setDetailPageURL(rs.getString("DetailpageURL"));
				item.setCreateYMD10(rs.getInt("create_ymd10"));
				item.setUpdateYMD10(rs.getInt("update_ymd10"));

				itemList.add(item);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		return itemList;
	}

	public List<Item> loadItemByPercentageSaved(int percentage, int ymd10) {

		List<Item> itemList = new ArrayList<>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:plafan.db");

			String sql = "SELECT _id, category, ASIN, TITLE, SALES_RANK, SmallImageURL, MediumImageURL, LargeImageURL, ean, ListPrice, ReleaseDate, Condition, OfferPrice, PercentageSaved, Availability, DetailPageURL, create_ymd10, update_ymd10 FROM ITEM WHERE PercentageSaved >= ? and create_ymd10=? order by _id";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, percentage);
			pstmt.setInt(2, ymd10);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Item item = new Item();
				item.setId(rs.getInt("_id"));// , DetailPageURL, YMD
				item.setCategory(rs.getString("category"));
				item.setAsin(rs.getString("ASIN"));
				item.setTitle(rs.getString("TITLE"));
				item.setSalesRank(rs.getInt("SALES_RANK"));
				item.setSmallImageURL(rs.getString("SmallImageURL"));
				item.setMediumImageURL(rs.getString("MediumImageURL"));
				item.setLargeImageURL(rs.getString("LargeImageURL"));
				item.setEan(rs.getString("ean"));
				item.setListPrice(rs.getInt("ListPrice"));
				item.setReleaseDate(rs.getString("ReleaseDate"));
				item.setCondition(rs.getString("Condition"));
				item.setOfferPrice(rs.getInt("OfferPrice"));
				item.setPercentageSaved(rs.getInt("PercentageSaved"));
				item.setAvailability(rs.getString("Availability"));
				item.setDetailPageURL(rs.getString("DetailpageURL"));
				item.setCreateYMD10(rs.getInt("create_ymd10"));
				item.setUpdateYMD10(rs.getInt("update_ymd10"));

				itemList.add(item);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		return itemList;
	}

}
