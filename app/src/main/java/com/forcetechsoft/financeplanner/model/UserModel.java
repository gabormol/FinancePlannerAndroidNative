package com.forcetechsoft.financeplanner.model;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.forcetechsoft.financeplanner.database.FinancePlannerDatabaseHelper;
import com.forcetechsoft.financeplanner.model.services.CommunicationService;
import com.forcetechsoft.financeplanner.model.services.SimpleCallback;

/**
 * Created by egbomol on 3/25/2016.
 */
public class UserModel extends GenericFinancePlannerModel {

    CommunicationService communicationService = CommunicationService.INSTANCE;

    @Override
    public void logIn(final Context aContext) {

        Log.d(TAG, "LOFASZ: Starting login process...");


        communicationService.loginToBackend("lofasz", "lofasz", dbOperations,
                new SimpleCallback() {

                    @Override
                    public void onSuccess() {
                        Cursor aCursor = dbOperations.getUserItem("lofasz");
                        aCursor.moveToFirst();
                        Log.d(TAG, "LOFASZ: read from Database: " +
                                aCursor.getString(
                                        aCursor.getColumnIndex(FinancePlannerDatabaseHelper.USER_COLUMN_USER_NAME))
                                + " token: " + aCursor.getString(
                                aCursor.getColumnIndex(FinancePlannerDatabaseHelper.USER_COLUMN_USER_TOKEN)));
                        //myData(aContext); // TODO: Remove this chaining later!!!
                    }

                    @Override
                    public void onError() {
                        Log.d(TAG, "LOFASZ: LOGIN failed!!!");
                    }
                });
    }

    @Override
    public void logOut() {
        dbOperations.deleteUserItem(dbOperations.getUserName());
    }

    @Override
    public void myExpenses(Context aContext) {
        Log.d(TAG, "LOFASZ: Getting expenses...");
        String token = dbOperations.getUserToken(dbOperations.getUserName());
        communicationService.getMyExpenses(token, dbOperations,
                new SimpleCallback() {

                    @Override
                    public void onSuccess() {
                        Cursor aCursor = dbOperations.getAllTemplateItems();
                        aCursor.moveToFirst();
                        Log.d(TAG, "LOFASZ: read from Database: ");
                        String[] columnNames = aCursor.getColumnNames();
                        String cursorString = "";
                        for (String name: columnNames)
                            cursorString += String.format("%s ][ ", name);
                        do {
                            for (String name: columnNames) {
                                cursorString += String.format("%s ][ ",
                                        aCursor.getString(aCursor.getColumnIndex(name)));
                            }
                            cursorString += "\n";
                        } while (aCursor.moveToNext());
                        Log.d(TAG, "Database content: " + cursorString);
                        //Log.d(TAG, "LOFASZ: read from Database: " +
                        //        aCursor.getString(
                        //                aCursor.getColumnIndex(FinancePlannerDatabaseHelper.USER_COLUMN_USER_NAME))
                        //        + " token: " + aCursor.getString(
                        //        aCursor.getColumnIndex(FinancePlannerDatabaseHelper.USER_COLUMN_USER_TOKEN)));
                        //myData(aContext); // TODO: Remove this chaining later!!!
                    }

                    @Override
                    public void onError() {
                        Log.d(TAG, "LOFASZ: GET EXPENSES failed!!!");
                    }
                });
    }

    @Override
    public void myTimesheet(Context aContext) {
        Log.d(TAG, "LOFASZ: Getting timesheet...");
        String token = dbOperations.getUserToken(dbOperations.getUserName());
        communicationService.getMyTimesheet(token, dbOperations,
                new SimpleCallback() {

                    @Override
                    public void onSuccess() {
                        Cursor aCursor = dbOperations.getAllTimesheetItems();
                        aCursor.moveToFirst();
                        Log.d(TAG, "LOFASZ: read from Database: ");
                        String[] columnNames = aCursor.getColumnNames();
                        String cursorString = "";
                        for (String name: columnNames)
                            cursorString += String.format("%s ][ ", name);
                        do {
                            for (String name: columnNames) {
                                cursorString += String.format("%s ][ ",
                                        aCursor.getString(aCursor.getColumnIndex(name)));
                            }
                            cursorString += "\n";
                        } while (aCursor.moveToNext());
                        Log.d(TAG, "Database content: " + cursorString);
                        //myData(aContext); // TODO: Remove this chaining later!!!
                    }

                    @Override
                    public void onError() {
                        Log.d(TAG, "LOFASZ: GET EXPENSES failed!!!");
                    }
                });
    }

    @Override
    public void myStatictics(Context aContext) {
        Log.d(TAG, "LOFASZ: Getting statistics...");
        String token = dbOperations.getUserToken(dbOperations.getUserName());
        communicationService.getMyStatistics(token, dbOperations,
                new SimpleCallback() {

                    @Override
                    public void onSuccess() {
                        Log.d(TAG, "LOFASZ: GET STATISTICS success! ");
                    }

                    @Override
                    public void onError() {
                        Log.d(TAG, "LOFASZ: GET STATISTICS failed!!!");
                    }
                });
    }

    @Override
    public void myData(Context aContext) {
        String token = dbOperations.getUserToken(dbOperations.getUserName());
        //Log.d(TAG, "LOFASZ: User name: " + dbOperations.getUserName());
        communicationService.getMyData(token, dbOperations,
                new SimpleCallback() {

                    @Override
                    public void onSuccess() {
                        //Cursor aCursor = dbOperations.getUserItem("lofasz");
                        //aCursor.moveToFirst();
                        Log.d(TAG, "LOFASZ: myData returned");
                        //        aCursor.getString(
                        //                aCursor.getColumnIndex(FinancePlannerDatabaseHelper.USER_COLUMN_USER_NAME))
                        //        + " token: " + aCursor.getString(
                        //        aCursor.getColumnIndex(FinancePlannerDatabaseHelper.USER_COLUMN_USER_TOKEN)));
                    }

                    @Override
                    public void onError() {
                        Log.d(TAG, "LOFASZ: GET myData failed!!!");
                    }
                });

    }

    public void showResponse(String response) {

        Log.d(TAG, "RESPONSE: " + response);
    }

    @Override
    public boolean isUserLoggedIn(Context aContext) {
        return false;
    }

    @Override
    public void returnAllContactsInLogd(Context aContext) {

        /*String contact_id;
        String name;
        String phoneNum;

        Log.d(TAG, "LOFASZ - returnAllContacts is called");

        StringBuffer output = new StringBuffer();
        Cursor aCursor = dbOperations.getAllTemplateItems();
        Log.d(TAG, "LOFASZ - returnAllContacts Cursor is received...");

        int rowNum = aCursor.getCount();
        Log.d(TAG, "LOFASZ - returnAllContactsInLogd Cursor size: " + rowNum);
        aCursor.moveToFirst();

        for (int i = 0; i < rowNum; i++) {

            Log.d(TAG, "LOFASZ - cursor moved to next...");

            contact_id = aCursor.getString(
                    aCursor.getColumnIndex(FinancePlannerDatabaseHelper.TEMPLATE_COLUMN_ID));
            //Log.d(TAG,"LOFASZ - contact_id = " + contact_id);
            output.append("\n _ID:" + contact_id);
            name = aCursor.getString(
                    aCursor.getColumnIndex(FinancePlannerDatabaseHelper.TEMPLATE_COLUMN_EXPENSE_NAME));
            //Log.d(TAG,"LOFASZ - name = " + name);
            output.append("\n Name:" + name);
            phoneNum = aCursor.getString(
                    aCursor.getColumnIndex(FinancePlannerDatabaseHelper.TEMPLATE_COLUMN_EXPENSE_PLANNED));
            //Log.d(TAG,"LOFASZ - phoneNum = " + phoneNum);
            output.append("\n Phone Num:" + phoneNum);

            aCursor.moveToNext();

            /*dbOperations.insertContact(name, phoneNum);
            Log.d(TAG,"LOFASZ - inserting contact into the database...");
            phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
            output.append("\n Phone number:" + phoneNumber);*/
        /*}

        Log.d(TAG, "Share database: " + output.toString());*/
    }

    /*private void insertContactIntoShareDatabase(Cursor aCursor){

        int contactIdx = aCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID);
        int nameIdx = aCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int phoneNumberIdx = aCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        int photoIdIdx = aCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_ID);

        String contact_id = aCursor.getString(contactIdx);
        Log.d(TAG,"LOFASZ - contact_id = " + contact_id);
        String name = aCursor.getString(nameIdx);
        Log.d(TAG,"LOFASZ - name = " + name);
        String phoneNum = aCursor.getString(phoneNumberIdx);
        Log.d(TAG,"LOFASZ - phoneNum = " + phoneNum);

        dbOperations.insertTemplateItem(name, phoneNum);
        Log.d(TAG,"LOFASZ - inserting contact into the database...");
    }*/
}
