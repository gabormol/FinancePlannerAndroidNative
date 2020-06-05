package com.forcetechsoft.financeplanner.presenter;

import android.content.Context;
import android.util.Log;
import com.forcetechsoft.financeplanner.MVP;
import com.forcetechsoft.financeplanner.common.GenericPresenter;
import com.forcetechsoft.financeplanner.model.UserModel;

/**
 * Created by egbomol on 3/25/2016.
 */
public class FinancePlannerPresenter
        extends GenericPresenter<MVP.RequiredPresenterOps,
                MVP.ProvidedModelOps,
        UserModel>
        implements MVP.ProvidedPresenterOps,
        MVP.RequiredPresenterOps {

    private Context applicationContext;
    private Context activityContext;

    @Override
    public Context getActivityContext() {
        return activityContext;
    }

    @Override
    public Context getApplicationContext() {

        return applicationContext;
    }



    @Override
    public void onCreate(MVP.RequiredViewOps view) {
        applicationContext = view.getApplicationContext();
        activityContext = view.getActivityContext();

        super.onCreate(UserModel.class,
                this);

    }

    @Override
    public void onConfigurationChange(MVP.RequiredViewOps view) {

    }

    @Override
    public void onDestroy(boolean isChangingConfigurations) {
        Log.d(TAG, "LOFASZ - Presenter destroying...");
        getModel().onDestroy(isChangingConfigurations);

    }

    @Override
    public void createContactDatabase() {
        Log.d(TAG, "LOFASZ - Presenter requesting DB creation from Model");
        //getModel().logIn(getApplicationContext());
        //getModel().myData(getApplicationContext());
        //getModel().myExpenses(getApplicationContext());
        //getModel().myStatictics(getApplicationContext());
        getModel().myTimesheet(getApplicationContext());
        //getModel().logOut();
    }

    @Override
    public void getDatabaseDump() {
        getModel().returnAllContactsInLogd(getApplicationContext());
    }
}
