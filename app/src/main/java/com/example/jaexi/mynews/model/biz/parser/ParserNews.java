package com.example.jaexi.mynews.model.biz.parser;

import android.content.Context;

import com.example.jaexi.mynews.model.entity.News;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * json
 * 数据解析
 */
public class ParserNews {
    private Context context;
    public ParserNews(Context context){
        this.context=context;
    }
    //解析新闻数据
    public ArrayList<News> parser(JSONObject jsonObject) throws Exception{
        ArrayList<News> newsLit=new ArrayList<News>();
        //根据数据块名称获取一个数组
        JSONArray jsonArray=jsonObject.getJSONArray("data");
        //循环遍历这个数组
        for (int i = 0; i <jsonArray.length(); i++) {
            JSONObject object=jsonArray.getJSONObject(i);
            int nid = object.getInt("nid");
            String title = object.getString("title");
            String summary = object.getString("summary");
            String icon = object.getString("icon");
            String link = object.getString("link");
            int type = object.getInt("type");
            News news=new News(nid, title, summary, icon, link,type);
            newsLit.add(news);
        }
        return newsLit;
    }
 /*  *//**
     * 解析新闻数据，测试的是 parseNews()方法
     *//*
    public void testParserNews() {
        try {
            String path = "http://192.168.2.134:8080/newsClient/DoNewsStartList?size=10&typeId= 1 ";
            //发送请求，得到返回数据
            String json = HttpClientUtil.httpGet(path);
            LogUtil.d(LogUtil.TAG, "新闻请求数据返回：" + json);
            ParserNews parser = new ParserNews(context);
            List<News> mList = parser.parser(json);
            for (News news : mList) {
                Log.d("vivi", news.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


}