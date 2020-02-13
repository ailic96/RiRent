package com.eggdoggo.rirent;

/*
* Constants class creates SQLite database and a table with values used
* in this app. When created, I was trying to figure out how Android works
* so I missed assigning the right data types. As you can see, all values are textual
* and it was a huge mistake, refactoring would take too long.
* */

public class Constants {

    //Database name
    public static final String DB_NAME = "RENT_DB.db";
    //Database version
    public static final int DB_VERSION = 1;
    //Database table
    public static final String TABLE_NAME = "RENT_TABLE";
    //Table columns
    public static final String C_ID = "ID";
    public static final String C_RENT= "RENT";
    public static final String C_RISTAN = "RISTAN";
    public static final String C_ELECTRICITY = "ELECTRICITY";
    public static final String C_INTERNET = "INTERNET";
    public static final String C_STAIRS = "STAIRS";
    public static final String C_DATE = "DATE";
    public static final String C_TOTAL = "TOTAL";
    public static final String C_TOTAL_EXPENSES = "TOTAL_EXPENSES";
    public static final String C_TOTAL_PERSON = "TOTAL_PERSON";
    public static final String C_NUM_PEOPLE= "NUM_PEOPLE";
    public static final String C_ADD_TIMESTAMP = "ADD_TIMESTAMP";
    public static final String C_UPDATE_TIMESTAMP = "UPDATE_TIMESTAMP";

    //Create query for table
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + C_RENT + " TEXT,"
            + C_RISTAN + " TEXT,"
            + C_ELECTRICITY + " TEXT,"
            + C_INTERNET + " TEXT,"
            + C_STAIRS + " TEXT,"
            + C_TOTAL + " TEXT,"
            + C_TOTAL_EXPENSES + " TEXT,"
            + C_TOTAL_PERSON + " TEXT,"
            + C_NUM_PEOPLE + " TEXT,"
            + C_DATE + " TEXT,"
            + C_ADD_TIMESTAMP + " TEXT,"
            + C_UPDATE_TIMESTAMP + " TEXT"
            + ");";
}
