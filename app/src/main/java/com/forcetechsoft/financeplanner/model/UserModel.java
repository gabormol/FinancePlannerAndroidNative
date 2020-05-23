package com.forcetechsoft.financeplanner.model;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import com.forcetechsoft.financeplanner.database.FinancePlannerDatabaseHelper;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by egbomol on 3/25/2016.
 */
public class UserModel extends GenericFinancePlannerModel {

    private Retrofit retrofit;

    public void logIn(Context aContext) {

        Log.d(TAG, "LOFASZ: Starting login process...");

        new Thread(new Runnable() {

            @Override
            public void run() {
                retrofit = ApiUtils.getClient(ApiUtils.BASE_URL);
                ApiService apiService = retrofit.create(ApiService.class);
                Log.d(TAG, "LOFASZ: Sending POST request");
                apiService.logIn("lofasz", "lofasz")
                        .subscribe(new SingleObserver<LoginStatus>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.d(TAG, "LOFASZ: onSubscribe");
                            }

                            @Override
                            public void onSuccess(LoginStatus loginStatus) {
                                Log.d(TAG, "LOFASZ: onSuccess() status:" + loginStatus.getStatus());
                                Log.d(TAG, "LOFASZ: onSuccess() success:" + loginStatus.getSuccess());
                                Log.d(TAG, "LOFASZ: onSuccess() token:" + loginStatus.getToken());
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "LOFASZ: onError: " + e);
                            }
                        });

                Log.d(TAG, "LOFASZ: POST returned");
            }
        }).start();
    }

    public void showResponse(String response) {

        Log.d(TAG, "RESPONSE: " + response);
    }

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();*/

    //}

    @Override
    public boolean isUserLoggedIn(Context aContext) {
        return false;
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
