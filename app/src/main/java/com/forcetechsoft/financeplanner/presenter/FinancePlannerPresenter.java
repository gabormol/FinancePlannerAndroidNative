package com.forcetechsoft.financeplanner.presenter;

import android.content.Context;
import android.util.Log;

import com.forcetechsoft.financeplanner.MVP;
import com.forcetechsoft.financeplanner.common.GenericPresenter;
import com.forcetechsoft.financeplanner.database.ShareDatabaseHelper;
import com.forcetechsoft.financeplanner.database.ShareDatabaseOperations;
import com.forcetechsoft.financeplanner.model.ContactShareModel;
import com.forcetechsoft.financeplanner.model.GenericShareModel;

import java.awt.font.TextAttribute;
import java.io.Serializable;

/**
 * Created by egbomol on 3/25/2016.
 */
public class FinancePlannerPresenter
        extends GenericPresenter<MVP.RequiredPresenterOps,
                MVP.ProvidedModelOps,
                ContactShareModel>
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

        super.onCreate(ContactShareModel.class,
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
        getModel().fetchContacts(getApplicationContext());
    }

    @Override
    public void getDatabaseDump() {
        getModel().returnAllContactsInLogd(getApplicationContext());
    }
}
