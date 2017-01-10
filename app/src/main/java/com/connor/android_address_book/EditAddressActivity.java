package com.connor.android_address_book;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.widget.RelativeLayout.BELOW;

public class EditAddressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("ID");

        AddressDatabaseHelper dbHelper = new AddressDatabaseHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        // select the row with the _id of the intent we received
        Cursor c = db.query(AddressDatabaseContract.AddressTable.TABLE_NAME, null, "_id='" + id + "'", null, null, null, null);
        c.moveToFirst();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(c.getString(1)); // make the title of the activity the current contact's name
        setSupportActionBar(toolbar);

        final Resources res = getResources();
        final String[] columnNames = c.getColumnNames();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) { // save the EditText changes to the database
               String statement = "UPDATE " + AddressDatabaseContract.AddressTable.TABLE_NAME + " SET "; // start constructing the SQL statement
               for (int i = 1; i < 12; ++i) {
                   int viewId = res.getIdentifier("editText" + Integer.toString(i), "id", "com.connor.android_address_book"); // get the id of an EditText by its integer title
                   EditText edit = (EditText) findViewById(viewId);
                   String text = edit.getText().toString();
                   if (text.trim().length() != 0) { // if the EditText field is NOT blank
                       statement += columnNames[i] + "='" + text + "'";
                       Log.d("DEBUG", Integer.toString(i));

                   }
               }

               statement += "WHERE _id='" + id + "';";
               db.execSQL(statement);
           }
       });

        // getDrawable is deprecated but line doesn't work when I remove it?
        Drawable d = getResources().getDrawable(android.R.drawable.ic_menu_save); // change icon of fab
        fab.setImageDrawable(d);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        for (int i = 1; i < 12; ++i) { // this loop accesses each db column's
            int viewId = res.getIdentifier("editText" + Integer.toString(i), "id", this.getPackageName()); // get the id of an EditText by its integer title
            if (c.getString(i) != null) { // if this column has no value, hide it
                EditText editText = (EditText) findViewById(viewId);
                editText.setText(c.getString(i), TextView.BufferType.EDITABLE);
            }
        }
    }
}
