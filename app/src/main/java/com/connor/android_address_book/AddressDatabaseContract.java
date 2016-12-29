package com.connor.android_address_book;

import android.provider.BaseColumns;

public final class AddressDatabaseContract {
    private AddressDatabaseContract() {}

    public static class Entry implements BaseColumns {
        public static String NAME;
        public static PhoneNumbers PHONES;
        public static Emails EMAILS;
        public static Address ADDRESS;

    }

    public static class PhoneNumbers { // update these to be types that only allow dash and integers?
        public static String PERSONAL;
        public static String WORK;
        public static String MISC;
    }

    public static class Emails  {
        public static String PERSONAL;
        public static String WORK;
        public static String MISC;
    }

    public static class Address {
        public static String LINE1;
        public static String LINE2;
        public static String CITYSTATE;
        public static int ZIPCODE;
    }
}
