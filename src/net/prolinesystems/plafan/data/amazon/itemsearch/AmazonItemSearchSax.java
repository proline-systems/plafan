package net.prolinesystems.plafan.data.amazon.itemsearch;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class AmazonItemSearchSax extends DefaultHandler {

	private String category;
	
	private List<Item> itemList;
	private Item tmpItem;
	private String currentQName = null;
	private String currentQName2 = null;
	private String currentQName3 = null;
	private int totalResults = 0;

	public AmazonItemSearchSax(String category) {
		itemList = new ArrayList<>();
		this.category=category;
	}

	public void clear() {
		tmpItem = null;
		currentQName = null;
		itemList.clear();
	}

	/**
	 * 1.ドキュメント開始時
	 */
	public void startDocument() {
		// System.out.println("ドキュメント開始");
	}

	/**
	 * 2.要素の開始タグ読み込み時
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) {

//		System.out.println("要素開始:" + qName + ", ");
		if ("Item".equals(qName)){
			tmpItem = new Item();
			tmpItem.setCategory(category);
		}

		currentQName3 = currentQName2;
		currentQName2 = currentQName;
		currentQName = qName;
	}

	/**
	 * 4.テキストデータ読み込み時
	 */
	public void characters(char[] ch, int offset, int length) {

		String textData = new String(ch, offset, length);
//		System.out.println("テキストデータ：" + textData);
		if ("TotalResults".equals(currentQName))
			totalResults = Integer.parseInt(textData);

		if("Item".equals(currentQName)){
			tmpItem =  new Item();
			tmpItem.setCategory(category);
			
			}
		if ("ASIN".equals(currentQName))
			tmpItem.setAsin(textData);
		else if ("DetailPageURL".equals(currentQName))
			tmpItem.setDetailPageURL(textData);
		else if ("SalesRank".equals(currentQName))
			tmpItem.setSalesRank(Integer.parseInt(textData));
		else if ("URL".equals(currentQName) && "SmallImage".equals(currentQName2))
			tmpItem.setSmallImageURL(textData);
		else if ("URL".equals(currentQName) && "MediumImage".equals(currentQName2))
			tmpItem.setMediumImageURL(textData);
		else if ("URL".equals(currentQName) && "LargeImage".equals(currentQName2))
			tmpItem.setLargeImageURL(textData);
		else if ("EAN".equals(currentQName))
			tmpItem.setEan(textData);
		else if("Condition".equals(currentQName)&& "OfferAttributes".equals(currentQName2)&&"Offer".equals(currentQName3))
			tmpItem.setCondition(textData);
		else if("Amount".equals(currentQName) && "ListPrice".equals(currentQName2))
			tmpItem.setListPrice(Integer.parseInt(textData));
		else if("ReleaseDate".equals(currentQName))
			tmpItem.setReleaseDate(textData);
		else if ("Title".equals(currentQName))
			tmpItem.setTitle(textData);
		else if("Amount".equals(currentQName)&&"Price".equals(currentQName2)&& "OfferListingId".equals(currentQName3))
			tmpItem.setOfferPrice(Integer.parseInt(textData));
		else if("PercentageSaved".equals(currentQName))
			tmpItem.setPercentageSaved(Integer.parseInt(textData));
		else if("AvailabilityType".equals(currentQName))
			tmpItem.setAvailability(textData);
		
	}

	/**
	 * 3.要素の終了タグ読み込み時
	 */
	public void endElement(String uri, String localName, String qName) {

//		System.out.println("要素終了:" + qName);
		if ("Item".equals(qName)) {
			itemList.add(tmpItem);
		}
	}

	/**
	 * 5.ドキュメント終了時
	 */
	public void endDocument() {
		// System.out.println("ドキュメント終了");
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public int getTotalResults() {
		return totalResults;
	}

}