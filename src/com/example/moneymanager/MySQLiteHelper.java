package com.example.moneymanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

  public static final String TABLE_SPENDINGS = "spendings";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_PRICE = "price";
  public static final String COLUMN_CATEGORY = "category";
  public static final String COLUMN_TIME="created_at";

  private static final String DATABASE_NAME = "Spendings.db";
  private static final int DATABASE_VERSION = 2;

  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table "
      + TABLE_SPENDINGS + "(" + COLUMN_ID
      + " integer primary key autoincrement, " + COLUMN_PRICE
      + " text not null, "
      +  COLUMN_CATEGORY
      + " text not null, "+ COLUMN_TIME
      + " text not null);";

  public MySQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(MySQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPENDINGS);
    onCreate(db);
  }

} 
