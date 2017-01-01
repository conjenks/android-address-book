package com.connor.android_address_book;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddressDatabaseHelper dbHelper = new AddressDatabaseHelper(this);
        // get the database, if it does not exist this is where it will be created
        SQLiteDatabase db = dbHelper.getWritableDatabase();



    }

    protected void onPause(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
