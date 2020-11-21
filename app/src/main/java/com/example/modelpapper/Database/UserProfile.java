package com.example.modelpapper.Database;

import android.provider.BaseColumns;

public final class UserProfile {
    private UserProfile() {
    }

    public static final class Users implements BaseColumns{
        public static final String TABLE_NAME="UserInfo";
        public static final String COLUMN_NAME_UserName="userName";
        public static final String COLUMN_NAME_DOB="dateOfBirth";
        public static final String COLUMN_NAME_Gender="Gender";
        public static final String COLUMN_NAME_PASSWORD="Password";


    }
}
