package net.prolinesystems.plafan.data.amazon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.prolinesystems.plafan.data.amazon.itemsearch.Item;

public class AmazonItemLoader {

	public List<Item> loadItem() {

		List<Item> itemList = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:plafan.db");

			String sql = "SELECT id, asin, title, DetailPageURL, category FROM ITEM order by id";
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Item item = new Item();
				item.setId(rs.getInt("id"));
				item.setAsin(rs.getString("asin"));
				item.setTitle(rs.getString("title"));
				item.setDetailPageURL(rs.getString("DetailPageURL"));
				item.setCategory(rs.getString("category"));
				itemList.add(item);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		return itemList;
	}

}
