package net.prolinesystems.plafan.data.amazon.itemsearch;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class AmazonIDSearch {

	public List<Item> getInfo(int initialPrice, int maxPrice, int tick, String ymd, List<String> categoryList) {

		List<Item> itemList = new ArrayList<>();

		int totalResults = 0;
		int totalPages = 0;
		for (String category : categoryList) {
			for (int price = initialPrice; price <= maxPrice; price += tick) {
				try {
					AmazonItemSearchSax sax = new AmazonItemSearchSax(category);
					try {
						totalResults = getNumberOfItem(sax, price, tick, category);
					} catch (Exception e) {
						try {
							totalResults = getNumberOfItem(sax, price, tick, category);
						} catch (Exception e1) {
							try {
								totalResults = getNumberOfItem(sax, price, tick, category);
							} catch (Exception e2) {
								try {
									totalResults = getNumberOfItem(sax, price, tick, category);
								} catch (Exception e3) {
									try {
										totalResults = getNumberOfItem(sax, price, tick, category);
									} catch (Exception e4) {
										throw new RuntimeException(e4);
									}
								}
							}
						}
					}
					if (totalResults > 100)
						throw new RuntimeException("TotalResults > 100");

					if (totalResults == 0)
						totalPages = 0;
					else
						totalPages = totalResults / 10 + 1;
					sax.clear();
					System.out
							.println("category:" + category + " price:" + price + " TotalPages:" + totalPages + " TotalResults:" + totalResults);
					for (int i = 1; i <= totalPages; i++) {
						try {
							search(sax, i, price, category);
						} catch (Exception e) {
							try {
								search(sax, i, price, category);
							} catch (Exception e2) {
								try {
									search(sax, i, price, category);
								} catch (Exception e3) {
									try {
										search(sax, i, price, category);
									} catch (Exception e4) {
										throw new RuntimeException(e4);
									}
								}
							}
						}
						List<Item> tmpItemList = sax.getItemList();
						itemList.addAll(tmpItemList);
						sax.clear();
						System.out.print("Page:" + i + ":" + price + ":" + itemList.size() + "    ");
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			System.out.println("Total Item Lists:" + itemList.size());
		}
		// for (Item item : itemList) {
		// System.out.println(item);
		// }
		
		return itemList;
	}

	private static int getNumberOfItem(AmazonItemSearchSax sax, int price, int tick, String category) {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
		}

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			Map<String, String> keyMap = new HashMap<String, String>();
			keyMap.put("Service", "AWSECommerceService");
			keyMap.put("AWSAccessKeyId", "AKIAIOQBYSAXCCXSV4ZQ");
			keyMap.put("Version", "2011-08-01");
			keyMap.put("Operation", "ItemSearch");
			keyMap.put("SearchIndex", "Hobbies");
			keyMap.put("MerchantId", "Amazon");
			keyMap.put("Condition", "New");
			keyMap.put("Sort", "-release-date");
			keyMap.put("Keywords", category);
			keyMap.put("AssociateTag", "prolinesystem-22");

			keyMap.put("MinimumPrice", "" + price);
			keyMap.put("MaximumPrice", "" + (price + tick));
			keyMap.put("ItemPage", "" + 1);
			SignedRequestsHelper signedRequestsHelper = new SignedRequestsHelper();
			String urlStr = signedRequestsHelper.sign(keyMap);
			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

			while ((line = br.readLine()) != null) {
				sb.append(line);

				// sb.append("\r\n"); // ラインセパレータは決めうち
				try {
					// SAXパーサーファクトリを生成
					SAXParserFactory spfactory = SAXParserFactory.newInstance();
					// SAXパーサーを生成
					SAXParser parser = spfactory.newSAXParser();
					// XMLファイルを指定されたデフォルトハンドラーで処理します
					InputStream bais = new ByteArrayInputStream(line.getBytes("utf-8"));
					parser.parse(bais, sax);
					bais.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// // 結果を出力
			// System.out.println(sb.toString());

		} catch (Exception e) {
			sax.clear();
			throw new RuntimeException(e);
		}
		return sax.getTotalResults();
	}

	private static void search(AmazonItemSearchSax sax, int i, int price, String category) {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
		}

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			Map<String, String> keyMap = new HashMap<String, String>();
			keyMap.put("Service", "AWSECommerceService");
			keyMap.put("AWSAccessKeyId", "AKIAIOQBYSAXCCXSV4ZQ");
			keyMap.put("Version", "2011-08-01");
			keyMap.put("Operation", "ItemSearch");
			keyMap.put("SearchIndex", "Hobbies");
			keyMap.put("MerchantId", "Amazon");
			keyMap.put("Condition", "New");
			keyMap.put("Sort", "-release-date");
			keyMap.put("Keywords", category);
			keyMap.put("AssociateTag", "prolinesystem-22");

			keyMap.put("MinimumPrice", "" + price);
			keyMap.put("MaximumPrice", "" + (price + 100));
			keyMap.put("ItemPage", "" + i);
			keyMap.put("ResponseGroup", "Large");
			SignedRequestsHelper signedRequestsHelper = new SignedRequestsHelper();
			String urlStr = signedRequestsHelper.sign(keyMap);
			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

			while ((line = br.readLine()) != null) {
				sb.append(line);

				// sb.append("\r\n"); // ラインセパレータは決めうち
				try {
					// SAXパーサーファクトリを生成
					SAXParserFactory spfactory = SAXParserFactory.newInstance();
					// SAXパーサーを生成
					SAXParser parser = spfactory.newSAXParser();
					// XMLファイルを指定されたデフォルトハンドラーで処理します
					InputStream bais = new ByteArrayInputStream(line.getBytes("utf-8"));
					parser.parse(bais, sax);
					bais.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// // 結果を出力
			// System.out.println(sb.toString());

		} catch (Exception e) {
			sax.clear();
			throw new RuntimeException(e);
		}
	}

	public void searchPrice(List<Item> itemList) {

		for (Item item : itemList) {
			AmazonItemOffersSax sax = new AmazonItemOffersSax();
			try {
				getOffers(sax, item.getAsin());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sax.clear();
			}
		}
	}

	private static void getOffers(AmazonItemOffersSax sax, String asin) {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
		}

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			Map<String, String> keyMap = new HashMap<String, String>();
			keyMap.put("Service", "AWSECommerceService");
			keyMap.put("AWSAccessKeyId", "AKIAIOQBYSAXCCXSV4ZQ");
			keyMap.put("Version", "2011-08-01");
			keyMap.put("Operation", "ItemSearch");
			keyMap.put("SearchIndex", "Hobbies");
			keyMap.put("MerchantId", "Amazon");
			keyMap.put("Condition", "New");
			keyMap.put("Sort", "-release-date");
			keyMap.put("Keywords", asin);
			keyMap.put("AssociateTag", "prolinesystem-22");
			keyMap.put("ResponseGroup", "Large");

			SignedRequestsHelper signedRequestsHelper = new SignedRequestsHelper();
			String urlStr = signedRequestsHelper.sign(keyMap);
			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

			while ((line = br.readLine()) != null) {
				sb.append(line);

				try {
					// SAXパーサーファクトリを生成
					SAXParserFactory spfactory = SAXParserFactory.newInstance();
					// SAXパーサーを生成
					SAXParser parser = spfactory.newSAXParser();
					// XMLファイルを指定されたデフォルトハンドラーで処理します
					InputStream bais = new ByteArrayInputStream(line.getBytes("utf-8"));
					parser.parse(bais, sax);
					bais.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// // 結果を出力
			// System.out.println(sb.toString());

		} catch (Exception e) {
			sax.clear();
			throw new RuntimeException(e);
		}
	}

}
