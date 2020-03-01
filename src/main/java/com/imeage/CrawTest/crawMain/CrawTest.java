package com.imeage.CrawTest.crawMain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.imeage.CrawTest.Bilibili.Top50Info;
import com.imeage.CrawTest.Utils.JSoupUtil;

public class CrawTest {
	public static void run() throws ClientProtocolException, IOException {
		//参数设置
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
//		formparams.add(new BasicNameValuePair("account", ""));  
//	    formparams.add(new BasicNameValuePair("password", ""));
	    HttpEntity reqEntity = new UrlEncodedFormEntity(formparams, "utf-8");  

	    RequestConfig requestConfig = RequestConfig.custom()  
	    .setConnectTimeout(5000)//一、连接超时：connectionTimeout-->指的是连接一个url的连接等待时间  
	            .setSocketTimeout(5000)// 二、读取数据超时：SocketTimeout-->指的是连接上一个url，获取response的返回等待时间  
	            .setConnectionRequestTimeout(5000)  
	            .build();  

	    HttpClient client = new DefaultHttpClient();  
	    
	    HttpGet get = new HttpGet("https://api.bilibili.com/pgc/web/rank/list?season_type=1&day=3");  
	    get.setConfig(requestConfig);  
	    get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36 QIHU 360SE");
	    get.setHeader("Accept", "application/json,text/plain,*/*");
	    HttpResponse response = client.execute(get);  

	    if (response.getStatusLine().getStatusCode() == 200) {  
	    	for (Header head : response.getAllHeaders()) {
	    		//System.out.println(head.getName() + ":" + head.getValue());
			}
	    	
	        HttpEntity resEntity = response.getEntity();  
	        String message = EntityUtils.toString(resEntity, "utf-8");  
	        new Top50Info().insertInto(message);
	        //JSoupUtil.parse(message);
	    } else {  
	        System.out.println(String.format("请求失败，errcode:%d", response.getStatusLine().getStatusCode()));  
	    }
	}
}
