package com.connor.android_address_book;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;

public class ContactsFragment extends ListFragment {

    private CursorAdapter mAdapter;
    private static final int layout = android.R.layout.simple_list_item_1;;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context context = getActivity();
        AddressDatabaseHelper dbHelper = new AddressDatabaseHelper(context);
        // get the database, if it does not exist this is where it will be created
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // create adapter once
        Cursor c = db.rawQuery("SELECT * FROM " + AddressDatabaseContract.AddressTable.TABLE_NAME, null);
        Log.d("DEBUG", DatabaseUtils.dumpCursorToString(c));
        int flags = 0; // no auto-requery!
        mAdapter = new SimpleCursorAdapter(context, layout, c, FROM, TO, flags);
        db.rawQuery(AddressDatabaseContract.AddressTable.DELETE_TABLE, null);
        Log.d("DEBUG", "Table dropped");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // each time we are started use our ListAdapter
        setListAdapter(mAdapter);
        // and tell loader manager to start loading
        // THIS LINE REMOVED
    }

    // and name should be displayed in the text1 TextView in item layout
    private static final String[] FROM = { AddressDatabaseContract.AddressTable.NAME };
    private static final int[] TO = { android.R.id.text1 };
}