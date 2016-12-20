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

        ArrayList mNameList = new ArrayList();
        ArrayAdapter mArrayAdapter;
        mArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                mNameList);

        ListView mainListView;
        mainListView = (ListView) findViewById(R.id.list);
        mainListView.setAdapter(mArrayAdapter);

        for (int i = 0; i < 30; i = i + 1) {
            mNameList.add(Integer.toString(i));
        }

        mArrayAdapter.notifyDataSetChanged();

    }

    protected void onPause(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
