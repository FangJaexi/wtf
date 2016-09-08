package com.example.jaexi.mynews.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/9/1 0001.
 */
public class DBOpenHelper  extends SQLiteOpenHelper {

    public DBOpenHelper(Context context) {
        super(context, "newsdb.db", null, 1);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table news (_id integer primary key autoincrement,nid integer,title text,summary text,icon text,link text,type integer)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }

}