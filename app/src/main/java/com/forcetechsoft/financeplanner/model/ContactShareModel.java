package com.forcetechsoft.financeplanner.model;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import com.forcetechsoft.financeplanner.database.FinancePlannerDatabaseHelper;

/**
 * Created by egbomol on 3/25/2016.
 */
public class ContactShareModel extends GenericShareModel {


    public void fetchContacts(Context aContext) {

        Log.d(TAG, "LOFASZ - Fetching contacts started...");

        String phoneNumber = null;
        String email = null;

        /*Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
        Uri EmailCONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
        String DATA = ContactsContract.CommonDataKinds.Email.DATA;
        //StringBuffer output = new StringBuffer();
        ContentResolver contentResolver = aContext.getContentResolver();
        Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null, null);*/
        Cursor cursor = aContext.getContentResolver()
                .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        Log.d(TAG, "LOFASZ - cursor received from ContentResolver...");
        Log.d(TAG, "LOFASZ - cursor columns: " + cursor.getColumnCount());
        Log.d(TAG, "LOFASZ - cursor rows: " + cursor.getCount());

        cursor.moveToFirst(); // move to the first row of the cursor

        Log.d(TAG, "LOFASZ - starting iterating the cursor...");

       // Loop for every contact in the phone
        if (cursor.getCount() > 0) {

            Log.d(TAG,"LOFASZ - Beginning iteration on Cursor...");

            int cursorRows = cursor.getCount();

            for (int i=0; i<cursorRows; i++){
                //insertContactIntoShareDatabase(cursor);
                cursor.moveToNext();
            }

            /*insertContactIntoShareDatabase(cursor);

            while (cursor.moveToNext()) { // move the cursor to the next row
                insertContactIntoShareDatabase(cursor);

                /*int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));

                if (hasPhoneNumber > 0) {

                    dbOperations.insertContact(name, phoneNum);
                    Log.d(TAG,"LOFASZ - inserting contact into the database...");
                    //output.append("\n First Name:" + name);
                    // Query and loop for every phone number of the contact
                    /*Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[]{contact_id}, null);

                    while (phoneCursor.moveToNext()) {
                        phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                        output.append("\n Phone number:" + phoneNumber);
                    }

                    phoneCursor.close();
                }
            }*/
        }
    }

    @Override
    public void returnAllContactsInLogd(Context aContext) {

        String contact_id;
        String name;
        String phoneNum;

        Log.d(TAG, "LOFASZ - returnAllContacts is called");

        StringBuffer output = new StringBuffer();
        Cursor aCursor = dbOperations.getAllTemplateItems();
        Log.d(TAG, "LOFASZ - returnAllContacts Cursor is received...");

        int rowNum = aCursor.getCount();
        Log.d(TAG, "LOFASZ - returnAllContactsInLogd Cursor size: " + rowNum);
        aCursor.moveToFirst();

        for (int i=0; i < rowNum; i++) {

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
        }

        Log.d(TAG, "Share database: " + output.toString());
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
