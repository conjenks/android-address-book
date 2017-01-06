package com.connor.android_address_book;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.content.CursorLoader;
import android.util.Log;

import static com.connor.android_address_book.AddressDatabaseContract.AddressTable.CELL;
import static com.connor.android_address_book.AddressDatabaseContract.AddressTable.NAME;

public class AddressDatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    public AddressDatabaseHelper(Context context) {
        super(context, AddressDatabaseContract.DATABASE_NAME, null, AddressDatabaseContract.DATABASE_VERSION);
        this.context = context;
    }

    // method called during creation of database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AddressDatabaseContract.AddressTable.CREATE_TABLE);

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = new String[] {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor people = context.getContentResolver().query(uri, projection, null, null, null);
        insertCursorIntoDB(people, db);
    }

    public void insertCursorIntoDB(Cursor cursor, SQLiteDatabase db) {
        int indexName = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int indexNumber = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        if (cursor != null) {
            while(cursor.moveToNext()) {
                ContentValues values = new ContentValues();
                values.put(NAME, cursor.getString(indexName));
                values.put(CELL, cursor.getString(indexNumber));
                db.insertOrThrow("addresses", null, values);
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(AddressDatabaseContract.AddressTable.DELETE_TABLE);
        onCreate(db);
    }
}
