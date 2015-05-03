package com.lxy.lotteryspider;

import java.text.DecimalFormat;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.lxy.hibernate.Lottery;
import com.lxy.store.DataStore;

/**
 * this class will grab the information of each lottery
 * run this method you will grab the latest information of the lottery
 * @author sl169
 *
 */

public class Spider {

	public static String getHtmlByUrl(String url) {
		String html = null;
		HttpClient httpClient = new DefaultHttpClient();// 创建httpClient对象
		HttpGet httpget = new HttpGet(url);// 以get方式请求该URL
		try {
			HttpResponse responce = httpClient.execute(httpget);// 得到responce对象
			int resStatu = responce.getStatusLine().getStatusCode();// 返回码
			if (resStatu == HttpStatus.SC_OK) {// 200正常 其他就不对
				// 获得相应实体
				HttpEntity entity = responce.getEntity();
				if (entity != null) {
					html = EntityUtils.toString(entity);// 获得html源代码
				}
			}
		} catch (Exception e) {
			System.out.println("访问【" + url + "】出现异常!");
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return html;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		DataStore dataStore = new DataStore();
		Lottery lottery = new Lottery();

		String html = getHtmlByUrl("http://baidu.lecai.com/lottery/draw/list/ssq.php");
		if (html != null && !"".equals(html)) {
			Document doc = Jsoup.parse(html);
			Elements trs = doc.select("table").first().select("tbody").select("tr");
			Elements tds = trs.get(4).select("td");

			String phase = tds.get(0).text();
			lottery.setPhase(phase);
			
			String date = tds.get(1).text();
			date = date.substring(0, 10);
			lottery.setDate(date);
			
			Elements tds2 = tds.get(2).select("em");
			int[] a = new int[tds2.size()];
			for (int i = 0; i < tds2.size(); i++) {
				
				String text = tds2.get(i).text();
				a[i] = Integer.parseInt(text);
			}
			
			lottery.setRed_1(a[0]);
			lottery.setRed_2(a[1]);
			lottery.setRed_3(a[2]);
			lottery.setRed_4(a[3]);
			lottery.setRed_5(a[4]);
			lottery.setRed_6(a[5]);
			lottery.setBlue(a[6]);
			if(a.length == 8){
				lottery.setLucky_blue(a[a.length-1]);
			}
			dataStore.storeData(lottery);

		}
	}

}
