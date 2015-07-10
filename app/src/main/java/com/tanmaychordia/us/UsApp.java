package com.tanmaychordia.us;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Tanmay on 7/10/15.
 */
public class UsApp extends Application {

    @Override
    public void onCreate(){
        super.onCreate();

        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "dyWJIWnj9GVRHJWy3xUXYt5hmWgtQQR5uSoUOM0T", "vaGQ8S3Eh04T8iC1Fg5VVcdbOZojncXLEzDMcdWK");


    }
}
