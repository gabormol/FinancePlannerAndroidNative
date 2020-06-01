package com.forcetechsoft.financeplanner.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by egbomol on 4/8/2016.
 */
public class FinancePlannerDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SQLiteFinancePlanner.db";
    private static final int DATABASE_VERSION = 11;

    public static final String TEMPLATE_TABLE_NAME = "template";
    public static final String TEMPLATE_COLUMN_ID = "_id";
    public static final String TEMPLATE_COLUMN_EXPENSE_ID = "mng_id";
    public static final String TEMPLATE_COLUMN_EXPENSE_NAME = "expense_name";
    public static final String TEMPLATE_COLUMN_EXPENSE_PLANNED = "planned";
    public static final String TEMPLATE_COLUMN_EXPENSE_FREQUENCY = "frequency";
    public static final String TEMPLATE_COLUMN_EXPENSE_NEXT_MONTH = "next_month";
    public static final String TEMPLATE_COLUMN_EXPENSE_DUE_TO = "due_to";
    public static final String TIMESHEET_TABLE_NAME = "timesheet";
    public static final String TIMESHEET_COLUMN_ID = "_id";
    public static final String TIMESHEET_COLUMN_TIMESHEET_ID = "mng_id_ts";
    public static final String TIMESHEET_COLUMN_EXPENSE_ID = "mng_id_tsitem";
    public static final String TIMESHEET_COLUMN_EXPENSE_NAME = "expense_name";
    public static final String TIMESHEET_COLUMN_EXPENSE_PLANNED = "planned";
    public static final String TIMESHEET_COLUMN_EXPENSE_PAID = "paid";
    public static final String USER_TABLE_NAME = "users";
    public static final String USER_COLUMN_ID = "_id";
    public static final String USER_COLUMN_USER_NAME = "username";
    public static final String USER_COLUMN_USER_TOKEN = "token";
    public static final String USER_COLUMN_ADMIN = "admin";
    public static final String USER_COLUMN_CURRENCY_DECIMALS = "curr_decimals";
    public static final String USER_COLUMN_CURRENCY_SYMBOL = "curr_symbol";
    public static final String USER_COLUMN_FIRST_NAME = "first_name";
    public static final String USER_COLUMN_LAST_NAME = "last_name";

    public FinancePlannerDatabaseHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("LOFASZ", "LOFASZ - Create Database from Helper");
        db.execSQL("DROP TABLE IF EXISTS " + TEMPLATE_TABLE_NAME); // FOR TEST - remove it later!!!
        db.execSQL(
                "CREATE TABLE " + TEMPLATE_TABLE_NAME +
                        "(" + TEMPLATE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        TEMPLATE_COLUMN_EXPENSE_ID + " TEXT, " +
                        TEMPLATE_COLUMN_EXPENSE_NAME + " TEXT, " +
                        TEMPLATE_COLUMN_EXPENSE_PLANNED + " INTEGER, " +
                        TEMPLATE_COLUMN_EXPENSE_FREQUENCY + " INTEGER, " +
                        TEMPLATE_COLUMN_EXPENSE_NEXT_MONTH +" INTEGER, " +
                        TEMPLATE_COLUMN_EXPENSE_DUE_TO +" INTEGER)"
        );
        db.execSQL("DROP TABLE IF EXISTS " + TIMESHEET_TABLE_NAME); // FOR TEST - remove it later!!!
        db.execSQL(
                "CREATE TABLE " + TIMESHEET_TABLE_NAME +
                        "(" + TIMESHEET_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        TIMESHEET_COLUMN_TIMESHEET_ID + " TEXT, " +
                        TIMESHEET_COLUMN_EXPENSE_ID + " TEXT, " +
                        TIMESHEET_COLUMN_EXPENSE_NAME + " TEXT, " +
                        TIMESHEET_COLUMN_EXPENSE_PLANNED + " INTEGER, " +
                        TIMESHEET_COLUMN_EXPENSE_PAID +" INTEGER)"
        );
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME); // FOR TEST - remove it later!!!
        db.execSQL(
                "CREATE TABLE " + USER_TABLE_NAME +
                        "(" + USER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        USER_COLUMN_USER_NAME + " TEXT, " +
                        USER_COLUMN_USER_TOKEN + " TEXT, " +
                        USER_COLUMN_ADMIN + " BOOLEAN, " +
                        USER_COLUMN_CURRENCY_DECIMALS + " TEXT, " +
                        USER_COLUMN_CURRENCY_SYMBOL + " TEXT, " +
                        USER_COLUMN_FIRST_NAME + " TEXT, " +
                        USER_COLUMN_LAST_NAME +" TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TEMPLATE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TIMESHEET_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(db);
    }
}
