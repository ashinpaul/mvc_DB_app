package com.example.mvc_db_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class CDB extends SQLiteOpenHelper {
    private static final int DATABSE_VERSION=1;
    private static final String DATABASE_NAME="DMS";
    public CDB(Context context){
        super(context,DATABASE_NAME,null,DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table TBDept (dno integer primary key autoincrement,dname text ,dloc text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TbDept");
        onCreate(db);
    }
    public void addDept(CDept d){
        try {
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues cv=new ContentValues();
            cv.put("dname",d.dname);
            cv.put("dloc",d.dloc);
            db.insert("TbDept",null,cv);
            db.close();
        }catch (Exception e){}
    }
    public List<CDept> getAllvalues(){
        List<CDept> recList=new ArrayList<CDept>();
        String selectQuery ="SELECT *FROM TbDept";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do {
                CDept rec=new CDept();
                rec.id=Integer.parseInt(cursor.getString(0));
                rec.dname=cursor.getString(1);
                rec.dloc=cursor.getString(2);
                recList.add(rec);
            }while (cursor.moveToNext());
        }
        return recList;
    }
}
