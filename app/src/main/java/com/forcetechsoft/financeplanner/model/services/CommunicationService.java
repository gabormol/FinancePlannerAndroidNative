package com.forcetechsoft.financeplanner.model.services;

import android.util.Log;
import com.forcetechsoft.financeplanner.database.FinancePlannerDatabaseOperations;
import com.forcetechsoft.financeplanner.model.*;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import retrofit2.Retrofit;

import java.util.List;

/**
 * Communication service queries the backend, stores the result in SQLite database
 * and notifies the Model about the successfull operation, which can read the result
 * from the database
 */
public enum CommunicationService {

    INSTANCE;

    protected final String TAG = getClass().getSimpleName();
    private Retrofit retrofit;

    public void loginToBackend(final String username, final String password,
                               final FinancePlannerDatabaseOperations dbOperations,
                               final SimpleCallback callback){
        new Thread(new Runnable() {

            @Override
            public void run() {
                retrofit = ApiUtils.getClient(ApiUtils.BASE_URL);
                ApiService apiService = retrofit.create(ApiService.class);
                Log.d(TAG, "LOFASZ: Sending POST request");
                apiService.logIn(username, password)
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
                                dbOperations.insertUserItem(username, loginStatus.getToken(),
                                        false, " ", " "," "," ");
                                callback.onSuccess();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "LOFASZ: onError: " + e);
                                callback.onError();
                            }
                        });

                Log.d(TAG, "LOFASZ: POST returned");
            }
        }).start();
    }

    public void getMyData(final String jwt, final FinancePlannerDatabaseOperations dbOperations,
                          final SimpleCallback callback){
        new Thread(new Runnable() {

            @Override
            public void run() {
                retrofit = ApiUtils.getClient(ApiUtils.BASE_URL);
                ApiService apiService = retrofit.create(ApiService.class);
                Log.d(TAG, "LOFASZ: Sending GET request");
                apiService.myData(jwt)
                        .subscribe(new SingleObserver<List<UserData>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.d(TAG, "LOFASZ: onSubscribe");
                            }

                            @Override
                            public void onSuccess(List<UserData> userList) {
                                Log.d(TAG, "LOFASZ: onSuccess() userData list size: " + userList.size());
                                Log.d(TAG, "LOFASZ: onSuccess() userData id:" + userList.get(0).getId());
                                Log.d(TAG, "LOFASZ: onSuccess() userData username:" + userList.get(0).getUsername());
                                Log.d(TAG, "LOFASZ: onSuccess() userData admin:" + userList.get(0).isAdmin());
                                callback.onSuccess();
                                //dbOperations.insertUserItem(username, loginStatus.getToken(),
                                //        false, " ", " "," "," ");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "LOFASZ: onError: " + e);
                                callback.onError();
                            }
                        });

                Log.d(TAG, "LOFASZ: GET returned");
            }
        }).start();
    }

    public void getMyExpenses(final String jwt, final FinancePlannerDatabaseOperations dbOperations,
                          final SimpleCallback callback){
        new Thread(new Runnable() {

            @Override
            public void run() {
                retrofit = ApiUtils.getClient(ApiUtils.BASE_URL);
                ApiService apiService = retrofit.create(ApiService.class);
                Log.d(TAG, "LOFASZ: Sending GET request");
                apiService.myExpenses(jwt)
                        .subscribe(new SingleObserver<List<Expense>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.d(TAG, "LOFASZ: onSubscribe");
                            }

                            @Override
                            public void onSuccess(List<Expense> expenses) {
                                dbOperations.deleteAllTemplateItems();
                                for(Expense e : expenses){
                                    /*Log.d(TAG, "LOFASZ: onSuccess() expenses id: " + e.getId());
                                    Log.d(TAG, "LOFASZ: onSuccess() expenses expensename: " + e.getExpenseName());
                                    Log.d(TAG, "LOFASZ: onSuccess() expenses amount: " + e.getAmount());
                                    Log.d(TAG, "LOFASZ: onSuccess() expenses frequency: " + e.getFrequency());
                                    Log.d(TAG, "LOFASZ: onSuccess() expenses nextmonth: " + e.getNextMonth());
                                    Log.d(TAG, "LOFASZ: onSuccess() expenses duetomonth: " + e.getDuetoMonth());
                                    */
                                    dbOperations.insertTemplateItem(e.getExpenseName(), e.getId(), e.getAmount(),
                                            e.getFrequency(), e.getNextMonth(), e.getDuetoMonth());
                                }

                                callback.onSuccess();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "LOFASZ: onError: " + e);
                                callback.onError();
                            }
                        });

                Log.d(TAG, "LOFASZ: GET returned");
            }
        }).start();
    }
}
