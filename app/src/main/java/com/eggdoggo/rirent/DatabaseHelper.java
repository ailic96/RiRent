package com.eggdoggo.rirent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
    }

    //Insert info function

    public long insertInfo(String rent, String ristan, String electricity,
                           String internet, String stairs, String date,
                           String addTimeStamp, String updateTimeStamp){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.C_RENT, rent);
        values.put(Constants.C_RISTAN, ristan);
        values.put(Constants.C_ELECTRICITY, electricity);
        values.put(Constants.C_INTERNET, internet);
        values.put(Constants.C_STAIRS, stairs);
        values.put(Constants.C_DATE, date);
        values.put(Constants.C_ADD_TIMESTAMP, addTimeStamp);
        values.put(Constants.C_UPDATE_TIMESTAMP, updateTimeStamp);

        long id = db.insert(Constants.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public ArrayList<Model> getAllData(String orderBy){

        ArrayList<Model> arrayList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME + " ORDER BY " + orderBy;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToNext()){
            do{
                Model model = new Model(
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_ID)),
                        "Stanarina :"+cursor.getString(cursor.getColumnIndex(Constants.C_RENT)),
                        "Ri Stan: "+cursor.getString(cursor.getColumnIndex(Constants.C_RISTAN)),
                        "Struja: "+cursor.getString(cursor.getColumnIndex(Constants.C_ELECTRICITY)),
                        "Internet: "+cursor.getString(cursor.getColumnIndex(Constants.C_INTERNET)),
                        "Stubište: "+cursor.getString(cursor.getColumnIndex(Constants.C_STAIRS)),
                        "Mjesec: "+cursor.getString(cursor.getColumnIndex(Constants.C_DATE)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_ADD_TIMESTAMP)),
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_UPDATE_TIMESTAMP))
                );
                arrayList.add(model);
            }while(cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }

}
