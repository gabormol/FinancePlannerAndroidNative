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
    private static final int DATABASE_VERSION = 2;

    public static final String TEMPLATE_TABLE_NAME = "template";
    public static final String TEMPLATE_COLUMN_ID = "_id";
    public static final String TEMPLATE_COLUMN_EXPENSE_NAME = "expense_name";
    public static final String TEMPLATE_COLUMN_EXPENSE_PLANNED = "planned";
    public static final String TEMPLATE_COLUMN_EXPENSE_FREQUENCY = "frequency";
    public static final String TEMPLATE_COLUMN_EXPENSE_NEXT_MONTH = "next_month";
    public static final String TEMPLATE_COLUMN_EXPENSE_DUE_TO = "due_to";
    public static final String TIMESHEET_TABLE_NAME = "timesheet";
    public static final String TIMESHEET_COLUMN_ID = "_id";
    public static final String TIMESHEET_COLUMN_EXPENSE_NAME = "expense_name";
    public static final String TIMESHEET_COLUMN_EXPENSE_PLANNED = "planned";
    public static final String TIMESHEET_COLUMN_EXPENSE_PAID = "paid";

    public FinancePlannerDatabaseHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("LOFASZ", "LOFASZ - Create Database from Helper");
        db.execSQL("DROP TABLE IF EXISTS " + TEMPLATE_TABLE_NAME); // FOR TEST - remove it later!!!
        db.execSQL(
                "CREATE TABLE " + TEMPLATE_TABLE_NAME +
                        "(" + TEMPLATE_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        TEMPLATE_COLUMN_EXPENSE_NAME + " TEXT, " +
                        TEMPLATE_COLUMN_EXPENSE_PLANNED + " TEXT, " +
                        TEMPLATE_COLUMN_EXPENSE_FREQUENCY + " TEXT, " +
                        TEMPLATE_COLUMN_EXPENSE_NEXT_MONTH +" TEXT, " +
                        TEMPLATE_COLUMN_EXPENSE_DUE_TO +" TEXT)"
        );
        db.execSQL("DROP TABLE IF EXISTS " + TIMESHEET_TABLE_NAME); // FOR TEST - remove it later!!!
        db.execSQL(
                "CREATE TABLE " + TIMESHEET_TABLE_NAME +
                        "(" + TIMESHEET_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        TIMESHEET_COLUMN_EXPENSE_NAME + " TEXT, " +
                        TIMESHEET_COLUMN_EXPENSE_PLANNED + " TEXT, " +
                        TIMESHEET_COLUMN_EXPENSE_PAID +" TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TEMPLATE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TIMESHEET_TABLE_NAME);
        onCreate(db);
    }
}
