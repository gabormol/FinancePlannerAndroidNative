package com.forcetechsoft.financeplanner;

/**
 * Created by egbomol on 3/24/2016.
 */

import android.content.Context;

import com.forcetechsoft.financeplanner.common.ContextView;
import com.forcetechsoft.financeplanner.common.ModelOps;
import com.forcetechsoft.financeplanner.common.PresenterOps;

/**
 * Defines the interfaces for the Download Image Viewer application
 * that are required and provided by the layers in the
 * Model-View-Presenter (MVP) pattern.  This design ensures loose
 * coupling between the layers in the app's MVP-based architecture.
 */
public interface MVP {
    /**
     * This interface defines the minimum API needed by the
     * ImagePresenter class in the Presenter layer to interact with
     * DownloadImagesActivity in the View layer.  It extends the
     * ContextView interface so the Model layer can access Context's
     * defined in the View layer.
     */
    public interface RequiredViewOps
            extends ContextView {

    }

    /**
     * This interface defines the minimum public API provided by the
     * ImagePresenter class in the Presenter layer to the
     * DownloadImagesActivity in the View layer.  It extends the
     * PresenterOps interface, which is instantiated by the
     * MVP.RequiredViewOps interface used to define the parameter
     * that's passed to the onConfigurationChange() method.
     */
    public interface ProvidedPresenterOps
            extends PresenterOps<RequiredViewOps> {

        public void createContactDatabase();
        public void getDatabaseDump();

    }

    /**
     * This interface defines the minimum API needed by the ImageModel
     * class in the Model layer to interact with ImagePresenter class
     * in the Presenter layer.  It extends the ContextView interface
     * so the Model layer can access Context's defined in the View
     * layer.
     */
    public interface RequiredPresenterOps
            extends ContextView {

    }

    /**
     * This interface defines the minimum public API provided by the
     * ImageModel class in the Model layer to the ImagePresenter class
     * in the Presenter layer.  It extends the ModelOps interface,
     * which is parameterized by the MVP.RequiredPresenterOps
     * interface used to define the argument passed to the
     * onConfigurationChange() method.
     */
    public interface ProvidedModelOps
            extends ModelOps<RequiredPresenterOps> {

        public void logIn(Context aContext);
        public void logOut();
        public void myData(Context aContext);
        public boolean isUserLoggedIn(Context aContext);
        public void returnAllContactsInLogd(Context aContext);

    }
}
