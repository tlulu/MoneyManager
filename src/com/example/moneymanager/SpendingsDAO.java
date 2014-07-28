package com.example.moneymanager;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SpendingsDAO{

  // Database fields
  private SQLiteDatabase database;
  private MySQLiteHelper dbHelper;
  private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
      MySQLiteHelper.COLUMN_PRICE,MySQLiteHelper.COLUMN_CATEGORY,MySQLiteHelper.COLUMN_TIME };

  public SpendingsDAO(Context context) {
    dbHelper = new MySQLiteHelper(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }

  public Spending createSpending(double price,String category,String time) {
    ContentValues values = new ContentValues();
    values.put(MySQLiteHelper.COLUMN_PRICE, price);
    values.put(MySQLiteHelper.COLUMN_TIME, time);
    values.put(MySQLiteHelper.COLUMN_CATEGORY, category);
    long insertId = database.insert(MySQLiteHelper.TABLE_SPENDINGS, null,
        values);
    Cursor cursor = database.query(MySQLiteHelper.TABLE_SPENDINGS,
        allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
        null, null, null);
    cursor.moveToFirst();
    Spending newSpending = cursorToSpending(cursor);
    cursor.close();
    return newSpending;
  }

  public void deleteSpending(Spending spending) {
    int id = spending.getID();
    System.out.println("Comment deleted with id: " + id);
    database.delete(MySQLiteHelper.TABLE_SPENDINGS, MySQLiteHelper.COLUMN_ID
        + " = " + id, null);
  }

  public List<Spending> getAllSpendings() {
    List<Spending> spendings = new ArrayList<Spending>();

    Cursor cursor = database.query(MySQLiteHelper.TABLE_SPENDINGS,
        allColumns, null, null, null, null, null);

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      Spending spending = cursorToSpending(cursor);
      spendings.add(spending);
      cursor.moveToNext();
    }
    // make sure to close the cursor
    cursor.close();
    return spendings;
  }

  private Spending cursorToSpending(Cursor cursor) {
    Spending spending = new Spending();
    spending.setID(cursor.getInt(0));
    spending.setPrice(cursor.getDouble(1));
    spending.setCategory(cursor.getString(2));
    spending.setTime(cursor.getString(3));
    return spending;
  }
} 
