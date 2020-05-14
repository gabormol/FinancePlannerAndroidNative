package com.forcetechsoft.financeplanner.common;

/**
 * Created by egbomol on 3/24/2016.
 */

import android.content.Context;

/**
 * Defines methods for obtaining Contexts used by all views in the
 * "View" layer.
 */
public interface ContextView {
    /**
     * Get the Activity Context.
     */
    Context getActivityContext();

    /**
     * Get the Application Context.
     */
    Context getApplicationContext();
}