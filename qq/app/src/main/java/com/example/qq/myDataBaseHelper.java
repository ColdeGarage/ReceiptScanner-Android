package com.example.qq;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class myDataBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION =1;

    public myDataBaseHelper(Context context) {
        super(context, "d.db", null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table pay(id INTEGER PRIMARY KEY, item Text, name Text, amount int, date Text, month Text)");
        db.execSQL("create table scan(id INTEGER PRIMARY KEY, month Text, code Text,YMD Text,amount int)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS pay");
        db.execSQL("DROP TABLE IF EXISTS scan");
        onCreate(db);
    }
    public Cursor getAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query("pay",new String[]{"id","item","name","amount"},null,null,null,null,null);
    }
    public Cursor getRow(int i) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("pay",new String[]{"id","item","name","amount"},"id=?",new String[]{Integer.toString(i)},null,null,null);
        if (cursor!=null)
            cursor.moveToFirst();
        return cursor;
    }
    public Cursor getDateRow(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("pay", new String[]{"id","item","name","amount","month"},"date=?" ,new String[]{date},null,null,null);
        if (cursor!=null)
            cursor.moveToFirst();
        return cursor;
    }
    public Cursor getMonth(String month) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("pay", new String[]{"id","item","name","amount"},"month=?" ,new String[]{month},null,null,null);
        if (cursor!=null)
            cursor.moveToFirst();
        return cursor;
    }
    public Cursor getMonth_r(String month) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("scan", new String[]{"id", "month", "code"}, "month=?", new String[]{month}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor getAll_r() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("scan",new String[]{"YMD","code","amount"},null,null,null,null,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}