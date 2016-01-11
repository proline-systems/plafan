package net.prolinesystems.plafan.data.amazon.itemsearch;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class AmazonItemOffersSax extends DefaultHandler {

	private List<Item> itemList;
	private Item tmpItem;
	private String currentQName;
	private ItemLink itemLink;
	private int totalResults = 0;

	public AmazonItemOffersSax() {
		itemList = new ArrayList<>();
	}

	public void clear() {
		tmpItem = null;
		currentQName = null;
		itemLink = null;
		itemList.clear();
	}
	
	/**
	 * 1.ドキュメント開始時
	 */
	public void startDocument() {
		System.out.println("ドキュメント開始");
	}

	/**
	 * 2.要素の開始タグ読み込み時
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) {

		
		System.out.println("要素開始:" + qName + ", ");
		if ("Item".equals(qName))
			tmpItem = new Item();

		currentQName = qName;
	}

	/**
	 * 4.テキストデータ読み込み時
	 */
	public void characters(char[] ch, int offset, int length) {

		String textData = new String(ch, offset, length);
		System.out.println("テキストデータ：" + textData);

	}

	/**
	 * 3.要素の終了タグ読み込み時
	 */
	public void endElement(String uri, String localName, String qName) {

		 System.out.println("要素終了:" + qName);
		if ("Item".equals(qName)) {
			itemList.add(tmpItem);
		}
	}

	/**
	 * 5.ドキュメント終了時
	 */
	public void endDocument() {
		System.out.println("ドキュメント終了");
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public int getTotalResults() {
		return totalResults;
	}
	
	
}