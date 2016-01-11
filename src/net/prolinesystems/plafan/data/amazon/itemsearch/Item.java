package net.prolinesystems.plafan.data.amazon.itemsearch;

import java.util.ArrayList;
import java.util.List;

public class Item {

	// public Item(String asin, List<ItemLink> itemlinks, String title){
	// this.asin = asin;
	// this.itemlinks = itemlinks;
	// this.title = title;
	// }

	public Item() {
		itemLinkList = new ArrayList<>();
	}

	private int id;
	private String asin;
	private List<ItemLink> itemLinkList;
	private String title;
	private String detailPageURL;
	private String category;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ItemLink> getItemLinkList() {
		return itemLinkList;
	}

	public void setItemLinkList(List<ItemLink> itemLinkList) {
		this.itemLinkList = itemLinkList;
	}

	public String getAsin() {
		return asin;
	}

	public void setAsin(String asin) {
		this.asin = asin;
	}

	public List<ItemLink> getItemlinkList() {
		return itemLinkList;
	}

	public void setItemlinkList(List<ItemLink> itemlinkList) {
		this.itemLinkList = itemlinkList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetailPageURL() {
		return detailPageURL;
	}

	public void setDetailPageURL(String detailPageURL) {
		this.detailPageURL = detailPageURL;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Item [asin=" + asin + ", title=" + title + ", detailPageURL=" + detailPageURL + ", category=" + category
				+ ", itemLinkList=" + itemLinkList + "]";
	}

}
