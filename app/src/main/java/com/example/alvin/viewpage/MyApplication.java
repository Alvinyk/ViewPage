package com.example.alvin.viewpage;

import android.app.Application;

/**
 * Created by alvin on 2017/5/24.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();

        CrashHandler handler = CrashHandler.getInstance();

        handler.init(getApplicationContext());
    }
}
