package com.connor.android_address_book;

import android.provider.BaseColumns;

public final class AddressDatabaseContract {
    private AddressDatabaseContract() {}

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Addresses.db";

    public static class AddressTable implements BaseColumns { // column names
        public static final String TABLE_NAME = "addresses";
        public static final String NAME = "name";
        // phone numbers
        public static final String CELL = "cellPhone"; // turn these into types that can only integers and dashes
        public static final String WORK = "workPhone";
        public static final String HOME = "homePhone";
        // emails
        public static final String PERSONAL = "personalEmail";
        public static final String EMPLOYEE = "workEmail";
        public static final String MISC = "miscEmail";
        // address
        public static final String LINE1 = "line1";
        public static final String LINE2 = "line2";
        public static final String CITYSTATE = "cityState";
        public static final String ZIPCODE = "zipCode";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + " (" + _ID + " INTEGER PRIMARY KEY," + NAME + " varchar(30), " + CELL + " varchar(15), "
                + WORK + " varchar(15), " + HOME + " varchar(15), " + PERSONAL + " varchar(50), "
                + EMPLOYEE + " varchar(50), " + MISC + " varchar(50), " + LINE1 + " varchar(30), "
                + LINE2 + " varchar(30), " + CITYSTATE + " varchar(30), " + ZIPCODE + " int);";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}







