package com.imeage.CrawTest.Bilibili.entity;

import lombok.Data;

@Data
public class Anime {
	private String ainimename;//动漫名称
	private String day;//时间
	private int follwnum;//关注数量
	private int pts;//总体评分
	private int view;//观看人数
	private int danmaku;//猜测弹幕数量？
	private String url;//地址
	
}
