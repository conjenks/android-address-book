package com.connor.android_address_book;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends ListActivity {

    // request code
    static final int PICK_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // necessary activity stuff
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // calls contact picker
        Intent contactsIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(contactsIntent, PICK_CONTACT);

        //------------------------------------------------------------
        // this is the stuff that actually shows in the app currently
        ArrayList mNameList = new ArrayList();
        ArrayAdapter mArrayAdapter;
        mArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mNameList);

        ListView mainListView;
        mainListView = (ListView) findViewById(R.id.list_view);
        mainListView.setAdapter(mArrayAdapter);

        for (int i = 0; i < 30; i = i + 1) {
            mNameList.add(Integer.toString(i));
        }

        mArrayAdapter.notifyDataSetChanged();
        //------------------------------------------------------------

    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (PICK_CONTACT) :
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c = getContentResolver().query(contactData, null, null, null, null);
                    if (c.moveToFirst()) {
                        String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                        c.close();
                    }
                    break;
                }
        }
    }

    protected void onPause(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}


/*
        // looking at SQLite databases
        // http://sarangasl.blogspot.com/2009/09/create-android-database.html
        SQLiteDatabase myDB = null;
        String TableName = "People";

        String Data = "";

        try {
        myDB = this.openOrCreateDatabase("Book", MODE_PRIVATE, null);
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
        mNameList.add(Data.toString());
        c.close();
        } catch(Exception e) {
        Log.e("Error", "Error", e);
        } finally {
        if (myDB != null)
        myDB.close();
        }
*/
