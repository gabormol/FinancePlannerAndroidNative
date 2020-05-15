package com.forcetechsoft.financeplanner.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by egbomol on 4/12/2016.
 */
public class ShareDatabaseOperations {

    private FinancePlannerDatabaseHelper dbHelper;
    private String[] TEMPLATE_TABLE_COLUMNS = {FinancePlannerDatabaseHelper.TEMPLATE_COLUMN_ID,
            FinancePlannerDatabaseHelper.TEMPLATE_COLUMN_EXPENSE_NAME, FinancePlannerDatabaseHelper.TEMPLATE_COLUMN_EXPENSE_PLANNED,
            FinancePlannerDatabaseHelper.TEMPLATE_COLUMN_EXPENSE_FREQUENCY, FinancePlannerDatabaseHelper.TEMPLATE_COLUMN_EXPENSE_NEXT_MONTH,
            FinancePlannerDatabaseHelper.TEMPLATE_COLUMN_EXPENSE_DUE_TO};

    private String[] TIMESHEET_TABLE_COLUMNS = {FinancePlannerDatabaseHelper.TIMESHEET_COLUMN_ID,
            FinancePlannerDatabaseHelper.TIMESHEET_COLUMN_EXPENSE_NAME, FinancePlannerDatabaseHelper.TIMESHEET_COLUMN_EXPENSE_PLANNED,
            FinancePlannerDatabaseHelper.TIMESHEET_COLUMN_EXPENSE_PAID};

    private SQLiteDatabase database;

    public ShareDatabaseOperations(Context context) {
        dbHelper = new FinancePlannerDatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertTemplateItem(String expenseName, String expensePlanned,
                                      String expenseFrequency, String expenseNextMonth,
                                      String expenseDueTo) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(FinancePlannerDatabaseHelper.TEMPLATE_COLUMN_EXPENSE_NAME, expenseName);
        contentValues.put(FinancePlannerDatabaseHelper.TEMPLATE_COLUMN_EXPENSE_PLANNED, expensePlanned);
        contentValues.put(FinancePlannerDatabaseHelper.TEMPLATE_COLUMN_EXPENSE_FREQUENCY, expenseFrequency);
        contentValues.put(FinancePlannerDatabaseHelper.TEMPLATE_COLUMN_EXPENSE_NEXT_MONTH, expenseNextMonth);
        contentValues.put(FinancePlannerDatabaseHelper.TEMPLATE_COLUMN_EXPENSE_DUE_TO, expenseDueTo);

        long studId = database.insert(FinancePlannerDatabaseHelper.TEMPLATE_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertTimesheetItem(String expenseName, String expensePlanned,
                                       String expensePaid) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(FinancePlannerDatabaseHelper.TIMESHEET_COLUMN_EXPENSE_NAME, expenseName);
        contentValues.put(FinancePlannerDatabaseHelper.TIMESHEET_COLUMN_EXPENSE_PLANNED, expensePlanned);
        contentValues.put(FinancePlannerDatabaseHelper.TIMESHEET_COLUMN_EXPENSE_PAID, expensePaid);

        long studId = database.insert(FinancePlannerDatabaseHelper.TIMESHEET_TABLE_NAME, null, contentValues);
        return true;
    }

    public int numberOfTemplateRows() {
        int numRows = (int) DatabaseUtils.queryNumEntries(
                database, FinancePlannerDatabaseHelper.TEMPLATE_TABLE_NAME);
        return numRows;
    }

    public int numberOfTimesheetRows() {
        int numRows = (int) DatabaseUtils.queryNumEntries(
                database, FinancePlannerDatabaseHelper.TIMESHEET_TABLE_NAME);
        return numRows;
    }

    public boolean updateTemplateItem(Integer id, String expenseName, String expensePlanned,
                                      String expenseFrequency, String expenseNextMonth,
                                      String expenseDueTo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FinancePlannerDatabaseHelper.TEMPLATE_COLUMN_EXPENSE_NAME, expenseName);
        contentValues.put(FinancePlannerDatabaseHelper.TEMPLATE_COLUMN_EXPENSE_PLANNED, expensePlanned);
        contentValues.put(FinancePlannerDatabaseHelper.TEMPLATE_COLUMN_EXPENSE_FREQUENCY, expenseFrequency);
        contentValues.put(FinancePlannerDatabaseHelper.TEMPLATE_COLUMN_EXPENSE_NEXT_MONTH, expenseNextMonth);
        contentValues.put(FinancePlannerDatabaseHelper.TEMPLATE_COLUMN_EXPENSE_DUE_TO, expenseDueTo);

        database.update(FinancePlannerDatabaseHelper.TEMPLATE_TABLE_NAME, contentValues,
                FinancePlannerDatabaseHelper.TEMPLATE_COLUMN_ID + " = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public boolean updateTimesheetItem(Integer id, String expenseName, String expensePlanned,
                                       String expensePaid) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(FinancePlannerDatabaseHelper.TIMESHEET_COLUMN_EXPENSE_NAME, expenseName);
        contentValues.put(FinancePlannerDatabaseHelper.TIMESHEET_COLUMN_EXPENSE_PLANNED, expensePlanned);
        contentValues.put(FinancePlannerDatabaseHelper.TIMESHEET_COLUMN_EXPENSE_PAID, expensePaid);

        database.update(FinancePlannerDatabaseHelper.TIMESHEET_TABLE_NAME, contentValues,
                FinancePlannerDatabaseHelper.TIMESHEET_COLUMN_ID + " = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteTemplateItem(Integer id) {
        return database.delete(FinancePlannerDatabaseHelper.TEMPLATE_TABLE_NAME,
                FinancePlannerDatabaseHelper.TEMPLATE_COLUMN_ID + " = ? ",
                new String[]{Integer.toString(id)});
    }

    public Integer deleteTimesheetItem(Integer id) {
        return database.delete(FinancePlannerDatabaseHelper.TIMESHEET_TABLE_NAME,
                FinancePlannerDatabaseHelper.TIMESHEET_COLUMN_ID + " = ? ",
                new String[]{Integer.toString(id)});
    }

    public Cursor getTemplateItem(int id) {
        Cursor res = database.rawQuery("SELECT * FROM " + FinancePlannerDatabaseHelper.TEMPLATE_TABLE_NAME
                        + " WHERE " + FinancePlannerDatabaseHelper.TEMPLATE_COLUMN_ID + "=?",
                new String[]{Integer.toString(id)});
        return res;
    }

    public Cursor getTimesheetItem(int id) {
        Cursor res = database.rawQuery("SELECT * FROM " + FinancePlannerDatabaseHelper.TIMESHEET_TABLE_NAME
                        + " WHERE " + FinancePlannerDatabaseHelper.TIMESHEET_COLUMN_ID + "=?",
                new String[]{Integer.toString(id)});
        return res;
    }

    public Cursor getAllTemplateItems() {
        Cursor res = database.rawQuery("SELECT * FROM "
                + FinancePlannerDatabaseHelper.TEMPLATE_TABLE_NAME, null);
        return res;
    }

    public Cursor getAllTimesheetItems() {
        Cursor res = database.rawQuery("SELECT * FROM "
                + FinancePlannerDatabaseHelper.TIMESHEET_TABLE_NAME, null);
        return res;
    }
}
