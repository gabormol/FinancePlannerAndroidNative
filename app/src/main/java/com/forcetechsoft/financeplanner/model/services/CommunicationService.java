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

    public void getMyTimesheet(final String jwt, final FinancePlannerDatabaseOperations dbOperations,
                              final SimpleCallback callback){
        new Thread(new Runnable() {

            @Override
            public void run() {
                retrofit = ApiUtils.getClient(ApiUtils.BASE_URL);
                ApiService apiService = retrofit.create(ApiService.class);
                Log.d(TAG, "LOFASZ: Sending GET request");
                apiService.myTimesheet(jwt)
                        .subscribe(new SingleObserver<List<TimesheetFrame>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.d(TAG, "LOFASZ: onSubscribe");
                            }

                            @Override
                            public void onSuccess(List<TimesheetFrame> timesheets) {
                                dbOperations.deleteAllTimesheeItems();
                                for(TimesheetFrame tf : timesheets){
                                    String timesheetID = tf.getId();
                                    List<TimesheetItem> tsItems= tf.getItems();
                                    for (TimesheetItem ti : tsItems)
                                    {
                                        dbOperations.insertTimesheetItem(timesheetID, ti.getId(), ti.getItemName(),
                                                ti.getAmountPlanned(), ti.getAmountPaid());
                                    }
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

    public void getMyStatistics(final String jwt, final FinancePlannerDatabaseOperations dbOperations,
                              final SimpleCallback callback){
        new Thread(new Runnable() {

            @Override
            public void run() {
                retrofit = ApiUtils.getClient(ApiUtils.BASE_URL);
                ApiService apiService = retrofit.create(ApiService.class);
                Log.d(TAG, "LOFASZ: Sending GET request");
                apiService.myStatictics(jwt)
                        .subscribe(new SingleObserver<List<StatisticItem>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.d(TAG, "LOFASZ: onSubscribe");
                            }

                            @Override
                            public void onSuccess(List<StatisticItem> statisticItems) {
                                dbOperations.deleteAllTemplateItems();
                                for(StatisticItem si : statisticItems){
                                    // No database for statistics
                                    Log.d(TAG, "LOFASZ: Statistics _id: " + si.getId());
                                    Log.d(TAG, "LOFASZ: Statistics year: " + si.getYear());
                                    Log.d(TAG, "LOFASZ: Statistics month: " + si.getMonth());
                                    Log.d(TAG, "LOFASZ: Statistics plannedToSpend: " + si.getPlannedToSpend());
                                    Log.d(TAG, "LOFASZ: Statistics totalSpent: " + si.getTotalSpent());
                                    Log.d(TAG, "LOFASZ: Statistics remainToPay: " + si.getRemainToPay());
                                    Log.d(TAG, "LOFASZ: Statistics balance: " + si.getBalance());
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
