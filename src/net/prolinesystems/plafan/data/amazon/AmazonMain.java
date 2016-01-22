package net.prolinesystems.plafan.data.amazon;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import net.prolinesystems.plafan.data.amazon.itemsearch.AmazonIDSearch;
import net.prolinesystems.plafan.data.amazon.itemsearch.Item;
import net.prolinesystems.plafan.data.amazon.itemsearch.ItemDao;
import net.prolinesystems.plafan.data.amazon.itemsearch.SearchInfo;
import net.prolinesystems.plafan.data.amazon.itemsearch.SearchInfoDao;

public class AmazonMain {

	public static void main(String[] args) {

		String strYMD = getCurrentYMD();

		List<String> categoryList = new ArrayList<>();
		categoryList.add("ガンダム プラモデル");
		categoryList.add("タミヤ プラモデル");
		categoryList.add("青島 プラモデル");

		AmazonIDSearch search = new AmazonIDSearch();
		List<Item> newItemList = search.getInfo(100, 11000, 100, strYMD, categoryList);

		SearchInfoDao dao = new SearchInfoDao();
		SearchInfo searchInfo = dao.getLast();
		Integer lastYmd10 = searchInfo.getYmd10();
		int ymd10 = getCurrentYmd10(lastYmd10);
		System.out.println("ymd:"+ymd10);

		ItemDao itemDao = new ItemDao();
		List<Item> itemList = itemDao.load();

		SearchInfo newInfo = new SearchInfo();
		newInfo.setYmd10(ymd10);
		newInfo.setSearchDate(new GregorianCalendar());
		dao.save(newInfo);
		for (Item newItem : newItemList) {
			newItem.setCreateYMD10(ymd10);
			newItem.setUpdateYMD10(ymd10);

			Item existItem = getItemByEAN(newItem.getEan(), itemList);
			if (existItem == null) {
				itemDao.saveItem(newItem);
			} else {
				if (newItem.getListPrice() == existItem.getListPrice()) {
					existItem.setUpdateYMD10(newItem.getCreateYMD10());
					itemDao.saveItem(existItem);
				} else {
					itemDao.saveItem(newItem);
				}
			}
		}

//		search.saveItemList(newItemList, strYmd);
		//
		// List<Item> itemList = loader.loadItemByPercentageSaved(40);
		//
		// for (Item item : itemList) {
		// System.out.println(item);
		// }

		// AmazonItemLoader loader = new AmazonItemLoader();
		// List<Item> itemList = loader.loadItem().subList(515, 517);
		//
		// System.out.println(itemList.size());
		//
		// try {
		// search.searchPrice(itemList);
		// } catch (Exception e) {
		// try {
		// search.searchPrice(itemList);
		// } catch (Exception e1) {
		// try {
		// search.searchPrice(itemList);
		// } catch (Exception e2) {
		// try {
		// search.searchPrice(itemList);
		// } catch (Exception e3) {
		// try {
		// search.searchPrice(itemList);
		// } catch (Exception e4) {
		// throw new RuntimeException(e4);
		// }
		// }
		// }
		// }
		// }

	}

	private static Item getItemByEAN(String ean, List<Item> itemList) {

		for (Item item : itemList)
			if (ean.equals(item.getEan()))
				return item;

		return null;
	}

	private static int getCurrentYmd10(Integer lastYmd10) {

		if (lastYmd10 == null) {
			return Integer.parseInt(getCurrentYMD() + "01");
		} else {
			String lastYmd = "" + (lastYmd10 / 100);
			int seq = lastYmd10 % 100000000;
			String currentYmd = getCurrentYMD();
			if (lastYmd.equals(currentYmd)) {
				seq++;
				String strSeq = "";
				if (seq < 10)
					strSeq += "0";
				strSeq += seq;
				return Integer.parseInt(currentYmd + strSeq);
			} else {
				String strYmd10 = "" + getCurrentYMD() + "01";
				return Integer.parseInt(strYmd10);
			}
		}
	}

	private static String getCurrentYMD() {

		Calendar cal = new GregorianCalendar();
		int iY = cal.get(Calendar.YEAR);
		int iM = cal.get(Calendar.MONTH) + 1;
		int iD = cal.get(Calendar.DAY_OF_MONTH);

		String strY = "" + iY;
		String strM = "";
		if (iM < 10)
			strM += "0";
		strM += iM;
		String strD = "";
		if (iD < 10)
			strD += "0";
		strD += iD;

		return strY + strM + strD;

	}
}
