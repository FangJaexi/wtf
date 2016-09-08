package com.example.jaexi.mynews.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jaexi.mynews.R;
import com.example.jaexi.mynews.common.CommonUtil;
import com.example.jaexi.mynews.common.LoadImage;
import com.example.jaexi.mynews.common.LogUtil;
import com.example.jaexi.mynews.model.entity.News;
import com.example.jaexi.mynews.ui.base.MyBaseAdapter;

/**
 * 新闻数据适配器
 *
 * 1.holdview 绑定控件三个控件赋值图片为默认
 */
public class NewsAdapter extends MyBaseAdapter<News> {

    /**
     * 加载图片之前默认图片
     */
    private Bitmap defaultBitmap;
    private ListView listview;
    //图片工具类
    private LoadImage loadImage;
    //回调的接口
    private LoadImage.ImageLoadListener listener=new
            LoadImage.ImageLoadListener() {
                @Override
                public void imageLoadOk(Bitmap bitmap, String url) {
                    //得到每个 listview的图片,通过异步加载显示图片
                    ImageView iv=(ImageView) listview.findViewWithTag(url);
                    LogUtil.d(url);
                    if(iv!=null){
                        //加载图片
                        iv.setImageBitmap(bitmap);
                    }
                }
            };
    public NewsAdapter(Context c, ListView lv) {
        super(c);
        defaultBitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.defaultpic);
        listview=lv;
        loadImage=new LoadImage(c, listener);
    }

    /***返回每一个子条目的视图*/
    @Override
    public View getMyView(int position, View  convertView, ViewGroup parent)
    {
        HoldView holdView=null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.item_list_news, null);
            holdView=new HoldView(convertView);
            convertView.setTag(holdView);
        }else{
            holdView=(HoldView) convertView.getTag();
        }
        News news = (News)list.get(position);
        holdView.tv_title.setText(news.getTitle());
        holdView.tv_text.setText(news.getSummary());
        holdView.iv_list_image.setImageBitmap(defaultBitmap);
        String uriImage = news.getIcon();
        String s = uriImage.replaceAll("localhost:8080", "192.168.2.134");
        holdView.iv_list_image.setTag(s);
        //给每个图片控件存入编号把图片的名字作为表示
        holdView.iv_list_image.setTag(CommonUtil.NETPATH);
        LogUtil.d(s);
        Bitmap bitmap = loadImage.getBitmap(s);
        if(bitmap != null) {
            holdView.iv_list_image.setImageBitmap(bitmap);
        }
        return convertView;
}
    /**标签类*/
    public class HoldView{
        public ImageView iv_list_image;
        public TextView tv_title;
        public TextView tv_text;


        public HoldView(View view){
            this.iv_list_image=(ImageView) view.findViewById(R.id.imageView1);
            tv_title = (TextView) view.findViewById(R.id.textView1);
            tv_text = (TextView) view.findViewById(R.id.textView2);
        }

    }
} 
