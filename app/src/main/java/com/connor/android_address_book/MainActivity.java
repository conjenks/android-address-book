package com.connor.android_address_book;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView mainListView; // ListView object
        ArrayAdapter mArrayAdapter; // ArrayAdapter object
        ArrayList mNameList = new ArrayList();

        // Access the ListView
        mainListView = (ListView) findViewById(R.id.list);

        // Create an ArrayAdapter for the ListView
        mArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                mNameList);

        // Set the ListView to use the ArrayAdapter
        mainListView.setAdapter(mArrayAdapter);

        mNameList.add("list item");
        mArrayAdapter.notifyDataSetChanged();

    }

    protected void onPause(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
