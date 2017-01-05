package com.connor.android_address_book;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class ContactsFragment extends ListFragment {

    private CursorAdapter mAdapter;
    private static final int layout = android.R.layout.simple_selectable_list_item;
    private static final String[] FROM = { AddressDatabaseContract.AddressTable.NAME };
    private static final int[] TO = { android.R.id.text1 };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context context = getActivity();
        AddressDatabaseHelper dbHelper = new AddressDatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + AddressDatabaseContract.AddressTable.TABLE_NAME +
                " ORDER BY " + AddressDatabaseContract.AddressTable.NAME, null);
        Log.d("DEBUG", DatabaseUtils.dumpCursorToString(c));
        int flags = 0; // no auto-requery!
        mAdapter = new SimpleCursorAdapter(context, layout, c, FROM, TO, flags);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(getActivity(), ScrollingAddressEntryActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // each time started use the ListAdapter
        setListAdapter(mAdapter);
    }
}