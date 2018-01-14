package com.example.geeta.madpractise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geeta on 07-Jan-18.
 */

public class helper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "AcademicDB";
    private static final String TABLE_USERS = "users";

    public helper(Context context) {


        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_TABLE = "CREATE TABLE " + TABLE_USERS + "("+
                "name varchar(100) NULL,"+
                "email varchar(100) NULL"+
                ")";
        db.execSQL(CREATE_TABLE);
        Log.d("TableCreated", "Table Created");

    }

    public void addStudent( String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        Log.d("Values Get", "Get the values sucessfully");
                    // Inserting Row

        db.insert(TABLE_USERS, null, values);
        Log.d("Inserting Row", "values inserted sucessfully");
        db.close(); // Closing database connection
            }

    public List<User> getAllUsers() {
        List<User> allUser = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User(cursor.getString(0), cursor.getString(1));
                allUser.add(user);
                } while (cursor.moveToNext());
            }
               return allUser;
         }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Academics");
        this.onCreate(db);
    }
}
