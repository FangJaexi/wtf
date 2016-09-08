package com.example.jaexi.mynews.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.jaexi.mynews.R;
import com.example.jaexi.mynews.common.HttpClientUtil;
import com.example.jaexi.mynews.common.LogUtil;
import com.example.jaexi.mynews.model.biz.parser.ParserNews;
import com.example.jaexi.mynews.model.dao.NewsDBManager;
import com.example.jaexi.mynews.model.entity.News;
import com.example.jaexi.mynews.ui.adapter.NewsAdapter;
import com.example.jaexi.mynews.ui.base.MyBaseActivity;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 核心版新闻客户端
 */
public class ActivityMain extends MyBaseActivity {
    // 每页显示 10行
    private int limit = 10;
    // 第几页
    private int offset;
    // 解析数据 json
    private ParserNews newsParser;
    private ListView listView;
    private NewsAdapter adapter;
    private NewsDBManager dbManager;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newslist);
        listView = (ListView) findViewById(R.id.listview);
        dbManager = NewsDBManager.getNewsDBManager(this);
        // 先加载缓存的新闻
        adapter = new NewsAdapter(this, listView);
        listView.setAdapter(adapter);
        // 滑动事件
        listView.setOnScrollListener(onScrollListener);
        // 单击事件跳转
        listView.setOnItemClickListener(onItemClickListener);

        if (dbManager.getCount() > 0) {
            // 数据库加载
            loadDataFromDB(limit, offset);
        } else {
            // 网络异步加载数据
            loadData();
        }
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                // 存在数据库中
                loadDataFromDB(limit, offset);

            } else if (msg.what == 200) {
                showToast("网络连接错误");

            }
            dialog.dismiss();
        }
    };

    /**
     * 数据库加载
     *
     * @param limit2  每页多少行
     * @param offset2 偏移多少行
     */

    private void loadDataFromDB(int limit2, int offset2) {
        // 第一次是第 0页 10行
        ArrayList<News> data = dbManager.queryNews(limit2, offset2);
        adapter.addendData(data, false);
        adapter.update();
        // 假如第一次是 0 10 第二次 10 10 第三次 20 10
        this.offset = offset + data.size();
    }

    /**
     * 异步加载数据
     */
    private void loadData() {
        dialog = ProgressDialog.show(this, null, "加载中，请稍候。。。");

        //启动新线程加载数据
        new Thread(new Runnable() {

            @Override
            public void run() {
                String path = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";
                try {
                    newsParser = new ParserNews(ActivityMain.this);
                    // 发送请求，得到返回数据
                    String httpResponse = HttpClientUtil.httpGet(path);
                    LogUtil.d("请求返回的数据", httpResponse);
                    // 将返回的数据解析
                    JSONObject object = new JSONObject(httpResponse);
                    if (object.getString("message").equals("OK")) {
                            ArrayList<News> parser = newsParser.parser(object);
                            for(int i = 0; i< parser.size();i++){
                                dbManager.insertNews(parser.get(i));
                            }
                        handler.sendEmptyMessage(100);
                    } else {
                        handler.sendEmptyMessage(200);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    // 出错
                    handler.sendEmptyMessage(200);
                }
            }
        }).start();
    }

    private AdapterView.OnItemClickListener onItemClickListener = new
            AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    LogUtil.d("点击子条目" + position);
                    News news = adapter.getAdapterData().get(position);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("news", news);
                    showToast("nidaye");
                    //openActivity(ActivityShow.class, bundle);
                }
            };

    /**
     * 滑动事件
     */
    private AbsListView.OnScrollListener onScrollListener = new AbsListView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        // 滑动时触发的方法
        // totalItemCount总数量
        // firstVisibleItem 第一行
        //
        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (totalItemCount > 20 && listView.getLastVisiblePosition() + 1 == totalItemCount) {
                loadDataFromDB(limit, offset);
            }
        }
    };
}
