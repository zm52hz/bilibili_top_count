package com.imeage.CrawTest.Bilibili;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.imeage.CrawTest.Bilibili.entity.Anime;
import com.imeage.CrawTest.Utils.Database.ConnectionUtil;

public class Top50Info {
	public void insertInto(String jsonMessage) {
		List<Anime> animeList = new ArrayList<Anime>();
		JSONObject jsonOb = JSON.parseObject(jsonMessage);
		JSONObject jsonResult = jsonOb.getJSONObject("result");
		JSONArray ainimeJsonList = jsonResult.getJSONArray("list");
		for (int i = 0 ; i < ainimeJsonList.size() ; i ++) {
			SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHH");
			
			Anime anime = new Anime();
			JSONObject animeJsonObject = ainimeJsonList.getJSONObject(i);
			JSONObject jsonStat = animeJsonObject.getJSONObject("stat");
			anime.setAinimename(animeJsonObject.getString("title"));
			anime.setDay(sdf.format(new Date()));
			anime.setPts(animeJsonObject.getInteger("pts"));
			anime.setUrl(animeJsonObject.getString("url"));
			anime.setFollwnum(jsonStat.getInteger("follow"));
			anime.setView(jsonStat.getIntValue("view"));
			anime.setDanmaku(jsonStat.getIntValue("danmaku"));
			animeList.add(anime);
		}
		inputDatabase(animeList);
	}
	
	
	public void inputDatabase(List<Anime> animeList) {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection("bilibilidata");
		} catch (ClassNotFoundException e) {
			System.out.println(String.format("classNotFoundException,error info :%s", e.getMessage()));
		} catch (SQLException e) {
			System.out.println(String.format("conn sql get error,error info :%s", e.getMessage()));
			return;
		}
		
		for (Anime anime : animeList) {
			String sql = String.format("replace into topfityinfo (animename,day,follwnum,pts,view,danmaku,url)"
					+ "values('%s','%s',%d,%d,%d,%d,'%s')"
					, anime.getAinimename(),anime.getDay(),anime.getFollwnum(),anime.getPts(),anime.getView(),
					anime.getDanmaku(),anime.getUrl());
			Statement stat = null;
			try {
				stat = conn.createStatement();
				stat.executeUpdate(sql);
			} catch (SQLException e) {
				System.out.println(String.format("insert sql error,sql:%s,error info:%s", sql,e.getMessage()));
			}
		}
		ConnectionUtil.close(conn);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd HH:mm:ss"); 
		
		System.out.println(sdf.format(new Date())+ " 番剧"+animeList.size() +"部 已入库！" );
	}
}
