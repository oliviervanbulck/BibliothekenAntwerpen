package com.oliviervanbulck.bibliothekenantwerpen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by olivi on 30/09/2016.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "bibliotheken.db";
    private static final String TABLE_BIBS = "bibliotheken";
    private static final int DATABASE_VERSION = 2;

    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BIBS_TABLE = "CREATE TABLE " + TABLE_BIBS + "(_id INTEGER PRIMARY KEY, point_lat REAL, point_lng REAL, name TEXT)";
        db.execSQL(CREATE_BIBS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BIBS);
        onCreate(db);
    }

    public void addPoint(Double point_lat, Double point_lng, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("point_lat", point_lat);
        values.put("point_lng", point_lng);
        values.put("name", name);

        db.insert(TABLE_BIBS, null, values);
        db.close();
    }

    public int testInput() {
        SQLiteDatabase db = this.getReadableDatabase();
        //db.execSQL("SELECT COUNT(id) FROM " + TABLE_BIBS);
        Cursor cursor = db.rawQuery("SELECT COUNT(_id) FROM " + TABLE_BIBS, null);
        if(cursor!=null)
            cursor.moveToFirst();
        return cursor.getInt(0);

    }
}
