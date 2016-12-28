package com.connor.android_address_book;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
        mainListView = (ListView) findViewById(R.id.list_view);
        mainListView.setAdapter(mArrayAdapter);

        for (int i = 0; i < 30; i = i + 1) {
            mNameList.add(Integer.toString(i));
        }

        mArrayAdapter.notifyDataSetChanged();

        //---------------------------------------------------------------------------------------------
        // looking at SQLite databases
        // http://sarangasl.blogspot.com/2009/09/create-android-database.html
        SQLiteDatabase myDB = null;
        String TableName = "myTable";

        String Data = "";

        try {
            myDB = this.openOrCreateDatabase("DatabaseName", MODE_PRIVATE, null);
            myDB.execSQL("CREATE TABLE IF NOT EXISTS " + TableName + " (Field1, Field2)" + " VALUES ('Saranga', 22);");

            Cursor c = myDB.rawQuery("SELECT * FROM " + TableName, null);

            int Column1 = c.getColumnIndex("Field1");
            int Column2 = c.getColumnIndex("Field2");

            c.moveToFirst();
            if (c != null) {
                do {
                    String Name = c.getString(Column1);
                    int Age = c.getInt(Column2);
                    Data = Data + Name + "/" + Age + "\n";
                } while (c.moveToNext());
                }
            TextView tv = new TextView(this);
            tv.setText(Data);
            setContentView(tv);
        } catch(Exception e) {
            Log.e("Error", "Error", e);
        } finally {
            if (myDB != null)
                myDB.close();
        }
    }

    protected void onPause(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
