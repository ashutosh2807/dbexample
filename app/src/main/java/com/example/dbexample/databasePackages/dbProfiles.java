package com.example.dbexample.databasePackages;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.style.UpdateAppearance;
import android.util.Log;

import androidx.annotation.IntRange;

import com.example.dbexample.Data.profileData;

import java.util.ArrayList;
import java.util.List;

public class dbProfiles extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ProfileDB";
    private static final String TABLE_NAME = "User_info";
    private static final String KEY_ID = "Id";
    private static final String KEY_NAME = "Name";
    private static final String KEY_PHONE = "Phone";
    private static final String KEY_AGE = "Age";
    private static final String KEY_JOB = "Job";


    public dbProfiles(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_PHONE + " TEXT," + KEY_AGE +" INTEGER, "+ KEY_JOB+ " TEXT" +")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    public long addEmployee(profileData profile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, profile.getName()); // profiles Name
        contentValues.put(KEY_PHONE, profile.getPhone());
        contentValues.put(KEY_AGE, profile.getAge());
        contentValues.put(KEY_JOB, profile.getJobDesc());// profiles Age
        // Inserting Row
        long success = db.insert(TABLE_NAME, null, contentValues);
        // 2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
        return success;
    }

    public  boolean tuncateTable(){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DELETE FROM " + TABLE_NAME);
            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int updateData(profileData p) {
        ContentValues values = new ContentValues();
        values.put("Name", p.getName());
        values.put("Phone", p.getPhone());
        values.put("Age", p.getAge());
        values.put("Job", p.getJobDesc());

        SQLiteDatabase db = this.getWritableDatabase();

        int rowsAffected = db.update(TABLE_NAME, values, "Id = ?", new String[]{String.valueOf(p.getProfileID())});
        Log.d("affected", String.valueOf(rowsAffected));
        db.close();

        return rowsAffected;
    }


    public ArrayList<profileData> getAllEmployees() {
        ArrayList<profileData> employeeList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range")
                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                @SuppressLint("Range")
                String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                @SuppressLint("Range")
                int age = cursor.getInt(cursor.getColumnIndex(KEY_AGE));
                @SuppressLint("Range")
                String phone = cursor.getString(cursor.getColumnIndex(KEY_PHONE));
                @SuppressLint("Range")
                String jobDesc = cursor.getString(cursor.getColumnIndex(KEY_JOB));

                profileData profile = new profileData(id, name, phone, age, jobDesc);
                employeeList.add(profile);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return employeeList;
    }

    public profileData getEmployee(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{KEY_ID, KEY_NAME, KEY_AGE, KEY_PHONE, KEY_JOB},
                KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();

            @SuppressLint("Range")
            profileData profile = new profileData(
                    cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                    cursor.getString(cursor.getColumnIndex(KEY_NAME)),
                    cursor.getString(cursor.getColumnIndex(KEY_PHONE)),
                    cursor.getInt(cursor.getColumnIndex(KEY_AGE)),
                    cursor.getString(cursor.getColumnIndex(KEY_JOB))
            );

            cursor.close();
            return profile;
        }
        return null;
    }


}
