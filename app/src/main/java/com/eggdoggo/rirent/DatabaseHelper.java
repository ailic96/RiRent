package com.eggdoggo.rirent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.NonNull;
import java.util.ArrayList;

/* DatabaseHelper class contains default constructor, table creating method and
*  CRUD (Create, Read, Update, Delete) methods used in this app.
*/

public class DatabaseHelper extends SQLiteOpenHelper {

    /* Default constructor */
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    /* Creates table */
    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_TABLE);
    }

    /* Refresh database */
    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
    }

    /*Insert information method*/

    public long insertInfo(String rent, String ristan, String electricity, String internet,
                           String stairs, String total, String totalExpenses, String totalPerson,
                           String numPeople, String date, String addTimeStamp, String updateTimeStamp){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.C_RENT, rent);
        values.put(Constants.C_RISTAN, ristan);
        values.put(Constants.C_ELECTRICITY, electricity);
        values.put(Constants.C_INTERNET, internet);
        values.put(Constants.C_STAIRS, stairs);
        values.put(Constants.C_TOTAL, total);
        values.put(Constants.C_TOTAL_EXPENSES, totalExpenses);
        values.put(Constants.C_TOTAL_PERSON, totalPerson);
        values.put(Constants.C_NUM_PEOPLE, numPeople);
        values.put(Constants.C_DATE, date);
        values.put(Constants.C_ADD_TIMESTAMP, addTimeStamp);
        values.put(Constants.C_UPDATE_TIMESTAMP, updateTimeStamp);

        long id = db.insert(Constants.TABLE_NAME, null, values);

        db.close();
        return id;

    }

    /*Update information method*/

    public void updateInfo(String id, String rent, String ristan, String electricity, String internet,
                           String stairs, String total, String totalExpenses, String totalPerson,
                           String numPeople, String date, String addTimeStamp, String updateTimeStamp){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.C_ID, id);
        values.put(Constants.C_RENT, rent);
        values.put(Constants.C_RISTAN, ristan);
        values.put(Constants.C_ELECTRICITY, electricity);
        values.put(Constants.C_INTERNET, internet);
        values.put(Constants.C_STAIRS, stairs);
        values.put(Constants.C_TOTAL, total);
        values.put(Constants.C_TOTAL_EXPENSES, totalExpenses);
        values.put(Constants.C_TOTAL_PERSON, totalPerson);
        values.put(Constants.C_NUM_PEOPLE, numPeople);
        values.put(Constants.C_DATE, date);
        values.put(Constants.C_ADD_TIMESTAMP, addTimeStamp);
        values.put(Constants.C_UPDATE_TIMESTAMP, updateTimeStamp);

        db.update(Constants.TABLE_NAME, values, Constants.C_ID + " =? ", new String[]{id});
        db.close();
    }


    /*Delete  information method*/
    public void deleteInfo(String id){

        SQLiteDatabase db = getWritableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.C_ID + " =? ", new String[]{id});
        db.close();
    }

    /*Read and write information method*/
    @NonNull
    public ArrayList<Model> getAllData(String orderBy){

        ArrayList<Model> arrayList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME + " ORDER BY " + orderBy;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToNext()){
            do{
                Model model = new Model(
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_ID)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_RENT)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_RISTAN)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_ELECTRICITY)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_INTERNET)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_STAIRS)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_TOTAL)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_TOTAL_EXPENSES)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_TOTAL_PERSON)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_NUM_PEOPLE)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_DATE)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.C_ADD_TIMESTAMP)),
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_UPDATE_TIMESTAMP))
                );
                arrayList.add(model);
            }while(cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }

    /* Checks if unassigned value exists. If NULL value does exist, it sets a zero value. */

    public String checkIfNull(String value){
        if (value.length()==0) {
            value = "0";
            return "0";
        }
        else
            return value;
    }
}
