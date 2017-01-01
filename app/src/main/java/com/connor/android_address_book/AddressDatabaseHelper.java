package com.connor.android_address_book;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.content.CursorLoader;

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

        String[] PROJECTION = {
                ContactsContract.Contacts._ID, // _ID is always required
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY // that's what we want to display
        };
        Uri contentUri = ContactsContract.Contacts.CONTENT_URI;
        CursorLoader contactCursor = new CursorLoader(context, contentUri, PROJECTION, null, null, "display_name");
        insertCursorIntoDB(contactCursor.loadInBackground(), db);
    }

    public void insertCursorIntoDB(Cursor cursor, SQLiteDatabase db) {
        if (cursor != null) {
            while(cursor.moveToNext()) {
                ContentValues values = new ContentValues();
                values.put(NAME, cursor.getString(3));
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
