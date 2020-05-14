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

    private ShareDatabaseHelper dbHelper;
    private String[] PERSON_TABLE_COLUMNS = { ShareDatabaseHelper.PERSON_COLUMN_ID,
            ShareDatabaseHelper.PERSON_COLUMN_NAME, ShareDatabaseHelper.PERSON_COLUMN_PHONE};

    private SQLiteDatabase database;

    public ShareDatabaseOperations(Context context) {
        dbHelper = new ShareDatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean insertContact(String name, String phone) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(ShareDatabaseHelper.PERSON_COLUMN_NAME, name);
        contentValues.put(ShareDatabaseHelper.PERSON_COLUMN_PHONE, phone);

        long studId = database.insert(ShareDatabaseHelper.PERSON_TABLE_NAME, null, contentValues);
        return true;
    }

    public int numberOfRows() {
        int numRows = (int) DatabaseUtils.queryNumEntries(
                database, ShareDatabaseHelper.PERSON_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact(Integer id, String name, String phone) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ShareDatabaseHelper.PERSON_COLUMN_NAME, name);
        contentValues.put(ShareDatabaseHelper.PERSON_COLUMN_PHONE, phone);
        database.update(ShareDatabaseHelper.PERSON_TABLE_NAME, contentValues,
                ShareDatabaseHelper.PERSON_COLUMN_ID + " = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact(Integer id) {
        return database.delete(ShareDatabaseHelper.PERSON_TABLE_NAME,
                ShareDatabaseHelper.PERSON_COLUMN_ID + " = ? ",
                new String[] { Integer.toString(id) });
    }

    public Cursor getContact(int id) {
        Cursor res =  database.rawQuery("SELECT * FROM " + ShareDatabaseHelper.PERSON_TABLE_NAME
                + " WHERE " + ShareDatabaseHelper.PERSON_COLUMN_ID + "=?",
                new String[]{Integer.toString(id)});
        return res;
    }

    public Cursor getAllContacts() {
        Cursor res =  database.rawQuery( "SELECT * FROM "
                + ShareDatabaseHelper.PERSON_TABLE_NAME, null );
        return res;
    }
}
