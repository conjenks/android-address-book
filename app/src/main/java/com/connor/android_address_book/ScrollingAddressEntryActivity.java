package com.connor.android_address_book;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class ScrollingAddressEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_address_entry);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("ID");

        AddressDatabaseHelper dbHelper = new AddressDatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // select the row with the _id of the intent we received
        Cursor c = db.query(AddressDatabaseContract.AddressTable.TABLE_NAME, null, "_id='" + id + "'", null, null, null, null);
        c.moveToFirst();
        final int Name = c.getColumnIndex(AddressDatabaseContract.AddressTable.NAME);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(c.getString(Name)); // make the title of the activity the current contact's name
        setSupportActionBar(toolbar);

        final Context context = this;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // make the edit button start the edit activity
                Intent intent = new Intent(context, EditAddressActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        // getDrawable is deprecated but line doesn't work when I remove it?
        Drawable d = getResources().getDrawable(android.R.drawable.ic_menu_edit); // change icon of fab
        fab.setImageDrawable(d);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        for (int i = 1; i < 11; ++i) {
            if (c.getString(i) != null) {

            }
        }

    }
}

//    final int Cell = c.getColumnIndex(AddressDatabaseContract.AddressTable.CELL);
//        final int Work = c.getColumnIndex(AddressDatabaseContract.AddressTable.WORK);
//        final int Home = c.getColumnIndex(AddressDatabaseContract.AddressTable.HOME);
//
//        final int Personal = c.getColumnIndex(AddressDatabaseContract.AddressTable.PERSONAL);
//        final int Employee = c.getColumnIndex(AddressDatabaseContract.AddressTable.EMPLOYEE);
//        final int Misc = c.getColumnIndex(AddressDatabaseContract.AddressTable.MISC);
//
//        final int Line1 = c.getColumnIndex(AddressDatabaseContract.AddressTable.LINE1);
//        final int Line2 = c.getColumnIndex(AddressDatabaseContract.AddressTable.LINE2);
//        final int CityState = c.getColumnIndex(AddressDatabaseContract.AddressTable.CITYSTATE);
//        final int ZipCode = c.getColumnIndex(AddressDatabaseContract.AddressTable.ZIPCODE);