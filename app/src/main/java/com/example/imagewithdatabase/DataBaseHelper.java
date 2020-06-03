package com.example.imagewithdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserImage.db";
    private static final String TABLE_NAME = "UserImages";

    private static final String COL1 = "ID";
    private static final String COL2 = "Image";



    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + "(" +
                COL1 + " INTEGER  PRIMARY key  AUTOINCREMENT," +
                COL2 + " BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

/**  notice that the type of method was changed from boolean to void ! */
    public void insertRow(byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2, image);
        db.insert(TABLE_NAME, null, cv);
    }


/**  notice that the return was changed from Cursor  to Without Cursor! */
    public Cursor getData() {
        SQLiteDatabase sql = this.getWritableDatabase();
        return sql.rawQuery(" SELECT * FROM " + TABLE_NAME, null);
    }


}
