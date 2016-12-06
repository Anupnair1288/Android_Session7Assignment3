package com.android.project.sqlite_employee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.TextView;


public class DBhelper extends SQLiteOpenHelper {
    SQLiteDatabase sqLiteDatabase;

    public static final String DB_NAME = "EMPLOYEE_DETAILS";
    public static final int DB_VERSION = 1;


    public static final String TABLE_NAME = "EMPLOYEE_LIST";
    public static final String COL_ID = "ID";
    public static final String COL_NAME = "NAME";
    public static final String COL_AGE = "AGE";
    public static final String COL_IMAGE = "IMAGE";

    public DBhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String table_emp = " CREATE TABLE " + TABLE_NAME + " ( " + COL_ID + " INTEGER PRIMARY KEY," + COL_NAME + " TEXT," + COL_AGE + " INTEGER," + COL_IMAGE + " BLOB )";
        sqLiteDatabase.execSQL(table_emp);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(" DROP TABLE IF EXITS" + TABLE_NAME);

        onCreate(sqLiteDatabase);


    }


    public void insertData(String name, int age, byte[] image) {

        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_AGE, age);
        values.put(COL_IMAGE, image);

        sqLiteDatabase.insert(TABLE_NAME, null, values);


        sqLiteDatabase.close();


    }


    public void getData(TextView name, TextView age, ImageView image) {

        sqLiteDatabase = this.getReadableDatabase();

        String select_all = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(select_all, null);

        if (cursor.moveToFirst()) {

            do {

                int id = cursor.getInt(0);
                String col_name = cursor.getString(1);
                int col_age = cursor.getInt(2);
                byte[] col_img = cursor.getBlob(3);


                System.out.println("Data============>>>" + id + "  " + col_name + "  " + col_age + "   " + col_img);

                name.setText(col_name);
                age.setText(String.valueOf(col_age));

                Bitmap bmp = BitmapFactory.decodeByteArray(col_img, 0, col_img.length);
                image.setImageBitmap(bmp);


            } while (cursor.moveToNext());


        }


    }
}
