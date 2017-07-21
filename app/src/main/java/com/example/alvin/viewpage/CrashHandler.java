package com.example.alvin.viewpage;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by alvin on 2017/5/24.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static CrashHandler instance;
    private static Context ctx;

    private CrashHandler(){};

    public synchronized static CrashHandler getInstance(){
        if(instance == null){
            instance = new CrashHandler();
        }

        return instance;
    }

    public void init(Context ctx){
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.ctx = ctx;
    }

    @Override
    public void uncaughtException(Thread thread,Throwable ex){

        String msg = "UncaughtException, Thread: "+thread + " name: "+thread.getName() +" id: " + thread.getId() + " exception: "+ex;
        Toast.makeText(ctx,msg,Toast.LENGTH_LONG).show();

        /*
        LogUtil.d(Globle.LOGTAG,"UncaughtException, Thread:"+thread
            + " name: "+thread.getName() +" id: " + thread.getId() + " exception: "+ex);
        ex.printStackTrace();
        */

    }
}
