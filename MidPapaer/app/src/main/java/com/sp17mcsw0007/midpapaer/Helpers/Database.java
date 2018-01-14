package com.sp17mcsw0007.midpapaer.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sp17mcsw0007.midpapaer.Models.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geeta on 14-Jan-18.
 */

public class Database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Contacts";
    private static final String TABLE_USERS = "contacts";

    public Database(Context context) {


        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_TABLE = "CREATE TABLE " + TABLE_USERS + "("+
                "name varchar(100) NULL,"+
                "email varchar(100) NULL"+
                ")";
        db.execSQL(CREATE_TABLE);

    }

    public void addContact( String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        // Inserting Row

        db.insert(TABLE_USERS, null, values);
        db.close(); // Closing database connection
    }

    public List<Contact> getAllContacts() {
        List<Contact> allUser = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact user = new Contact(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
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
