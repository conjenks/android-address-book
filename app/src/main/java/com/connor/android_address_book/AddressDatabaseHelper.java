package com.connor.android_address_book;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Connor on 12/31/2016.
 */

public class AddressDatabaseHelper extends SQLiteOpenHelper {
    public AddressDatabaseHelper(Context context) {
        super(context, AddressDatabaseContract.DATABASE_NAME, null, AddressDatabaseContract.DATABASE_VERSION);
    }

    // method called during creation of database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AddressDatabaseContract.AddressTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(AddressDatabaseContract.AddressTable.DELETE_TABLE);
        onCreate(db);
    }
}
