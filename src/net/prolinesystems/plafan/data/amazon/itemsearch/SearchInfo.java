package net.prolinesystems.plafan.data.amazon.itemsearch;

import java.util.Calendar;

public class SearchInfo {

	int id;
	int ymd10;
	Calendar searchDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getYmd10() {
		return ymd10;
	}
	public void setYmd10(int ymd10) {
		this.ymd10 = ymd10;
	}
	public Calendar getSearchDate() {
		return searchDate;
	}
	public void setSearchDate(Calendar searchDate) {
		this.searchDate = searchDate;
	}
	
	
}
