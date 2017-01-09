package com.connor.android_address_book;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.res.Resources;
import android.util.Log;
import android.support.v7.widget.Toolbar.LayoutParams;

import static android.widget.RelativeLayout.BELOW;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(c.getString(1)); // make the title of the activity the current contact's name
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

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.scrollingLayout);
        Resources res = getResources();
        for (int i = 2; i < 9; ++i) { // this loop access each db column's
            int viewId = res.getIdentifier("_" + Integer.toString(i), "id", this.getPackageName()); // get the id of a TextView by its integer title
            if (c.getString(i) == null) { // if this column has no value, hide it
                TextView tv = (TextView) findViewById(viewId);
                tv.setVisibility(View.GONE);
            } else { // add a new TextView under the corresponding label with the label's data from the db
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT); // create params object
                params.addRule(BELOW, viewId); // put the following TextView below the current label
                params.setMargins(40, 0, 0, 0); // set left margin, needs updated
                TextView newView = new TextView(this);
                newView.setLayoutParams(params); // add layout to new TextView
                layout.addView(newView);
                newView.setId(i + 100);
                newView.setText(c.getString(i)); // set the TextView's text
            }
        }

    }
}
