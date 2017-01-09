package com.connor.android_address_book;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditAddressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("ID");

        AddressDatabaseHelper dbHelper = new AddressDatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // select the row with the _id of the intent we received
        Cursor c = db.query(AddressDatabaseContract.AddressTable.TABLE_NAME, null, "_id='" + id + "'", null, null, null, null);
        c.moveToFirst();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(c.getString(1)); // make the title of the activity the current contact's name
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // getDrawable is deprecated but line doesn't work when I remove it?
        Drawable d = getResources().getDrawable(android.R.drawable.ic_menu_save); // change icon of fab
        fab.setImageDrawable(d);

        EditText editText = (EditText) findViewById(R.id.editText1);
        editText.setText(c.getString(1), TextView.BufferType.EDITABLE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
