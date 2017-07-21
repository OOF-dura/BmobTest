package com.example.frankdura.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by frankdura on 2017/7/17.
 */

public  class MyDatabaseHelper  extends SQLiteOpenHelper{
    public static final String CREATE_BOOK ="create table Book ("
            +"id integer primary key autoincrement,"
            +"author text,"
            +"price real,"
            +"pages integer,"
            +"name text)";
    public static final String CREATE_CATEGORY ="create table Category (" +
            "id integer primary key autoincrement," +
            "category_name text," +
            "category_code integer)";

    private Context mContext;
    public MyDatabaseHelper(Context context , String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mContext = context;
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
        //执行的数据库语句都是字符串
        Toast.makeText(mContext,"插入数据成功",Toast.LENGTH_LONG).show();
    }
    public void onUpgrade(SQLiteDatabase db,int oldVsersion,int newVersion){
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);
    }
}
