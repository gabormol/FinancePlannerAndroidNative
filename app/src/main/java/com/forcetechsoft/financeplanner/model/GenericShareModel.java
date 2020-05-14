package com.forcetechsoft.financeplanner.model;

import android.util.Log;

import com.forcetechsoft.financeplanner.MVP;
import com.forcetechsoft.financeplanner.database.ShareDatabaseOperations;

import java.lang.ref.WeakReference;

/**
 * Created by egbomol on 3/25/2016.
 */
public abstract class GenericShareModel implements MVP.ProvidedModelOps  {

    protected final String TAG =
            getClass().getSimpleName();
    protected ShareDatabaseOperations dbOperations;
    protected WeakReference<MVP.RequiredPresenterOps> mImagePresenter;

    @Override
    public void onCreate(MVP.RequiredPresenterOps presenter) {
        Log.d(TAG, "LOFASZ - Model onCreate called...");

        mImagePresenter =
                new WeakReference<>(presenter);

        dbOperations = new ShareDatabaseOperations(mImagePresenter.get().getApplicationContext());
        dbOperations.open();

    }

    @Override
    public void onDestroy(boolean isChangingConfigurations) {
        if (isChangingConfigurations)
            Log.d(TAG,
                    "just a configuration change - unbindService() not called");
        else
            // Unbind from the Services only if onDestroy() is not
            // triggered by a runtime configuration change.
            dbOperations.close();

    }
}
