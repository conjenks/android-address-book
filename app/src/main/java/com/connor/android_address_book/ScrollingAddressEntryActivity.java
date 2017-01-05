package com.connor.android_address_book;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ScrollingAddressEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_address_entry);

        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");

        AddressDatabaseHelper dbHelper = new AddressDatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + AddressDatabaseContract.AddressTable.TABLE_NAME + " WHERE _id=" + id, null);
        c.moveToFirst();
        int indexName = c.getColumnIndex(AddressDatabaseContract.AddressTable.NAME);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(c.getString(indexName));
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Drawable d = getResources().getDrawable(android.R.drawable.ic_menu_edit); // change icon of fab
        fab.setImageDrawable(d);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
