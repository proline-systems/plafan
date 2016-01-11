package net.prolinesystems.plafan.data.amazon;

import java.util.ArrayList;
import java.util.List;

import net.prolinesystems.plafan.data.amazon.itemsearch.AmazonIDSearch;
import net.prolinesystems.plafan.data.amazon.itemsearch.Item;

public class AmazonMain {

	public static void main(String[] args) {

		List<String> categoryList = new ArrayList<>();
		categoryList.add("ガンダム プラモデル");

		AmazonIDSearch search = new AmazonIDSearch();
		// search.process(1000, 100, 1500, categoryList);

		AmazonItemLoader loader = new AmazonItemLoader();
		List<Item> itemList = loader.loadItem().subList(515, 517);

		System.out.println(itemList.size());

		try {
			search.searchPrice(itemList);
		} catch (Exception e) {
			try {
				search.searchPrice(itemList);
			} catch (Exception e1) {
				try {
					search.searchPrice(itemList);
				} catch (Exception e2) {
					try {
						search.searchPrice(itemList);
					} catch (Exception e3) {
						try {
							search.searchPrice(itemList);
						} catch (Exception e4) {
							throw new RuntimeException(e4);
						}
					}
				}
			}
		}

	}
}
