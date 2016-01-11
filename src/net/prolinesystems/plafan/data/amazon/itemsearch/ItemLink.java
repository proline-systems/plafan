package net.prolinesystems.plafan.data.amazon.itemsearch;

public class ItemLink {

//	public ItemLink(String description, String url) {
//		this.description = description;
//		this.url = url;
//	}
	
	private String description;
	private String url;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "ItemLink [description=" + description + ", url=" + url + "]";
	}

}
