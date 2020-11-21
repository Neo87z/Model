package com.example.modelpapper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="UsersNew";
    public DBHelper( Context context) {
        super(context, DATABASE_NAME,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Query=
                "CREATE TABLE " + UserProfile.Users.TABLE_NAME + " ("+
                        UserProfile.Users._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        UserProfile.Users.COLUMN_NAME_UserName + " TEXT," +
                        UserProfile.Users.COLUMN_NAME_DOB + " TEXT," +
                        UserProfile.Users.COLUMN_NAME_PASSWORD + " TEXT," +
                        UserProfile.Users.COLUMN_NAME_Gender + " TEXT)";
        db.execSQL(Query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addInfo(String Username,String Dob,String pass,String gender){
        SQLiteDatabase DB= getWritableDatabase();
        ContentValues CV= new ContentValues();
        CV.put(UserProfile.Users.COLUMN_NAME_UserName,Username);
        CV.put(UserProfile.Users.COLUMN_NAME_DOB,Dob);
        CV.put(UserProfile.Users.COLUMN_NAME_PASSWORD,pass);
        CV.put(UserProfile.Users.COLUMN_NAME_Gender,gender);
        long x= DB.insert(UserProfile.Users.TABLE_NAME,null,CV);
        return x;

    }

    public Boolean UpdateInfo(String Username,String Dob,String gender,String Password){
        SQLiteDatabase Db= getWritableDatabase();
        ContentValues CV= new ContentValues();
        CV.put(UserProfile.Users.COLUMN_NAME_DOB,Dob);
        CV.put(UserProfile.Users.COLUMN_NAME_Gender,gender);
        CV.put(UserProfile.Users.COLUMN_NAME_PASSWORD,Password);

        String seection= UserProfile.Users.COLUMN_NAME_UserName+" Like ?";
        String []args ={ Username };
        try{
            Db.update(
                    UserProfile.Users.TABLE_NAME,
                    CV,
                    seection,
                    args
            );
            return true;

        }catch (Exception e){
            return  false;
        }

    }
    public List readAllInfor(){
        SQLiteDatabase Db= getReadableDatabase();
        String [] Projection={
                UserProfile.Users._ID,
                UserProfile.Users.COLUMN_NAME_UserName,
                UserProfile.Users.COLUMN_NAME_DOB,
                UserProfile.Users.COLUMN_NAME_Gender
        };

        Cursor cursor=Db.query(
                UserProfile.Users.TABLE_NAME,
                Projection,
                null,
                null,
                null,
                null,
                null

        );

        List UserNames= new ArrayList();

        while (cursor.moveToNext()){
            UserNames.add(cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_UserName)));
        }
        cursor.close();
        return UserNames;

    }
    public List readAllInfor(String Name){
        SQLiteDatabase Db= getReadableDatabase();
        String [] Projection={
                UserProfile.Users._ID,
                UserProfile.Users.COLUMN_NAME_UserName,
                UserProfile.Users.COLUMN_NAME_PASSWORD,
                UserProfile.Users.COLUMN_NAME_DOB,
                UserProfile.Users.COLUMN_NAME_Gender
        };

        String seection= UserProfile.Users.COLUMN_NAME_UserName + " Like ?";
        String []args ={ Name };
        Cursor cursor=Db.query(
                UserProfile.Users.TABLE_NAME,
                Projection,
                seection,
                args,
                null,
                null,
                null

        );

        List Data = new ArrayList();
        try{
            while (cursor.moveToNext()){
                String Dateofbirth=cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_DOB));
                String password=cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_PASSWORD));
                String Gender=cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_NAME_Gender));
                Data.add(Dateofbirth);
                Data.add(password);
                Data.add(Gender);
            }
        }catch (Exception e){
            while (cursor.moveToNext()){
                String Dateofbirth=" ";
                String password=" ";
                String Gender=" ";
                Data.add(Dateofbirth);
                Data.add(password);
                Data.add(Gender);
            }
        }


        cursor.close();
        return Data;

    }

    public void deleteInfo(String ID){
        SQLiteDatabase Db= getWritableDatabase();
        String seection= UserProfile.Users.COLUMN_NAME_UserName+" Like ?";
        String []args ={ ID };
        Db.delete(UserProfile.Users.TABLE_NAME,seection,args);
    }
}
