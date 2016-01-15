package net.prolinesystems.plafan.data.amazon.itemsearch;

public class Item {

	// public Item(String asin, List<ItemLink> itemlinks, String title){
	// this.asin = asin;
	// this.itemlinks = itemlinks;
	// this.title = title;
	// }

	public Item() {
	}

	private int id;
	private String category;
	private String asin;
	private String title;
	private int salesRank;
	private String smallImageURL;
	private String mediumImageURL;
	private String largeImageURL;
	private String ean;
	private int listPrice;// ListPrice, Amount
	private String releaseDate;
	private String condition;// OfferAttributes,要素開始:Condition,テキストデータ：New
	private int offerPrice; // OfferListing,Price,Amount,
	private int percentageSaved;// PercentageSaved,
	private String availability;// Availability
	private String detailPageURL;
	private int createYMD10;
	private int updateYMD10;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAsin() {
		return asin;
	}

	public void setAsin(String asin) {
		this.asin = asin;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getSalesRank() {
		return salesRank;
	}

	public void setSalesRank(int salesRank) {
		this.salesRank = salesRank;
	}

	public String getSmallImageURL() {
		return smallImageURL;
	}

	public void setSmallImageURL(String smallImageURL) {
		this.smallImageURL = smallImageURL;
	}

	public String getMediumImageURL() {
		return mediumImageURL;
	}

	public void setMediumImageURL(String mediumImageURL) {
		this.mediumImageURL = mediumImageURL;
	}

	public String getLargeImageURL() {
		return largeImageURL;
	}

	public void setLargeImageURL(String largeImageURL) {
		this.largeImageURL = largeImageURL;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public int getListPrice() {
		return listPrice;
	}

	public void setListPrice(int listPrice) {
		this.listPrice = listPrice;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public int getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(int offerPrice) {
		this.offerPrice = offerPrice;
	}

	public int getPercentageSaved() {
		return percentageSaved;
	}

	public void setPercentageSaved(int percentageSaved) {
		this.percentageSaved = percentageSaved;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getDetailPageURL() {
		return detailPageURL;
	}

	public void setDetailPageURL(String detailPageURL) {
		this.detailPageURL = detailPageURL;
	}


	public int getCreateYMD10() {
		return createYMD10;
	}

	public void setCreateYMD10(int createYMD10) {
		this.createYMD10 = createYMD10;
	}

	public int getUpdateYMD10() {
		return updateYMD10;
	}

	public void setUpdateYMD10(int updateYMD10) {
		this.updateYMD10 = updateYMD10;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", category=" + category + ", asin=" + asin + ", title=" + title + ", salesRank="
				+ salesRank + ", smallImageURL=" + smallImageURL + ", mediumImageURL=" + mediumImageURL
				+ ", largeImageURL=" + largeImageURL + ", ean=" + ean + ", listPrice=" + listPrice + ", releaseDate="
				+ releaseDate + ", condition=" + condition + ", offerPrice=" + offerPrice + ", percentageSaved="
				+ percentageSaved + ", availability=" + availability + ", detailPageURL=" + detailPageURL
				+ ", createYMD10=" + createYMD10 + ", updateYMD10=" + updateYMD10 + "]";
	}


}
