package com.inkostilaton.dbpractice;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public static final String CUSTOMER = "CUSTOMER";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_POSTAL = "POSTAL";
    public static final String COLUMN_STATE = "STATE";
    public static final String COLUMN_CITY = "CITY";
    public static final String COLUMN_ADDRESS = "ADDRESS";
    public static final String COLUMN_CUST_ID = "CUST_ID";

    public Database(@Nullable Context context) {
        super(context, "base.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + CUSTOMER + " (" + COLUMN_CUST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_POSTAL + " INT, " + COLUMN_STATE + " TEXT, " + COLUMN_CITY + " TEXT, " + COLUMN_ADDRESS + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addCustomer(CustomModel customModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, customModel.getName());
        cv.put(COLUMN_POSTAL, customModel.getPostal());
        cv.put(COLUMN_STATE, customModel.getState());
        cv.put(COLUMN_CITY, customModel.getCity());
        cv.put(COLUMN_ADDRESS, customModel.getAddress());

        long insert = db.insert(CUSTOMER,null, cv);
        if (insert == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

}
